package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ProductEntity;
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{
 
	
//	@Query(value = "select * from product_entity where product_id in :productIds",nativeQuery = true)
//	private List<ProductEntity> productDetials(List<String> productIds);

}
