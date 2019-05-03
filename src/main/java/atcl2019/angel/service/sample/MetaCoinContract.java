package atcl2019.angel.service.sample;

import javax.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.web3j.protocol.Web3j;
import atcl2019.angel.util.AbstractEthereumContract;

public class MetaCoinContract extends AbstractEthereumContract{


   @Autowired
   public MetaCoinContract(@Nonnull Web3j ethereum) {
      super(ethereum);

   }

}
