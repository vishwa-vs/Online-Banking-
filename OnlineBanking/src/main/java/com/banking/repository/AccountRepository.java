package com.banking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.model.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

	public Optional<AccountEntity> findByUserBankAccountNumber(long accNo);

	public AccountEntity findByUserUserNumber(long fromNo);


	//public Optional<AccountEntity> findByAccountNumberAccountNumber(long accNo);

}
