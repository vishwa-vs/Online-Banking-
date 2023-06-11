package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "DealerInfo")
public class DealerInfoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DealerId")
	private int dealerId;

	@Column(name = "dealerName")
	private String dealerName;

	@Column(name = "PhoneNumber", unique = true)
	private long phoneNumber;

	@OneToOne(cascade = CascadeType.ALL)
	private AddressEntity address;

	@ManyToOne(cascade=CascadeType.ALL)
	@JsonBackReference
	@JoinColumn(name = "cart_id")
	private CartEntity cart;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JsonBackReference
	@JoinColumn(name = "order_id")
	private OrderEntity order;
	
    @OneToMany( mappedBy = "dealer" ,cascade = CascadeType.ALL )
	private List<ProductEntity> listOfProducts;


}
