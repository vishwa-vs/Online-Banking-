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
@Table(name = "AuthEntity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthEntity implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY) 
	private int userCode;
	
	@Column(name = "userEmail",unique=true)
	private String userEmail;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private String role;
	
	@OneToOne(cascade = CascadeType.ALL)
	AdminEntity admin;
	
	@OneToOne(cascade = CascadeType.ALL)
	UserEntity user;

}
