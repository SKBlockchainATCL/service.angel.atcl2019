package atcl2019.angel.support.web3j;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotBlank;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;

/**
 * @author Sangmoon Oh
 * @since 2019-05-03
 */
@Component
public abstract class AbstractEthereumContract{

  protected final Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   *
   */
  private Web3j ethereum;

  /**
   * Get the Ethereum where the current contract resides in
   *
   * @return the Ethereum where the current contract resides in
   */
  protected Web3j getEthereum(){
    return this.ethereum;
  }

  /**
   * The address of contract at the Ethereum
   */
  private String address;

  /**
   * Get the Ethereum address of this contract.
   *
   * @return the Ethereum address of this contract.
   */
  @ManagedAttribute(currencyTimeLimit=100000)
  public String getAddress() {
    return this.address;
  }

  public AbstractEthereumContract(@Nonnull final Web3j ethereum, @NotBlank final String addr){
    Validate.isTrue(ethereum != null);
    Validate.isTrue(StringUtils.isNotBlank(addr));

    this.ethereum = ethereum;
    this.address = addr;

    logger.info("Binded Ethereum client and contract address.");
  }

  // @TODO Need implementation
  public AbstractEthereumContract(@Nonnull final Web3j ethereum, @Nonnull final Resource meta){
    throw new UnsupportedOperationException();
  }



}
