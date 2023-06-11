package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Seller;
import com.example.demo.model.SellerId;
import com.example.demo.repository.SellerRepository;

@Service
public class SellerService {
	
	@Autowired
	private SellerRepository sellerRepository;
	
	public Seller addSeller(Seller seller) {
	    try {
	        return sellerRepository.save(seller);
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while adding the seller: " + e.getMessage());
	    }
	}

	public List<Seller> viewSeller(int sellerCode, int productCode) {
	    try {
	        return sellerRepository.findBySellerCodeAndProductCode(sellerCode, productCode);
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while viewing the seller: " + e.getMessage());
	    }
	}

	public List<Seller> viewSellerById(int sellerCode) {
	    try {
	        return sellerRepository.findBySellerCode(sellerCode);
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while viewing the seller by ID: " + e.getMessage());
	    }
	}
}
