package com.banking.service;

import java.time.LocalDateTime;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.banking.model.AccountEntity;
import com.banking.model.BankEntity;
import com.banking.model.BranchEntity;
import com.banking.model.UserEntity;
import com.banking.repository.AccountRepository;
import com.banking.repository.BankRepository;
import com.banking.repository.BranchRepository;
import com.banking.repository.UserRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private BankRepository bankRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);


	 public AccountEntity createAccount(AccountEntity account) {
	        try {
	            return accountRepository.save(account);
	        } catch (Exception e) {
	            logger.error("Failed to create account", e);
	            throw new RuntimeException("Failed to create account", e);
	        }
	    }

	    public Optional<AccountEntity> viewAccount(long accountId) {
	        try {
	            return accountRepository.findById(accountId);
	        } catch (Exception e) {
	            logger.error("Failed to view account", e);
	            return Optional.empty();
	        }
	    }

	    public String deleteAccount(Long accountId) {
	        try {
	            accountRepository.deleteById(accountId);
	            return "Account Deleted";
	        } catch (Exception e) {
	            logger.error("Failed to delete account", e);
	            throw new RuntimeException("Failed to delete account", e);
	        }
	    }

	    public AccountEntity updateAccount(long userNum) {
	        try {
	            UserEntity user = userRepository.findByUserNumber(userNum);
	            BankEntity bank = bankRepository.findByAccountNumber(user.getBank().getAccountNumber());

	            bank.setUpdatedAt(LocalDateTime.now());

	            AccountEntity account = new AccountEntity();
	            account.setAccountId(userNum);
	            account.setUser(user);
	            
	            return accountRepository.save(account);
	        } catch (Exception e) {
	            logger.error("Failed to update account", e);
	            throw new RuntimeException("Failed to update account", e);
	        }
	    }

	    public String viewAccountBalance(long accNo) {
	        try {
	            AccountEntity account = accountRepository.findByUserBankAccountNumber(accNo)
	                    .orElseThrow(() -> new IllegalArgumentException("Account not found"));

	            long accBalance = account.getUser().getBank().getAccountBalance();

	            return "Account Balance: " + accBalance;
	        } catch (Exception e) {
	            logger.error("Failed to view account balance", e);
	            throw new RuntimeException("Failed to view account balance", e);
	        }
	    }

}
