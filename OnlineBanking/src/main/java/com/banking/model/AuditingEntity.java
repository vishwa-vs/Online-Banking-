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
@Table(name = "AuditingEntity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditingEntity implements Serializable{
	
	@Id
	int cusId;
	
	@Column(name = "transaction_amount")
	long amount;
	
	@Column(name="transaction_date")
	LocalDateTime transactionDate;
	

}
