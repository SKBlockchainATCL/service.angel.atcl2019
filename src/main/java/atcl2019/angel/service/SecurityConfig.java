package atcl2019.angel.service;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// Referendes
//   - https://docs.spring.io/spring-security/site/docs/5.1.x/guides/html5/helloworld-boot.html

/**
 * @author Sangmoon Oh
 * @since 2019-05-07
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

  @Override
  protected void configure(HttpSecurity http) throws Exception{
    http.authorizeRequests().antMatchers("/samples/**").permitAll();
  }


}
