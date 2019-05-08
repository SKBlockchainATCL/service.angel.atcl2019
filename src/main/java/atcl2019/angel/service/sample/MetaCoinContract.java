package atcl2019.angel.service.sample;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Repository;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import atcl2019.angel.support.web3j.AbstractEthereumContract;

// References
//   - https://web3j.readthedocs.io/en/latest/transactions.html#querying-state

@Repository
@ManagedResource(
    objectName="angel:type=bean,name=metaCoinContract",
    description="MetaCoin Contract"
    )
@ParametersAreNonnullByDefault
public class MetaCoinContract extends AbstractEthereumContract{

  @Autowired
  public MetaCoinContract(Web3j ethereum, @Value("${contract.MetaCoin.address}") String addr){
    super(ethereum, addr);

  }

  public BigInteger getBalance(@NotBlank final String addr) {
    //@TODO Check wether or not Function is thread-safe

    final Function func = new Function("getBalance",
        Arrays.asList(new Address(addr)),
        Arrays.asList(new TypeReference<Uint256>() {}));
    final String encodedFunc = FunctionEncoder.encode(func);

    final EthCall resp;
    try {
      resp = this.getEthereum().ethCall(
          Transaction.createEthCallTransaction(addr, this.getAddress(), encodedFunc),
          DefaultBlockParameterName.LATEST).sendAsync().get();

      if(resp.hasError()) throw new RuntimeException(String.format("%s (code: %d)", resp.getError().getMessage(), resp.getError().getCode()));

      List<Type> output = FunctionReturnDecoder.decode(resp.getValue(), func.getOutputParameters());
      return ((Uint256)(output.get(0))).getValue();
    }catch(Exception ex) {
      this.logger.error(ex.getMessage(), ex);
      if(ex instanceof RuntimeException) throw (RuntimeException)ex;
      else throw new RuntimeException(ex);
    }


  }


}
