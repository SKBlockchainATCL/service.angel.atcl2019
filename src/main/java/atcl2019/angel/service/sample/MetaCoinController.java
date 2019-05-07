package atcl2019.angel.service.sample;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/samples/metacoin",
   produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
   consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class MetaCoinController{


  @GetMapping(value = "/balance/{address}")
  public Double getBalance(@PathVariable("address") String address) {
    return Double.valueOf(0.0);
  }



}
