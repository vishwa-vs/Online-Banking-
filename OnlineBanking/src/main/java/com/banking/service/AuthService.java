package com.banking.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.banking.model.AdminEntity;
import com.banking.model.AuthEntity;
import com.banking.model.UserEntity;
import com.banking.repository.AuthRepository;

@Service
public class AuthService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthRepository authRepository;

	private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

	public AuthEntity createAdmin(AuthEntity admin) {
		try {
			admin.setPassword(passwordEncoder.encode(admin.getPassword()));
			admin.setRole("ADMIN");
			return authRepository.save(admin);
		} catch (Exception e) {
			logger.error("Failed to create admin", e);
			throw new RuntimeException("Failed to create admin", e);
		}
	}

	public AuthEntity createUser(AuthEntity user) {
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRole("USER");
			return authRepository.save(user);
		} catch (Exception e) {
			logger.error("Failed to create user", e);
			throw new RuntimeException("Failed to create user", e);
		}
	}

}
