package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.CartController;
import com.example.demo.dto.CartListDTO;
import com.example.demo.dto.DealerDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.model.CartEntity;
import com.example.demo.model.DealerInfoEntity;
import com.example.demo.model.PriceListEntity;
import com.example.demo.model.ProductEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.model.UserInfoEntity;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.DealerInfoRepository;
import com.example.demo.repository.PriceListRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.repository.UserRepository;

@Service
public class CartService {

	Logger logger = LoggerFactory.getLogger(CartService.class);

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private DealerInfoRepository dealerInfoRepository;

	@Autowired
	private PriceListService listService;

	public CartEntity addCart(CartEntity cart) {
	    try {
	        return cartRepository.save(cart);
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while adding the cart: " + e.getMessage());
	    }
	}

	public Optional<CartEntity> viewCart(int cartId) {
	    try {
	        return cartRepository.findById(cartId);
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while viewing the cart: " + e.getMessage());
	    }
	}

	public String removeCart(int cartId) {
	    try {
	        cartRepository.deleteById(cartId);
	        return "Cart removed successfully";
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while removing the cart: " + e.getMessage());
	    }
	}

//	
//	public CartEntity removingFromCart(int cartId, int dealerId, int productId, int userId) {
//	    DealerInfoEntity dealer = dealerInfoRepository.findById(dealerId).get();
//	    List<ProductEntity> products = listService.getPriceListOFProductsByDealerId(dealerId);
//	    UserInfoEntity user = userInfoRepository.findById(userId).get();
//	    CartEntity cart = cartRepository.findById(cartId).get();
//
//	    cart.setCartId(cartId);
//	    cart.setUserId(user);
//	    cart.setTotalCost(null);
//
//	    List<ProductEntity> listOfProducts = new ArrayList<>();
//
//	    for (ProductEntity productEntity : products) {
//	        if (productId == productEntity.getProductId()) {
//	            productEntity.setDealer(dealer);
//	            listOfProducts.add(productEntity);
//	        }
//	    }
//
//	    List<ProductEntity> listOfFinalProducts = new ArrayList<>();
//
//	    if (!dealer.getListOfProducts().isEmpty()) {
//	        listOfFinalProducts.addAll(dealer.getListOfProducts());
//	        listOfFinalProducts.addAll(listOfProducts);
//	        dealer.setListOfProducts(listOfFinalProducts);
//	    } else {
//	        dealer.setListOfProducts(listOfProducts);
//	    }
//
//	    dealer.setCart(cart);
//	    dealerInfoRepository.save(dealer);
//
//	    List<ProductEntity> cartProducts = dealer.getListOfProducts();
//	    cartProducts.removeIf(product -> product.getProductId() == productId);
//
//	    cart.setTotalCost((long) calculateTotal(cartId));
//	    cartRepository.save(cart);
//	    return cart;
//	}


	public double calculateTotal(int cartId) {
	    try {
	        double tempCost = 0;
	        CartEntity cartEntity = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
	        List<DealerInfoEntity> dealers = cartEntity.getDealer();

	        for (DealerInfoEntity dealerEntity : dealers) {
	            List<ProductEntity> products = dealerEntity.getListOfProducts();

	            for (ProductEntity productEntity : products) {
	                tempCost += productEntity.getSalesPrice();
	            }
	        }

	        return tempCost;
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while calculating the total cost: " + e.getMessage());
	    }
	}

	public CartEntity addingIntoCart(int cartId, int dealerId, int productId, int userId) {
	    try {
	        DealerInfoEntity dealer = dealerInfoRepository.findById(dealerId).orElseThrow(() -> new RuntimeException("Dealer not found"));
	        List<ProductEntity> products = listService.getPriceListOFProductsByDealerId(dealerId);
	        UserInfoEntity user = userInfoRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
	        CartEntity cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));

	        cart.setCartId(cartId);
	        cart.setTotalCost(null);
	        cart.setUserId(user);

	        cart.setTotalCost((long) calculateTotal(cartId));

	        List<ProductEntity> listOfProducts = new ArrayList<>();

	        for (ProductEntity productEntity : products) {
	            if (productId == productEntity.getProductId()) {
	                productEntity.setDealer(dealer);
	                listOfProducts.add(productEntity);
	            }
	        }

	        List<ProductEntity> listOfFinalProducts = new ArrayList<>();

	        if (!dealer.getListOfProducts().isEmpty()) {
	            listOfFinalProducts.addAll(dealer.getListOfProducts());
	            listOfFinalProducts.addAll(listOfProducts);
	            dealer.setListOfProducts(listOfFinalProducts);
	        } else {
	            dealer.setListOfProducts(listOfProducts);
	        }

	        dealer.setCart(cart);
	        dealerInfoRepository.save(dealer);
	        cartRepository.save(cart);

	        return cart;
	    } catch (Exception e) {
	        throw new RuntimeException("Error occurred while adding items to the cart: " + e.getMessage());
	    }
	}

}
