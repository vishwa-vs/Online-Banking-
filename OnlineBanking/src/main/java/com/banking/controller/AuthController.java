package com.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.model.AuthEntity;
import com.banking.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/createAdmin")
	public AuthEntity createAdmin(@RequestBody AuthEntity admin) {

		if (admin != null) {
			return authService.createAdmin(admin);
		} else {
			throw new IllegalArgumentException("Enter the valid admin details");
		}
	}

	@PostMapping("/createUser")
	public AuthEntity createUser(@RequestBody AuthEntity user) {
		if (user != null) {
			return authService.createUser(user);
		} else {
			throw new IllegalArgumentException("Enter the valid user details");
		}
	}
}
