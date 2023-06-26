package com.banking.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@SuppressWarnings("serial")
@Entity
@Table(name = "BankEntity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankEntity implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY) 
	private int bankId;
	
	@Column(name = "bankName")
	private String bankName;
	
	@Column(name = "accountNumber",unique=true)
	private long accountNumber;
	
	@Column(name = "accountBalance")
	private long accountBalance;
	
	@Column(name = "accountType")
	private String accountType;
	
	@Column(name = "createdAt")
	private LocalDateTime createdAt;

	@Column(name = "updatedAt")
	private LocalDateTime updatedAt;


}
