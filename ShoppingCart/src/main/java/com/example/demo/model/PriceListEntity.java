package com.example.demo.model;

import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "price_list")
public class PriceListEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="listId" )
	private int listId;
	@Column(name = "salesPrice")
	private double salesPrice;
	
	@ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
	
	@ManyToOne
    @JoinColumn(name = "dealer_id")
    private DealerInfoEntity dealer;
	
	
}
