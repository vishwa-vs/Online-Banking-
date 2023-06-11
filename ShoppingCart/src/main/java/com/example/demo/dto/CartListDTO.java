package com.example.demo.dto;

import java.util.List;
import com.example.demo.model.UserInfoEntity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartListDTO {

	private int cartId;

	private UserInfoEntity userEntity;
	
	
	private List<DealerDTO> dealerDTO;

}
