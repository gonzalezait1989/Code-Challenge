package com.wefox.challenge.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests()
        .antMatchers("/api/v1/pokemons/**", "/api/v1/thread/**").authenticated()
        .antMatchers("/api/v1/**").permitAll()
        .antMatchers("/**", "/accounts", "/products").permitAll()
        .anyRequest().permitAll();
  }
}