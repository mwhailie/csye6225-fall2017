package com.csye6225.demo.auth;

import com.csye6225.demo.authTest.HibernateUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import javax.sql.DataSource;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private DataSource dataSource;

  @Autowired
  private BasicAuthEntryPoint basicAuthEntryPoint;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/").permitAll()
            .antMatchers("/user/register").permitAll()
        .anyRequest().authenticated()
        .and()
        .httpBasic()
        .authenticationEntryPoint(basicAuthEntryPoint);
  }
  public void configure(WebSecurity web) throws Exception {
    web
            .ignoring()
            .antMatchers("/user/register");
  }

  @Bean
  public HttpSessionStrategy httpSessionStrategy() {
    return new HeaderHttpSessionStrategy();
  }

  @Bean
  public UserDetailsService userDetailsService() {
//    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//    manager.createUser(User.withUsername("user").password(bCryptPasswordEncoder.encode("password")).roles("USER").build());
//    return manager;
    HibernateUserDetailsService hibernateUserDetailsService = new HibernateUserDetailsService();
    return hibernateUserDetailsService;
  }


  @Autowired
  public void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication().dataSource(this.dataSource).usersByUsernameQuery("select name, password, true from user where name = ?").passwordEncoder(bCryptPasswordEncoder)
            .authoritiesByUsernameQuery("select name, 'USER' from user where name = ?");
  }
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return NoOpPasswordEncoder.getInstance();
//  }
}
