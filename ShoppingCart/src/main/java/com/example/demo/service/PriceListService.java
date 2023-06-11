package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.DealerInfoEntity;
import com.example.demo.model.PriceListEntity;
import com.example.demo.model.ProductEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.model.UserInfoEntity;
import com.example.demo.repository.DealerInfoRepository;
import com.example.demo.repository.PriceListRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.repository.UserRepository;

@Service
public class PriceListService {
	
	@Autowired
	private PriceListRepository listRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private DealerInfoRepository dealerInfoRepository;
	
	
	public PriceListEntity createList(PriceListEntity data) {
	    try {
	        return listRepository.save(data);
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while creating the price list: " + e.getMessage());
	    }
	}

	public PriceListEntity addToList(long salesPrice, int listId, int productId, int dealerId) {
	    try {
	        PriceListEntity listEntity = listRepository.findById(listId).orElse(null);
	        if (listEntity == null) {
	            throw new RuntimeException("Price list not found");
	        }

	        ProductEntity product = productRepository.findById(productId).orElse(null);
	        if (product == null) {
	            throw new RuntimeException("Product not found");
	        }

	        DealerInfoEntity dealer = dealerInfoRepository.findById(dealerId).orElse(null);
	        if (dealer == null) {
	            throw new RuntimeException("Dealer not found");
	        }

	        listEntity.setSalesPrice(salesPrice);
	        listEntity.setProduct(product);
	        listEntity.setDealer(dealer);
	        updateSalesRate(productId);

	        return listRepository.save(listEntity);
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while adding to the price list: " + e.getMessage());
	    }
	}

	public Optional<PriceListEntity> viewList(int listId) {
	    try {
	        return listRepository.findById(listId);
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while viewing the price list: " + e.getMessage());
	    }
	}

	public List<PriceListEntity> viewAllList() {
	    try {
	        return listRepository.findAll();
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while viewing all price lists: " + e.getMessage());
	    }
	}

	public List<DealerInfoEntity> getPriceListOFDealerByProductId(int productId) {
	    try {
	        ProductEntity product = productRepository.findById(productId).orElse(null);
	        if (product == null) {
	            throw new RuntimeException("Product not found");
	        }

	        List<PriceListEntity> listEntity = listRepository.findByProductProductId(productId);
	        List<DealerInfoEntity> resultdealerList = new ArrayList<>();

	        for (PriceListEntity priceListEntity : listEntity) {
	            double totalcost = 0;
	            product = productRepository.findById(priceListEntity.getProduct().getProductId()).orElse(null);
	            if (product == null) {
	                throw new RuntimeException("Product not found");
	            }

	            totalcost = totalcost + priceListEntity.getSalesPrice();
	            resultdealerList.add(priceListEntity.getDealer());
	            product.setSalesPrice(totalcost);
	        }

	        productRepository.save(product);
	        System.out.println("-----------------sales price updated---------------");
	        return resultdealerList;
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while getting the price list of dealers by product ID: " + e.getMessage());
	    }
	}
	
	public List<ProductEntity> getPriceListOFProductsByDealerId(int dealerId) {
	    try {
	        List<PriceListEntity> listEntity = listRepository.findByDealerDealerId(dealerId);
	        List<ProductEntity> resultProductList = new ArrayList<>();
	        double totalCost = 0;

	        for (PriceListEntity priceListEntity : listEntity) {
	            ProductEntity product = productRepository.findById(priceListEntity.getProduct().getProductId()).orElse(null);
	            if (product != null) {
	                totalCost += priceListEntity.getSalesPrice();
	                product.setSalesPrice(totalCost);
	                resultProductList.add(priceListEntity.getProduct());
	            }
	        }

	        productRepository.saveAll(resultProductList);
	        System.out.println("-----------------sales price updated---------------");
	        return resultProductList;
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while getting the price list of products by dealer ID: " + e.getMessage());
	    }
	}

	public void updateSalesRate(int productId) {
		try {
		List<PriceListEntity> listEntity = listRepository.findByProductProductId(productId);
		ProductEntity product = productRepository.findById(productId).get();
		List<Double> minSalesPrice =  new ArrayList<>();
		for (PriceListEntity price : listEntity) {
			 minSalesPrice.add(price.getSalesPrice());
		}
		
	//	product.setSalesPrice(Collections.min(minSalesPrice));
		 productRepository.save(product);
		} catch (Exception e) {
			throw new RuntimeException("Error occurred while updateSalesRate" + e.getMessage());
		}
		}
	}

