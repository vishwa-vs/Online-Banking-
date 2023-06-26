package com.banking.controller;

import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.banking.model.AccountEntity;
import com.banking.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PreAuthorize("hasAuthority('USER')")
	@PostMapping("/createAccount")
	public AccountEntity createAccount(@RequestBody AccountEntity account) {
		if (account != null) {
			return accountService.createAccount(account);
		} else {
			throw new IllegalArgumentException("Enter the account");
		}
	}

	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/viewAccount")
	public Optional<AccountEntity> viewAccount(@RequestBody Map<String, Object> request) {
		int accountId = Integer.parseInt(request.get("accountId").toString());
		if (accountId > 0) {

			return accountService.viewAccount(accountId);
		} else {
			throw new IllegalArgumentException("Enter a valid accountId");
		}
	}

	@PreAuthorize("hasAuthority('USER')")
	@DeleteMapping("/deleteAccount")
	public String deleteAccount(@RequestBody Map<String, Object> request) {
		long accountId = Integer.parseInt(request.get("accountId").toString());
		if (accountId > 0) {
			accountService.deleteAccount(accountId);
			return "Account Deleted";
		} else {
			throw new IllegalArgumentException("Enter a valid accountId");
		}

	}

	@PreAuthorize("hasAuthority('USER')")
	@PutMapping("/updateAccount")
	public AccountEntity updateAccount(@RequestBody Map<String, Object> request) {
		int userNum = Integer.parseInt(request.get("userNum").toString());
		if (userNum > 0) {
			return accountService.updateAccount(userNum);
		} else {
			throw new IllegalArgumentException("Enter a valid Details");
		}
	}

	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/viewAccountBalance")
	public String viewAccountBalance(@RequestBody Map<String, Object> request) {
		long accNo = Integer.parseInt(request.get("accNo").toString());
		if (accNo > 0) {
			return accountService.viewAccountBalance(accNo);
		} else {
			throw new IllegalArgumentException("Enter a valid Details");
		}
	}
}
