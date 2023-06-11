package com.example.demo.dto;

import java.util.Set;

import com.example.demo.model.ProductEntity;
import com.example.demo.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDto {

	
	private int cartId;

	private Long totalCost;

	private UserEntity userId;

	private Set<ProductEntity> productId;
	
}
