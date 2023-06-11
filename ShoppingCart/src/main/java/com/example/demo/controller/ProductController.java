package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ProductEntity;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/add")
	//@PreAuthorize("hasAuthority('DEALER')")
	public ProductEntity addProduct(@RequestBody ProductEntity product) {
	    try {
	        return productService.addProduct(product);
	    } catch (Exception e) {

	        throw new RuntimeException("An error occurred while adding the product: " + e.getMessage());
	    }
	}

	@GetMapping("/view")
	public Optional<ProductEntity> viewProduct(@RequestBody int productId) {
	    try {
	        return productService.viewProduct(productId);
	    } catch (Exception e) {

	        throw new RuntimeException("An error occurred while viewing the product: " + e.getMessage());
	    }
	}

	@DeleteMapping("/remove")
	//@PreAuthorize("hasAuthority('DEALER')")
	public String removeProduct(@RequestBody int productId) {
	    try {
	        productService.removeProduct(productId);
	        return "Product removed successfully";
	    } catch (Exception e) {

	        throw new RuntimeException("An error occurred while removing the product: " + e.getMessage());
	    }
	}

	@PutMapping("/updateProduct")
	public ProductEntity updateProduct(@RequestBody ProductEntity product) {
	    try {
	        return productService.updateProduct(product);
	    } catch (Exception e) {

	        throw new RuntimeException("An error occurred while updating the product: " + e.getMessage());
	    }
	}

}
