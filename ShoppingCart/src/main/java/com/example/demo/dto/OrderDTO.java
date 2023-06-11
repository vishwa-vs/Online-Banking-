package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	
	private int orderId;
	private double totalCost;
	private LocalDateTime orderTime;
	private UserDTO user;

	private List<DealerDTO> dealers;


}
