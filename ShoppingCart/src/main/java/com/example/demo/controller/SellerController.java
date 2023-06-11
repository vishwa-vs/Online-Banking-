package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Seller;
import com.example.demo.service.SellerService;

@RestController
@RequestMapping("/seller")
public class SellerController {

	@Autowired
	private SellerService sellerService;

	@PostMapping("/addSeller")
	public Seller addSeller(@RequestBody Seller seller) {
	    try {
	        return sellerService.addSeller(seller);
	    } catch (Exception e) {

	        throw new RuntimeException("An error occurred while adding the seller: " + e.getMessage());
	    }
	}

	@GetMapping("/viewSeller")
	public List<Seller> viewSeller(@RequestBody Map<String, Object> request) {
	    try {
	        int sellerCode = Integer.parseInt(request.get("sellerCode").toString());
	        int productCode = Integer.parseInt(request.get("productCode").toString());

	        return sellerService.viewSeller(sellerCode, productCode);
	    } catch (Exception e) {

	        throw new RuntimeException("An error occurred while viewing the sellers: " + e.getMessage());
	    }
	}

	@GetMapping("/viewSellerById")
	public List<Seller> viewSellerById(@RequestBody Map<String, Object> request) {
	    try {
	        int sellerCode = Integer.parseInt(request.get("sellerCode").toString());

	        return sellerService.viewSellerById(sellerCode);
	    } catch (Exception e) {

	        throw new RuntimeException("An error occurred while viewing the sellers by ID: " + e.getMessage());
	    }
	}

	
	
}
