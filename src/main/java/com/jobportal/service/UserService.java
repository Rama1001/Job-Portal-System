package com.jobportal.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobportal.model.Role;
import com.jobportal.model.User;
import com.jobportal.repository.UserRepo;

@Service
public class UserService {

	private final UserRepo userRepo;
	private final BCryptPasswordEncoder encoder;

	public UserService(UserRepo userRepo, BCryptPasswordEncoder encoder) {
		this.userRepo = userRepo;
		this.encoder = encoder;
	}

	public User register(String email, String rawPassword, String fullName, Role role) {
		User u = new User();
		u.setEmail(email);
		u.setPassword(encoder.encode(rawPassword));
		u.setFullName(fullName);
		u.getRoles().add(role);
		return userRepo.save(u);
	}
}
