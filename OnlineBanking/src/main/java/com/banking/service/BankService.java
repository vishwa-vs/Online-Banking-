package com.banking.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.model.BankEntity;
import com.banking.repository.BankRepository;

@Service
public class BankService {
	
	@Autowired
	private BankRepository bankRepository;
	
	 private static final Logger logger = LoggerFactory.getLogger(BankService.class);

	    public BankEntity createBank(BankEntity bank) {
	        try {
	            bank.setCreatedAt(LocalDateTime.now());
	            return bankRepository.save(bank);
	        } catch (Exception e) {
	            logger.error("Failed to create bank", e);
	            throw new RuntimeException("Failed to create bank", e);
	        }
	    }

	    public BankEntity viewBank(int accNo) {
	        try {
	            return bankRepository.findByAccountNumber(accNo);
	        } catch (Exception e) {
	            logger.error("Failed to view bank", e);
	            throw new RuntimeException("Failed to view bank", e);
	        }
	    }

	    public String deleteBank(int bankId) {
	        try {
	            bankRepository.deleteByAccountNumber(bankId);
	            return "Bank Deleted";
	        } catch (Exception e) {
	            logger.error("Failed to delete bank", e);
	            throw new RuntimeException("Failed to delete bank", e);
	        }
	    }
}
