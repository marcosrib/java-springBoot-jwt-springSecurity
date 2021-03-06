package com.studo.jwtsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.studo.jwtsecurity.service.impl.UsuarioServiceImpl;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String username = "marcos";
	private String charSequence = "123";
	
    @Autowired
	private UsuarioServiceImpl usuarioService; 
    
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(usuarioService)
		     .passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
		    .csrf().disable()
		    .authorizeRequests()
		    .antMatchers("/api/services/**")
		    	.hasAnyRole("ADMIN")
		    .antMatchers(HttpMethod.POST,"/api/usuario/**")
		        .permitAll()
		    .anyRequest().authenticated()    
		    .and().httpBasic();
	    
	}
}

