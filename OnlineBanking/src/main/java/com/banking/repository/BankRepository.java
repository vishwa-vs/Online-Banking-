package com.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.model.BankEntity;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, Integer> {

	public BankEntity findByAccountNumber(long accNo);

	public void deleteByAccountNumber(int bankId);

}
