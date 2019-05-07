package atcl2019.angel.service.sample;

import javax.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetaCoinService{

  private MetaCoinContract contract;

  @Autowired
  public MetaCoinService(@Nonnull MetaCoinContract contract) {
    this.contract = contract;
  }

}
