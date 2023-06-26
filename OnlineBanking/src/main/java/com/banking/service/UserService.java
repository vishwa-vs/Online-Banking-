package com.banking.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.model.BankEntity;
import com.banking.model.BranchEntity;
import com.banking.model.UserEntity;
import com.banking.repository.BankRepository;
import com.banking.repository.BranchRepository;
import com.banking.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BankRepository bankRepository;
	
	@Autowired
	private BranchRepository branchRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public Optional<UserEntity> viewUser(int userId) {
        try {
            return userRepository.findById(userId);
        } catch (Exception e) {
            logger.error("Failed to view user", e);
            return Optional.empty();
        }
    }

    public String deleteUser(int userId) {
        try {
            userRepository.deleteById(userId);
            return "User Deleted";
        } catch (Exception e) {
            logger.error("Failed to delete user", e);
            throw new RuntimeException("Failed to delete user", e);
        }
    }

    public UserEntity updateUser(UserEntity user) {
        try {
            UserEntity existingUser = userRepository.findById(user.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            existingUser.setUserAddress(user.getUserAddress());
            existingUser.setUserName(user.getUserName());
            existingUser.setUserNumber(user.getUserNumber());

            return userRepository.save(existingUser);
        } catch (Exception e) {
            logger.error("Failed to update user", e);
            throw new RuntimeException("Failed to update user", e);
        }
    }
    
    public UserEntity updateBank(long userNum, long accNo, String branchName, long depositAmount ) {
        try {
            UserEntity existingUser = userRepository.findByUserNumber(userNum);
            BankEntity bank = bankRepository.findByAccountNumber(accNo);
            BranchEntity branch = branchRepository.findByBranchName(branchName);
                    
            bank.setAccountBalance(depositAmount);
            
            existingUser.setBank(bank);
            existingUser.setBranch(branch);
           
            return userRepository.save(existingUser);
        } catch (Exception e) {
            logger.error("Failed to update bank", e);
            throw new RuntimeException("Failed to update bank", e);
        }
    }


}
