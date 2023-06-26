package com.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.banking.model.AccountEntity;
import com.banking.model.TransactionEntity;

import jakarta.transaction.Transactional;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
	
	//public List<TransactionEntity> findByFromAccount(long fromNo);
	

	    @Query(value = "SELECT * FROM transaction_entity WHERE from_account_account_id = :accountId", nativeQuery = true)
	    List<TransactionEntity> findByFromAccountNative(@Param("accountId") int accountId);
	

	    @Query(value = "SELECT findTotalAmount()", nativeQuery = true)
	    public Integer findTotalAmount();
	   
	
	    @Procedure(value = "getListOfTransaction")
	  public List<TransactionEntity> findListofTransaction(@Param("accNo")Integer accNo);
	    
	    @Procedure(value = "jsonDataOfTransaction", outputParameterName = "transactionDetails")
		  public String findListofTransactionByjson(int accNo);

}
