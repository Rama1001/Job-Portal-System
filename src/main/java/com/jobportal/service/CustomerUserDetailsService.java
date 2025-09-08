package com.jobportal.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jobportal.model.User;
import com.jobportal.repository.UserRepo;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

	private final UserRepo userRepo;

	public CustomerUserDetailsService(UserRepo userRepo){ 
		this.userRepo = userRepo; 
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		 User u = userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Not found"));
		    return org.springframework.security.core.userdetails.User
		        .withUsername(u.getEmail())
		        .password(u.getPassword())
		        .roles(u.getRoles().stream().map(Enum::name).toArray(String[]::new))
		        .build();
	}

}
