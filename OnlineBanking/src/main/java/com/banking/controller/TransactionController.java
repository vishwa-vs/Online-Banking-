package com.banking.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.dto.ListOfTransaction;
import com.banking.model.AccountEntity;
import com.banking.model.TransactionEntity;
import com.banking.service.TransactionService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/transferMoney")
	public TransactionEntity transferMoney(@RequestBody Map<String, Object> request) {

		String desc = request.get("desc").toString();
		long fromNo = Integer.parseInt(request.get("fromNo").toString());
		long toNo = Integer.parseInt(request.get("toNo").toString());
		int amount = Integer.parseInt(request.get("amount").toString());

		if(desc!=null && fromNo > 100000 && toNo > 100000 && amount > 0)
		{
		return transactionService.transferMoney(desc, fromNo, toNo, amount);
		} else {
			throw new IllegalArgumentException("Enter a valid details");
		}
		
		
		}

	@GetMapping("/listOfTransaction")
	public List<ListOfTransaction> listOfTransaction(@RequestBody Map<String, Object> request) {

		Integer accNo = Integer.parseInt(request.get("accNo").toString());
		if(accNo != null)
		{		
			return transactionService.listOfTransaction(accNo);
		} else {
			throw new IllegalArgumentException("Enter a valid details");
		}

	}
	
	@GetMapping("/getTotalTransferedMoney")
	public Integer getTotalTransferedMoney() {
		return transactionService.getTotalTransferedMoney();
	}
	
	@GetMapping("/getTotalTransferedMoneyByJson")
	public String getTotalTransferedMoneyByJson(@RequestBody Map<String, Object> request) {
		int accNo = Integer.parseInt(request.get("accNo").toString());
		return transactionService.getTotalTransferedMoneyByJson(accNo);
	}
	
	
	@GetMapping("/getStatement")
	public void generateExcelReport(HttpServletResponse response) throws Exception{
		
		response.setContentType("application/octet-stream");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=transaction.xls";

		response.setHeader(headerKey, headerValue);
		
		transactionService.getStatement(response);
		
		response.flushBuffer();
		
	}


}
