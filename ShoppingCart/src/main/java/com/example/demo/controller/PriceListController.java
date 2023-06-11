package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.DealerInfoEntity;
import com.example.demo.model.PriceListEntity;
import com.example.demo.model.ProductEntity;
import com.example.demo.service.PriceListService;

@RestController
@RequestMapping("/PriceList")
public class PriceListController {

	@Autowired
	private PriceListService listService;

	@PostMapping("/createList")
	private PriceListEntity createList(PriceListEntity data) {
		try {
			return listService.createList(data);
		} catch (Exception e) {

			throw new RuntimeException("An error occurred while creating the price list: " + e.getMessage());
		}
	}

	@PutMapping("/addToList/listId/productId/userId")
	public PriceListEntity addToList(@RequestBody Map<String, Object> request) {
		try {
			long salesPrice = Long.parseLong(request.get("salesPrice").toString());
			int listId = Integer.parseInt(request.get("listId").toString());
			int productId = Integer.parseInt(request.get("productId").toString());
			int dealerId = Integer.parseInt(request.get("dealerId").toString());

			return listService.addToList(salesPrice, listId, productId, dealerId);
		} catch (Exception e) {

			throw new RuntimeException("An error occurred while adding to the price list: " + e.getMessage());
		}
	}

	@PostMapping("/viewList")
	public Optional<PriceListEntity> viewList(@RequestBody int listId) {
		try {
			return listService.viewList(listId);
		} catch (Exception e) {
			throw new RuntimeException("An error occurred while viewing the price list: " + e.getMessage());
		}
	}

	@GetMapping("/viewAllList")
	public List<PriceListEntity> viewAllList() {
		try {
			return listService.viewAllList();
		} catch (Exception e) {
			throw new RuntimeException("An error occurred while viewing all price lists: " + e.getMessage());
		}
	}

	@PostMapping("/priceLists")
	public List<ProductEntity> getPriceListsByProductId(@RequestBody int dealerId) {
		try {
			List<ProductEntity> priceLists = listService.getPriceListOFProductsByDealerId(dealerId);
			return priceLists;
		} catch (Exception e) {
			throw new RuntimeException(
					"An error occurred while retrieving price lists by dealer ID: " + e.getMessage());
		}
	}

}
