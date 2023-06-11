package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PriceListEntity;
import com.example.demo.model.ProductEntity;
@Repository
public interface PriceListRepository extends JpaRepository<PriceListEntity, Integer> {

	//public List<PriceListEnitity> findByproduct(int productId);
	public List<PriceListEntity> findByProductProductId(int productId);
	public List<PriceListEntity> findByDealerDealerId(int dealerId);
     
	
	
	@Query(value = "select product_id from price_list where dealer_id = :dealerId" , nativeQuery = true)
	public List<String> getProductIds(String dealerId);

}
