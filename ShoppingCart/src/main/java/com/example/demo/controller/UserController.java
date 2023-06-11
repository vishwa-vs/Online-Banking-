package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ProductEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	
	@GetMapping("/check")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String show() {
		return "APi checking...";
	}
	
	@PostMapping("/addUser")
	public UserEntity addUser(@RequestBody UserEntity user) {
	    try {
	        return userService.addUser(user);
	    } catch (Exception e) {

	        throw new RuntimeException("An error occurred while adding the user: " + e.getMessage());
	    }
	}

	@PostMapping("/addDealer")
	//@PreAuthorize("hasAuthority('ADMIN')")
	public UserEntity addDealer(@RequestBody UserEntity dealer) {
	    try {
	        return userService.addDealer(dealer);
	    } catch (Exception e) {

	        throw new RuntimeException("An error occurred while adding the dealer: " + e.getMessage());
	    }
	}

	@PostMapping("/addAdmin")
	//@PreAuthorize("hasAuthority('ADMIN')")
	public UserEntity addAdmin(@RequestBody UserEntity admin) {
	    try {
	        return userService.addAdmin(admin);
	    } catch (Exception e) {

	        throw new RuntimeException("An error occurred while adding the admin: " + e.getMessage());
	    }
	}

	@GetMapping("/view")
	public Optional<UserEntity> viewUser(@RequestBody int userId) {
	    try {
	        return userService.viewUser(userId);
	    } catch (Exception e) {

	        throw new RuntimeException("An error occurred while viewing the user: " + e.getMessage());
	    }
	}

	@DeleteMapping("/remove")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String removeUser(@RequestBody int userId) {
	    try {
	        userService.removeUser(userId);
	        return "User Removed Successfully";
	    } catch (Exception e) {

	        throw new RuntimeException("An error occurred while removing the user: " + e.getMessage());
	    }
	}

	@PutMapping("/updateUser")
	public UserEntity updateUser(@RequestBody UserEntity user) {
	    try {
	        return userService.updateUser(user);
	    } catch (Exception e) {

	        throw new RuntimeException("An error occurred while updating the user: " + e.getMessage());
	    }
	}

	
}
