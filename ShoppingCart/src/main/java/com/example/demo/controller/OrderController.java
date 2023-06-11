package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.OrderDTO;
import com.example.demo.model.OrderEntity;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/create")
	public OrderEntity createOrder(OrderEntity order) {
		try {
			order.setOrderTime(LocalDateTime.now());
			return orderService.createOrder(order);
		} catch (Exception e) {
			throw new RuntimeException("An error occurred while creating the order: " + e.getMessage());
		}
	}

	@GetMapping("/view")
	public OrderDTO viewOrder(@RequestBody int orderId) {
		try {
			return orderService.viewOrder(orderId);
		} catch (Exception e) {
			throw new RuntimeException("An error occurred while viewing the order: " + e.getMessage());
		}
	}

	@DeleteMapping("/cancel")
	public String cancelOrder(@RequestBody int orderId) {
		try {
			orderService.cancelOrder(orderId);
			return "Order Cancelled Successfully";
		} catch (Exception e) {

			throw new RuntimeException("An error occurred while cancelling the order: " + e.getMessage());
		}
	}

	@PutMapping("/update/order/cart/user")
	public String updateOrder(@RequestBody Map<String, Object> request) {
		try {
			int orderId = Integer.parseInt(request.get("orderId").toString());
			int cartId = Integer.parseInt(request.get("cartId").toString());
			int userId = Integer.parseInt(request.get("userId").toString());

			return orderService.updateOrder(orderId, cartId, userId);
		} catch (Exception e) {

			throw new RuntimeException("An error occurred while updating the order: " + e.getMessage());
		}
	}

}
