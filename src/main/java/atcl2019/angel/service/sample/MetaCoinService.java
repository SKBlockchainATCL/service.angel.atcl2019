package atcl2019.angel.service.sample;

import java.math.BigInteger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.ThreadSafe;
import javax.validation.constraints.NotBlank;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ThreadSafe
@ParametersAreNonnullByDefault
public class MetaCoinService{

  private MetaCoinContract contract;


  @Autowired
  public MetaCoinService(@Nonnull MetaCoinContract contract) {
    this.contract = contract;
  }

  @Nullable
  public BigInteger getBalance(@NotBlank String addr) {
    Validate.isTrue(StringUtils.isNotBlank(addr));

    return this.contract.getBalance(addr);
  }


}
