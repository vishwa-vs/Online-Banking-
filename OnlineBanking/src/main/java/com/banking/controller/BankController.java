package com.banking.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.model.BankEntity;
import com.banking.service.BankService;

@RestController
@RequestMapping("/bank")
public class BankController {

	@Autowired
	private BankService bankService;

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/createBank")
	public BankEntity createBank(@RequestBody BankEntity bank) {
		if (bank != null) {
			return bankService.createBank(bank);
		} else {
			throw new IllegalArgumentException("Enter the valid bank details");
		}
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/viewBank")
	public BankEntity viewBank(@RequestBody Map<String, Object> request) {

		int accNo = Integer.parseInt(request.get("accNo").toString());
		if (accNo > 0) {
			return bankService.viewBank(accNo);
		} else {
			throw new IllegalArgumentException("Enter the valid Account Number details");
		}
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/deleteBank")
	public String deleteBank(@RequestBody Map<String, Object> request) {
		int accNo = Integer.parseInt(request.get("accNo").toString());

		if (accNo > 0) {
			bankService.deleteBank(accNo);
			return "Bank Deleted";
		} else {
			throw new IllegalArgumentException("Enter the valid Account Number details");
		}
	}
}
