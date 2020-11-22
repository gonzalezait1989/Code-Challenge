package com.wefox.challenge.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	protected void configure(final HttpSecurity http) throws Exception {  
            http
            .authorizeRequests()
            .antMatchers("/api/v1/pokemons", "/api/v1/thread").authenticated()
            .antMatchers("/**").permitAll();
    }
}