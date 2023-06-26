package com.banking.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListOfTransaction {
	
	String paidTo;
	
	long amount;
	
	LocalDateTime transactionTime;
	
	String debitedFrom;
	
	String creditedTo;

}
