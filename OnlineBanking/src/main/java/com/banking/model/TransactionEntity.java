package com.banking.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@SuppressWarnings("serial")
@Entity
@Table(name = "TransactionEntity")
@Data
@NoArgsConstructor
@AllArgsConstructor

    @NamedStoredProcedureQuery(
        name = "transactionEntity.getListOfTransaction",
        procedureName = "getListOfTransaction",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "accNo", type = Integer.class)
        }
    )
public class TransactionEntity implements Serializable {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY) 
	private int transactionId;
	
	@Column(name = "amount")
	private long amount;
	
	@Column(name = "description")
	private String  description;
	
	@Column(name = "transactionDate")
	private LocalDateTime transactionDate;
	
	@ManyToOne(cascade = CascadeType.ALL)
	AccountEntity fromAccount;
	
	@ManyToOne(cascade = CascadeType.ALL)
	AccountEntity toAccount;
	
	
	
}
