package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealerDTO {

	private int dealerId;
	
	private String dealerName;
	
	private List<ProductDTO> listofProducts;
}
