package com.example.demo.service;

import java.lang.invoke.CallSite;
import java.text.Format;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.DealerDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.AddressEntity;
import com.example.demo.model.CartEntity;
import com.example.demo.model.DealerInfoEntity;
import com.example.demo.model.OrderEntity;
import com.example.demo.model.ProductEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;

@Service
public class OrderService {

	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public OrderEntity createOrder(OrderEntity order) {
	    try {
	        return orderRepository.save(order);
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while creating the order: " + e.getMessage());
	    }
	}

	public String cancelOrder(int orderId) {
	    try {
	        orderRepository.deleteById(orderId);
	        return "Order Cancelled Successfully";
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while cancelling the order: " + e.getMessage());
	    }
	}

	
	public String updateOrder(int orderId, int cartId,int userId) {

		OrderEntity order = orderRepository.findById(orderId).get();
		CartEntity cart = cartRepository.findById(cartId).get();
		UserEntity user = userRepository.findById(userId).get();

		
		order.setOrderTime(LocalDateTime.now());
	
		try {
			if (user.getUser().getAddress().getActive().equals("YES")) {
		        order.setAddress(user.getUser().getAddress());
		    } else {
		        order.setAddress(null);
		    }
		order.setUser(user);
		order.setTotalCost(cart.getTotalCost());
		 List<DealerInfoEntity> dealer = cart.getDealer();
		 
		for (DealerInfoEntity dealerInfoEntity : dealer) {
			
			dealerInfoEntity.setOrder(order);
			order.getDealerId().clear();
			order.getDealerId().add(dealerInfoEntity);
			
		}
	        if (order.getAddress() != null) {
	       
	            orderRepository.save(order);
	            return "Order Placed";
	        }
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return "Please Select the Address";
	}
	
	public OrderDTO viewOrder(int orderId) {
	    try {
	        OrderEntity order = orderRepository.findById(orderId).orElse(null);
	        if (order == null) {
	            throw new RuntimeException("Order not found");
	        }

	        OrderDTO orderDto = new OrderDTO();

	        List<DealerInfoEntity> dealers = order.getDealerId();
	        List<DealerDTO> dealerDtos = new ArrayList<>();
	        for (DealerInfoEntity dealerInfoEntity : dealers) {
	            DealerDTO dealerDto = new DealerDTO();
	            dealerDto.setDealerId(dealerInfoEntity.getDealerId());
	            dealerDto.setDealerName(dealerInfoEntity.getDealerName());

	            List<ProductDTO> productDtos = new ArrayList<>();
	            List<ProductEntity> products = dealerInfoEntity.getListOfProducts();
	            for (ProductEntity productEntity : products) {
	                ProductDTO productDto = new ProductDTO();
	                productDto.setMrp(productEntity.getMrp());
	                productDto.setProductId(productEntity.getProductId());
	                productDto.setProductName(productEntity.getProductName());
	                productDto.setSalesPrice(productEntity.getSalesPrice());
	                productDtos.add(productDto);
	            }
	            dealerDto.setListofProducts(productDtos);
	            dealerDtos.add(dealerDto);
	        }

	        orderDto.setDealers(dealerDtos);
	        orderDto.setOrderId(order.getOrderId());
	        orderDto.setOrderTime(order.getOrderTime());
	        orderDto.setTotalCost(order.getTotalCost());

	        AddressDTO addressDto = new AddressDTO();
	        addressDto.setAddress(order.getAddress().getAddress());
	        addressDto.setAddressId(order.getAddress().getAddressId());

	        UserEntity user = order.getUser();
	        UserDTO userDto = new UserDTO();
	        userDto.setContactNumber(user.getUser().getPhoneNumber());
	        userDto.setUserId(user.getUser().getUserCode());
	        userDto.setUserName(user.getUser().getUserName());
	        userDto.setAddress(addressDto);
	        orderDto.setUser(userDto);

	        return orderDto;
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while viewing the order: " + e.getMessage());
	    }
	}
}
