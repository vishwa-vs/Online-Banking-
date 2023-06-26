package com.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.model.BranchEntity;

@Repository
public interface BranchRepository extends JpaRepository<BranchEntity, Integer> {

	public BranchEntity findByBranchName(String branchName);

}
