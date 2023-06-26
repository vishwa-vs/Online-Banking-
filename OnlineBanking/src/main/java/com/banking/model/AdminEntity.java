package com.banking.model;

import java.io.Serializable;

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
@Table(name = "AdminEntity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminEntity implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY) 
	private int adminId;
	
	@Column(name = "adminName")
	private String adminName;
	
	@Column(name = "adminNumber",unique=true)
	private long adminNumber;
	
	@Column(name = "adminAddress")
	private String adminAddress;

}
