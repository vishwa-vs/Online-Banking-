package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.AddressEntity;
import com.example.demo.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	public String deleteAddress(int addressId) {
	    try {
	        AddressEntity address = addressRepository.findById(addressId).orElseThrow(() -> new NotFoundException());
	        
	        if (address.getActive().equals("NO")) {
	            return "Address does not exist";
	        } else {
	            address.setActive("NO");
	            addressRepository.save(address);
	            return "Address deleted successfully";
	        }
	    } catch (NotFoundException e) {
	        return "Address not found";
	    } catch (Exception e) {
	        return "An error occurred while deleting the address: " + e.getMessage();
	    }
	}	
}
