package com.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.model.AuthEntity;

@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, Integer> {

	public AuthEntity findByUserEmail(String eMail);
	

}
