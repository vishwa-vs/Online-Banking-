package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Seller;
import com.example.demo.model.SellerId;
@Repository
public interface SellerRepository extends JpaRepository<Seller, SellerId> {
	
	
	List<Seller> findBySellerCodeAndProductCode(int sellerCode,int productCode);
	List<Seller> findBySellerCode(int sellerCode);
}
