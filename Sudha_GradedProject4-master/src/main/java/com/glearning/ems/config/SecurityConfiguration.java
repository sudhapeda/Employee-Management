package com.glearning.ems.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	// Used for authentication
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(authenticationProvider());
		return authenticationManagerBuilder.build();
	}

	// Authorization
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors().disable();
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.authorizeRequests().antMatchers("/h2-console**", "/login**", "/logout**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/employees**", "/api/employees/**",
						"/api/employees/search/{firstName}**", "/api/employees/sort**")
				.hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
				.antMatchers(HttpMethod.POST, "/api/employees**").hasAnyRole("ADMIN", "SUPER_ADMIN")
				.antMatchers(HttpMethod.PUT, "/api/employees/**").hasAnyRole("ADMIN", "SUPER_ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("SUPER_ADMIN")
				.anyRequest().fullyAuthenticated().and().formLogin().and().httpBasic();

		return http.build();
	}

}
