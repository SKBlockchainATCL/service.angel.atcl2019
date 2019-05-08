package atcl2019.angel.service.request;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/request",
   produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class ServiceRequestController{

}
