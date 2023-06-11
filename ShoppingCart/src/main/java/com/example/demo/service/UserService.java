package com.example.demo.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.UserRepository;

@Service 
public class UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;

	
	public UserEntity addUser(UserEntity user) {
	    try {

	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        user.setRole("USER");
	        return userRepository.save(user);
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while adding the user: " + e.getMessage());
	    }
	}

	public UserEntity addDealer(UserEntity dealer) {
	    try {
	        dealer.setPassword(passwordEncoder.encode(dealer.getPassword()));
	        dealer.setRole("DEALER");
	        return userRepository.save(dealer);
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while adding the dealer: " + e.getMessage());
	    }
	}

	public UserEntity addAdmin(UserEntity admin) {
	    try {
	        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
	        admin.setRole("ADMIN");
	        return userRepository.save(admin);
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while adding the admin: " + e.getMessage());
	    }
	}

	public Optional<UserEntity> viewUser(int userId) {
	    try {
	        return userRepository.findById(userId);
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while viewing the user: " + e.getMessage());
	    }
	}

	public String removeUser(int userId) {
	    try {
	        userRepository.deleteById(userId);
	        return "User removed successfully";
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while removing the user: " + e.getMessage());
	    }
	}

	public UserEntity updateUser(UserEntity user) {
	    try {
	        UserEntity existingUser = userRepository.findById(user.getUserId()).orElse(null);
	        if (existingUser == null) {
	            throw new RuntimeException("User not found");
	        }
	        
	        existingUser.setEMail(user.getEMail());
	        existingUser.setPassword(user.getPassword());
	        existingUser.setRole(user.getRole());
	        existingUser.setUserId(user.getUserId());
	        existingUser.setDealer(user.getDealer());
	        
	        userRepository.save(existingUser);
	        
	        return existingUser;
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while updating the user: " + e.getMessage());
	    }
	}
}
