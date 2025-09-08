package com.jobportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jobportal.model.Role;
import com.jobportal.service.UserService;

import jakarta.validation.constraints.Email;

@Controller
public class AuthController {

	private final UserService userService;

	public AuthController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String registerForm() {
		return "register";
	}

	@PostMapping("/register")
	public String register(@RequestParam @Email String email, @RequestParam String password,
			@RequestParam String fullName, @RequestParam String role) {
		Role r = "EMPLOYER".equalsIgnoreCase(role) ? Role.EMPLOYER : Role.APPLICANT;
		userService.register(email, password, fullName, r);
		return "redirect:/login?registered";
	}
}
