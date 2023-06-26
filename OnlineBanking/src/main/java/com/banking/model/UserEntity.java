package com.banking.model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "UserEntity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY) 
	private int userId;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "userNumber",unique=true)
	private long userNumber;
	
	@Column(name = "userAddress")
	private String userAddress;
	
	@OneToOne(cascade = CascadeType.ALL)
	BankEntity bank;
	
	@OneToOne(cascade = CascadeType.ALL)
	BranchEntity branch;

}
