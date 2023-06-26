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
import com.banking.model.AdminEntity;
import com.banking.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/viewAdmin")
	public Optional<AdminEntity> viewAdmin(@RequestBody Map<String, Object> request) {
		int adminId = Integer.parseInt(request.get("adminId").toString());
		if (adminId > 0) {
			return adminService.viewAdmin(adminId);
		} else {
			throw new IllegalArgumentException("Enter a valid adminId");
		}
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/deleteAdmin")
	public String deleteAdmin(@RequestBody Map<String, Object> request) {
		int adminId = Integer.parseInt(request.get("adminId").toString());

		if (adminId > 0) {
			adminService.deleteAdmin(adminId);
			return "Admin Deleted ";
		} else {
			throw new IllegalArgumentException("Enter a valid adminId");
		}

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/updateAdmin")
	public AdminEntity updateAdmin(@RequestBody AdminEntity admin) {
		if (admin != null) {
			return adminService.updateAdmin(admin);
		} else {
			throw new IllegalArgumentException("Enter a valid admin Data");
		}
	}
}
