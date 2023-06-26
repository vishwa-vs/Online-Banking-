package com.banking.controller;

import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.banking.model.UserEntity;
import com.banking.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/viewUser")
	public Optional<UserEntity> viewUser(@RequestBody Map<String, Object> request) {
		int userId = Integer.parseInt(request.get("userId").toString());
		if (userId > 0) {
			return userService.viewUser(userId);
		} else {
			throw new IllegalArgumentException("Enter the valid userId");
		}

	}

	@PreAuthorize("hasAuthority('USER')")
	@DeleteMapping("/deleteUser")
	public String deleteUser(@RequestBody Map<String, Object> request) {
		int userId = Integer.parseInt(request.get("userId").toString());
		
		if (userId > 0) {
			userService.deleteUser(userId);
			return "User Deleted ";
		} else {
			throw new IllegalArgumentException("Enter the valid userId");
		}
		
	}

	@PreAuthorize("hasAuthority('USER')")
	@PutMapping("/updateUser")
	public UserEntity updateUser(@RequestBody UserEntity user) {
		
		if (user != null) {
			return userService.updateUser(user);
		} else {
			throw new IllegalArgumentException("Enter the valid user");
		}
		
	}
	
	
	@PreAuthorize("hasAuthority('USER')")
	@PutMapping("/updateBank")
	public UserEntity updateBank(@RequestBody Map<String, Object> request) {
	
		long userNum = Integer.parseInt(request.get("userNum").toString());
		long accNo = Integer.parseInt(request.get("accNo").toString());
		long depositAmount = Integer.parseInt(request.get("depositAmount").toString());
		String branchName = (request.get("branchName").toString());
		
		return userService.updateBank(userNum, accNo, branchName, depositAmount);
		
	}

}
