package com.example.demo.controller;


import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.CartEntity;
import com.example.demo.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	 @Autowired
	    private CartService cartService;

	    @PostMapping("/create")
	    public CartEntity addCart(@RequestBody CartEntity cart) {
	        try {
	            return cartService.addCart(cart);
	        } catch (Exception e) {
	            throw new RuntimeException("Error occurred while adding cart: " + e.getMessage());
	        }
	    }

	    @GetMapping("/view")
	    public Optional<CartEntity> viewCart(@RequestBody int cartId) {
	        try {
	            return cartService.viewCart(cartId);
	        } catch (Exception e) {
	            throw new RuntimeException("Error occurred while viewing cart: " + e.getMessage());
	        }
	    }

	    @DeleteMapping("/remove")
	    public String removeCart(@RequestBody int cartId) {
	        try {
	            cartService.removeCart(cartId);
	            return "Cart removed successfully";
	        } catch (Exception e) {
	            throw new RuntimeException("Error occurred while removing cart: " + e.getMessage());
	        }
	    }

	    @PutMapping("/update/cart/product/user")
	    public CartEntity updateCart(@RequestBody Map<String, Object> request) {
	        try {
	            int cartId = Integer.parseInt(request.get("cartId").toString());
	            int dealerId = Integer.parseInt(request.get("dealerId").toString());
	            int productId = Integer.parseInt(request.get("productId").toString());
	            int userId = Integer.parseInt(request.get("userId").toString());

	            return cartService.addingIntoCart(cartId, dealerId, productId, userId);
	        } catch (Exception e) {
	            throw new RuntimeException("Error occurred while updating cart: " + e.getMessage());
	        }
	    }
//
//	    @PutMapping("/update/cart/decuctProduct/user")
//	    public CartEntity removeAndUpdateCart(@RequestBody Map<String, Object> request) {
//	        try {
//	            int cartId = Integer.parseInt(request.get("cartId").toString());
//	            int productId = Integer.parseInt(request.get("productId").toString());
//	            int dealerId = Integer.parseInt(request.get("dealerId").toString());
//	            int userId = Integer.parseInt(request.get("userId").toString());
//
//	            return cartService.removingFromCart(cartId, dealerId, productId, userId);
//	        } catch (Exception e) {
//	            throw new RuntimeException("Error occurred while removing and updating cart: " + e.getMessage());
//	        }
//	    }	
}
