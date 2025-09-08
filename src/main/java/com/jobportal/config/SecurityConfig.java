package com.jobportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jobportal.service.CustomerUserDetailsService;

@Configuration
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authProvider(CustomerUserDetailsService uds, BCryptPasswordEncoder enc) {
		DaoAuthenticationProvider p = new DaoAuthenticationProvider();
		p.setUserDetailsService(uds);
		p.setPasswordEncoder(enc);
		return p;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/", "/login", "/register", "/css/**").permitAll()
				.requestMatchers("/employer/**").hasRole("EMPLOYER").requestMatchers("/applicant/**")
				.hasRole("APPLICANT").anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/", true).permitAll())
				.logout(l -> l.logoutUrl("/logout").logoutSuccessUrl("/").permitAll()).csrf(csrf -> csrf.disable());
		return http.build();
	}
}
