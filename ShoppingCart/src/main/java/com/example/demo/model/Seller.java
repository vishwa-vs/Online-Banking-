package com.example.demo.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity(name = "Seller")
@NoArgsConstructor
@AllArgsConstructor
@Data
@IdClass(SellerId.class)
public class Seller  {

	@Id
	private int sellerCode;
	@Id
	private int productCode;
	@Column(name = "productName")
	private String productName;
	@Column(name = "productDesc")
	private String productDesc;
	@Column(name = "mrp")
	private double mrp;
	@Column(name = "salesPrice")
	private double salesPrice;
}
