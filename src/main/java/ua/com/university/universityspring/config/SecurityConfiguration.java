package ua.com.university.universityspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ua.com.university.universityspring.service.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final UserServiceImpl userServiceImpl;

  public SecurityConfiguration(UserServiceImpl userServiceImpl) {
    this.userServiceImpl = userServiceImpl;
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
    auth.setUserDetailsService(userServiceImpl);
    auth.setPasswordEncoder(EncryptionConfig.getPasswordEncoder());
    return auth;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth){
    auth.authenticationProvider(authenticationProvider());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
                .antMatchers("/").permitAll()
            .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
            .and()
                .logout()
                .permitAll()
            .and()
                .exceptionHandling().accessDeniedPage("/access-denied")
            .and()
                .logout()
                .permitAll();
  }
}