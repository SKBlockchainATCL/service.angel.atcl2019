package atcl2019.angel.service.sample;

import java.math.BigInteger;
import javax.annotation.Nonnull;
import javax.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/samples/metacoin",
   produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class MetaCoinController{


  private MetaCoinService service;

  @Autowired
  public MetaCoinController(@Nonnull MetaCoinService service) {
    this.service = service;
  }


  @GetMapping(value = "/balance/{address}")
  public BigInteger getBalance(@PathVariable("address") @NotBlank String addr) {

    return this.service.getBalance(addr);
  }



}
