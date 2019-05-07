package atcl2019.angel.util;

import javax.annotation.Nonnull;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;

/**
 * @author Sangmoon Oh
 * @since 2019-05-03
 */
@Component
public abstract class AbstractEthereumContract{

   protected final Logger logger = LoggerFactory.getLogger(this.getClass());

   private Web3j ethereum;

   protected Web3j getEthereum() { return this.ethereum; }


   public AbstractEthereumContract(@Nonnull final Web3j ethereum) {
      Validate.isTrue(ethereum != null);

      this.ethereum = ethereum;

      logger.info("Binded Ethereum client.");
   }
}
