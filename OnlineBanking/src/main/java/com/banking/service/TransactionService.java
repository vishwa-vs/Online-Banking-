package com.banking.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.banking.dto.ListOfTransaction;
import com.banking.model.AccountEntity;
import com.banking.model.BankEntity;
import com.banking.model.TransactionEntity;
import com.banking.repository.AccountRepository;
import com.banking.repository.BankRepository;
import com.banking.repository.TransactionRepository;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service

public class TransactionService {

	@Autowired
	private EntityManager em;
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private BankRepository bankRepository;

	private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);


	public TransactionEntity transferMoney(String desc, long fromNo, long toNo, int amount) {
	    try {
	        TransactionEntity transaction = new TransactionEntity();
	        AccountEntity fromAccount = accountRepository.findById(fromNo).get();
	        AccountEntity toAccount = accountRepository.findById(toNo).get();

	        BankEntity fromBank = bankRepository.findByAccountNumber(fromAccount.getUser().getBank().getAccountNumber());
	        BankEntity toBank = bankRepository.findByAccountNumber(toAccount.getUser().getBank().getAccountNumber());

	        fromBank.setAccountBalance(fromBank.getAccountBalance() - amount);
	        fromBank.setUpdatedAt(LocalDateTime.now());

	        toBank.setAccountBalance(toBank.getAccountBalance() + amount);
	        toBank.setUpdatedAt(LocalDateTime.now());

	        bankRepository.save(fromBank);
	        bankRepository.save(toBank);

	        transaction.setAmount(amount);
	        transaction.setFromAccount(fromAccount);
	        transaction.setToAccount(toAccount);
	        transaction.setDescription(desc);
	        transaction.setTransactionDate(LocalDateTime.now());

	        return transactionRepository.save(transaction);
	    } catch (Exception e) {
	        logger.error("Error occurred during money transfer: {}", e.getMessage());
	        throw new RuntimeException("Error occurred during money transfer", e);
	    }
	}

	
    @Transactional
	@SuppressWarnings("unchecked")
	@Cacheable( cacheNames = "listOfTransaction" ,key = "#accNo")
	public List<ListOfTransaction> listOfTransaction(Integer accNo) {
	    try {
	        //List<TransactionEntity> listOfTransactions = transactionRepository.findByFromAccountNative(accountId);

	        @SuppressWarnings("unchecked")
			List<TransactionEntity> listOfTransactions = transactionRepository.findListofTransaction(accNo);
	        
	        List<ListOfTransaction> transactions = new ArrayList<>();

	        for (TransactionEntity transactionEntity : listOfTransactions) {
	            ListOfTransaction transaction = new ListOfTransaction();
	            transaction.setAmount(transactionEntity.getAmount());
	            transaction.setCreditedTo(transactionEntity.getToAccount().getUser().getBank().getBankName());
	            transaction.setDebitedFrom(transactionEntity.getFromAccount().getUser().getBank().getBankName());
	            transaction.setPaidTo(transactionEntity.getToAccount().getUser().getUserName());
	            transaction.setTransactionTime(transactionEntity.getTransactionDate());

	            transactions.add(transaction);
	        }

	        return transactions;
	    } catch (Exception e) {
	        logger.error("Error occurred while retrieving list of transactions: {}", e.getMessage());
	        throw new RuntimeException("Error occurred while retrieving list of transactions", e);
	    }
	}
    

    	public Integer getTotalTransferedMoney() {
	    try {
	        
	        return transactionRepository.findTotalAmount();
	    } catch (Exception e) {
	        logger.error("Error occurred during finding Total Amount: {}", e.getMessage());
	        throw new RuntimeException("Error occurred during finding Total Amount", e);
	    }
    	}
	    
	    public String getTotalTransferedMoneyByJson(int accNo) {
		    try {
		        
		        return transactionRepository.findListofTransactionByjson(accNo);
		    } catch (Exception e) {
		        logger.error("Error occurred during finding Total Amount: {}", e.getMessage());
		        throw new RuntimeException("Error occurred during finding Total Amount", e);
		    }
	}
	    
	    
	    public void getStatement( HttpServletResponse response) throws IOException {
	
	    	List<TransactionEntity> entity  = transactionRepository.findAll();
	    	
	    	HSSFWorkbook workBook = new HSSFWorkbook();
	    	
	    	HSSFSheet sheets = workBook.createSheet("Transaction Info");
	    	
	    	HSSFRow headRow = sheets.createRow(0);
	    	
	    	headRow.createCell(0).setCellValue("Transaction Id");
	    	headRow.createCell(1).setCellValue("Transaction Amount");
	    	headRow.createCell(2).setCellValue("Transaction Date");
	    	headRow.createCell(3).setCellValue("Transaction Description");
	    	headRow.createCell(4).setCellValue("Transaction From");
	    	headRow.createCell(5).setCellValue("Transaction To");

	    	int dataRowIndex = 1;
	    	
	    	for(TransactionEntity Transaction : entity) {
	    		
	    		HSSFRow dataRow = sheets.createRow(dataRowIndex);
	    		dataRow.createCell(0).setCellValue(Transaction.getTransactionId());
	    		dataRow.createCell(1).setCellValue(Transaction.getAmount());
	    		dataRow.createCell(2).setCellValue(Transaction.getTransactionDate().getDayOfMonth());
	    		dataRow.createCell(3).setCellValue(Transaction.getDescription());
	    		dataRow.createCell(4).setCellValue(Transaction.getFromAccount().getUser().getUserName());
	    		dataRow.createCell(5).setCellValue(Transaction.getToAccount().getUser().getUserName());
	    		dataRowIndex ++;
			}
	    	
	    	ServletOutputStream ops = response.getOutputStream();
	    	workBook.write(ops);
	    	workBook.close();
	    	ops.close();
	    	
	    }
	    
}