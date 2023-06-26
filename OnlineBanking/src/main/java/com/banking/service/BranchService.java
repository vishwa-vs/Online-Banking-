package com.banking.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.banking.model.BranchEntity;
import com.banking.repository.BranchRepository;

@Service
public class BranchService {

	@Autowired
	private BranchRepository branchRepository;

	 private static final Logger logger = LoggerFactory.getLogger(BranchService.class);

	    public BranchEntity createBranch(BranchEntity branch) {
	        try {
	            return branchRepository.save(branch);
	        } catch (Exception e) {
	            logger.error("Failed to create branch", e);
	            throw new RuntimeException("Failed to create branch", e);
	        }
	    }

	    public Optional<BranchEntity> viewBranch(int branchId) {
	        try {
	            return branchRepository.findById(branchId);
	        } catch (Exception e) {
	            logger.error("Failed to view branch", e);
	            return Optional.empty();
	        }
	    }

	    public String deleteBranch(int branchId) {
	        try {
	            branchRepository.deleteById(branchId);
	            return "Branch Deleted";
	        } catch (Exception e) {
	            logger.error("Failed to delete branch", e);
	            throw new RuntimeException("Failed to delete branch", e);
	        }
	    }

}
