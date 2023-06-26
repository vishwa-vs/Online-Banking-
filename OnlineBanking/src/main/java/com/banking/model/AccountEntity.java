package com.banking.model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
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
@Table(name = "AccountEntity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity implements Serializable {

	
	@Id
	private long AccountId;
		
	@OneToOne(cascade = CascadeType.ALL)
	UserEntity user;
	
	
}
