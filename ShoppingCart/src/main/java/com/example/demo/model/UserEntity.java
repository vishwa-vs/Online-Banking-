package com.example.demo.model;

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

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "User")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserId")
	private int UserId;
	
	@Column(name = "role")
	private String role;

	@Column(name = "password")
	private String password;
	
	@Column(name = "eMail", unique = true)
	private String eMail;
	
	@OneToOne(cascade = CascadeType.ALL)
	AdminInfoEntity admin;
	
	@OneToOne(cascade = CascadeType.ALL)
	UserInfoEntity user;
	
	@OneToOne(cascade = CascadeType.ALL)
	DealerInfoEntity dealer;


	
}
