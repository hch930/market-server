package com.market.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;;

@Configuration
public class SecurityConfig{
	private final Environment env;
	
	public SecurityConfig(Environment env) {
	    this.env = env;
	    System.out.println("env: " + env);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		if(isLocalMode()) {
			System.out.println("11111111111");
			setLocalMode(http);
		}else {
			//setRealMode(http);
		}
		
		http
		.httpBasic()
			.disable()
		.csrf()
			.disable()
		.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.headers()
				.frameOptions()
					.disable()
		.and()
			.authorizeHttpRequests()
				.antMatchers().permitAll()
				.antMatchers("/h2-console/*").permitAll()
				.antMatchers("/auth/**").permitAll();
			//.anyRequest().authenticated();
		
		//login Setting
		/*
		 * http .formLogin() .loginPage(null) //Get .loginProcessingUrl(null) //Post
		 * .usernameParameter(null) //Login Id value Setting .passwordParameter(null)
		 * //Login Password value Setting .defaultSuccessUrl(null); //Redirect to
		 * Current page after successful login
		 * 
		 * //logut Setting http .logout() .logoutUrl(null) .logoutSuccessUrl(null);
		 * //Redirect to Current page after successful logout
		 */		
		
		return http.build();
	}
	
	private boolean isLocalMode() {
		String profile = env.getActiveProfiles().length > 0 ? env.getActiveProfiles()[0] : "";
		System.out.println("porfile: " + profile);
		return profile.equals("local");
	}
	
	private void setLocalMode(HttpSecurity http) throws Exception{
		http
		.csrf()
			.disable();
		http
			.headers()
				.frameOptions()
					.disable();
		http
			.authorizeHttpRequests()
				.antMatchers().permitAll()
				.antMatchers("/h2-console/*").permitAll();
			//.anyRequest().authenticated();
	}
	
	private void setProdMode(HttpSecurity http) throws Exception {
		http
        .csrf().disable()
        .httpBasic().disable();
	}
}
