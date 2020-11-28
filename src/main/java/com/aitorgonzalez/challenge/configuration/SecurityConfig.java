package com.aitorgonzalez.challenge.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configuration for the different endpoints of the API.
 * 
 * @author aitor
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	/**
	 * Secures the Pokemon and Thread requiring authentication. Allows any request
	 * to the rest of the Endpoints.
	 */
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable().cors().and().authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()
				.antMatchers("/api/v1/pokemons/**", "/api/v1/thread/**").authenticated().antMatchers("/api/v1/**")
				.permitAll().antMatchers("/**", "/accounts", "/products").permitAll().anyRequest().permitAll();
	}
}