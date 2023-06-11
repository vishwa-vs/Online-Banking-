package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AddressEntity;
import com.example.demo.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	
	
	@DeleteMapping("/deleteAddress")
	public String deleteAddress(@RequestBody int addressId) {
	    try {
	        return addressService.deleteAddress(addressId);
	    } catch (Exception e) {
	        return "An error occurred while deleting the address: " + e.getMessage();
	    }
	}

}
