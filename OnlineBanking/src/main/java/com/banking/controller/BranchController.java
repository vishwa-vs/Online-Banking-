package com.banking.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.model.BranchEntity;
import com.banking.service.BranchService;

@RestController
@RequestMapping("/branch")
public class BranchController {

	@Autowired
	private BranchService branchService;

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/createBranch")
	public BranchEntity createBranch(@RequestBody BranchEntity branch) {

		if (branch != null) {
			return branchService.createBranch(branch);
		} else {
			throw new IllegalArgumentException("Enter the valid Branch Details");
		}

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/viewBranch")
	public Optional<BranchEntity> viewBranch(@RequestBody Map<String, Object> request) {
		int branchId = Integer.parseInt(request.get("branchId").toString());

		if (branchId > 0) {
			return branchService.viewBranch(branchId);
		} else {
			throw new IllegalArgumentException("Enter the valid BranchId ");
		}

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/deleteBranch")
	public String deleteBranch(@RequestBody Map<String, Object> request) {

		int branchId = Integer.parseInt(request.get("branchId").toString());

		if (branchId > 0) {
			branchService.deleteBranch(branchId);
			return "Branch Deleted";
		} else {
			throw new IllegalArgumentException("Enter the valid BranchId ");
		}
	}
}
