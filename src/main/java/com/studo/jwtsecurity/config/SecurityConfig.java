package com.studo.jwtsecurity.config;

import org.springframework.context.annotation.Bean;

/*
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String username = "marcos";
	private String charSequence = "123";

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication()
			.passwordEncoder(passwordEncoder())
			.withUser(username)
			.password(passwordEncoder().encode(charSequence))
			.roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
		    .csrf().disable()
		    .authorizeRequests()
		    .antMatchers("/api/services/**")
		    .authenticated()
		    .and().formLogin();
	    
	}
}
*/
