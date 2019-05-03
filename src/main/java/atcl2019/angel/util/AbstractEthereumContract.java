package atcl2019.angel.util;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
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
      this.ethereum = ethereum;
   }
}
