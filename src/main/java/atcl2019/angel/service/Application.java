package atcl2019.angel.service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
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
         @Value("${ethereum.networks.local.port}") @Positive int port) {
      return this.buildWeb3j("http", addr, port);
   }


   private Web3j buildWeb3j(@NotEmpty String protocol, @NotEmpty String addr, int port) {
      Web3j instance = Web3j.build(new HttpService(String.format("%s://%s:%d", protocol, addr, port)));

      logger.info("Ethereum connector is created for {}://{}:{}", protocol, addr, port);

      return instance;
   }
}
