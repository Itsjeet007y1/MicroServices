package com.example.demo.service;

import com.example.demo.model.Address;

public interface AddressService {

	public Address saveAddress(Address address);
	public Address getAddress(String addressId);
	public boolean isAddressExist(String id);
}
