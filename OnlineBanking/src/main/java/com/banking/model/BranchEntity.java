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
@Table(name = "BranchEntity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchEntity implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY) 
	private int branchId;
	
	@Column(name = "branchName",unique=true)
	private String branchName;
	
	@Column(name = "branchManager")
	private String branchManager;
	
	@Column(name = "branchAddress")
	private String branchAddress;

}
