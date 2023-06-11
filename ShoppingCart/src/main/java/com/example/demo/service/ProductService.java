package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ProductEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public ProductEntity addProduct(ProductEntity product) {
	    try {
	        return productRepository.save(product);
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while adding the product: " + e.getMessage());
	    }
	}

	public Optional<ProductEntity> viewProduct(int productId) {
	    try {
	        return productRepository.findById(productId);
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while viewing the product: " + e.getMessage());
	    }
	}

	public String removeProduct(int productId) {
	    try {
	        productRepository.deleteById(productId);
	        return "Product removed successfully";
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while removing the product: " + e.getMessage());
	    }
	}

	public ProductEntity updateProduct(ProductEntity product) {
	    try {
	        ProductEntity existingProduct = productRepository.findById(product.getProductId()).orElse(null);
	        if (existingProduct == null) {
	            throw new RuntimeException("Product not found");
	        }
	        
	        existingProduct.setProductId(product.getProductId());
	        existingProduct.setMrp(product.getMrp());
	        existingProduct.setProductName(product.getProductName());
	        existingProduct.setProductDesc(product.getProductDesc());
	        existingProduct.setSalesPrice(product.getSalesPrice());

	        return productRepository.save(existingProduct);
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while updating the product: " + e.getMessage());
	    }
	}

	
	
}
