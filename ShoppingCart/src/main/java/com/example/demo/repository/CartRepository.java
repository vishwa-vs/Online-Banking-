package com.example.demo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CartEntity;
import com.example.demo.model.ProductEntity;
import com.example.demo.model.UserEntity;

import jakarta.transaction.Transactional;
@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {
	
	
	

}
