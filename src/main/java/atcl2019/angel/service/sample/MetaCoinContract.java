package atcl2019.angel.service.sample;

import java.math.BigInteger;
import java.util.Arrays;
import javax.annotation.Nonnull;
import javax.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import atcl2019.angel.util.AbstractEthereumContract;

// References
//   - https://web3j.readthedocs.io/en/latest/transactions.html#querying-state

@Repository
public class MetaCoinContract extends AbstractEthereumContract{

  @Autowired
  public MetaCoinContract(@Nonnull Web3j ethereum){
    super(ethereum);

  }

  public BigInteger getBalance(@NotBlank final String addr) {
    //@TODO Check wether or not Function is thread-safe

    final Function func = new Function("getBalance",
        Arrays.asList(new Address(addr)),
        Arrays.asList(new TypeReference<Uint256>() {}));
    final String encodedFunc = FunctionEncoder.encode(func);



    return null;
  }


}
