package atcl2019.angel.service;

import javax.annotation.Nonnull;
import javax.annotation.WillNotClose;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@SpringBootApplication
public class Application{

   private Logger logger = LoggerFactory.getLogger(this.getClass());

   public static void main(String... args){

      SpringApplication.run(Application.class, args);
   }

   @Bean
   public Web3j ethereum(@Value("${ethereum.networks.local.address}") @NotEmpty String addr,
         @Value("${ethereum.networks.local.port}") @Positive int port,
         @Value("${ethereum.networks.local.port}") @NotEmpty String netVersion) {
      return this.buildWeb3j("http", addr, port, netVersion);
   }


   private Web3j buildWeb3j(@NotEmpty String protocol, @NotEmpty String addr, int port, @NotEmpty String netVersion) {
      Web3j instance = Web3j.build(new HttpService(String.format("%s://%s:%d", protocol, addr, port)));

      logger.info("Ethereum connector is created for {}://{}:{}", protocol, addr, port);

      try {
        validateEthereumNetVersion(instance, netVersion);
        logger.info("Successfully validate the network ID of the specified Ethereum at {}://{}:{}", protocol, addr, port);
      } catch(Exception ex) {
        logger.warn(String.format("Fail to validate network ID of the specified Ethereum at %s://%s:%d", protocol, addr, port), ex);
      }

      return instance;
   }

   private void validateEthereumNetVersion(@Nonnull @WillNotClose Web3j ethereum, @Nonnull String ver) throws Exception{
      if (ethereum == null) throw new RuntimeException("...");

      String ver1 = ethereum.netVersion().send().getNetVersion();

      Validate.isTrue(StringUtils.equals(ver1, ver));
   }
}
