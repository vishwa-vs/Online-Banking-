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
@Table(name = "AdminInfo")
public class AdminInfoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AdminCode")
	private int adminCode;
	
	@Column(name = "adminName")
	private String adminName;
	
	@Column(name = "PhoneNumber", unique = true)
	private long phoneNumber;
	
	@OneToOne(cascade = CascadeType.ALL)
	private AddressEntity address;

}
