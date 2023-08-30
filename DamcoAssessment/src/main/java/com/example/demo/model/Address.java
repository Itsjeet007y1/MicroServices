package com.example.demo.model;

public class Address {

	private int addressId;
	private String city;
	private String state;
	private String pinCode;
	
	public Address() {
		super();
	}
	public Address(int addressId, String city, String state, String pinCode) {
		super();
		this.addressId = addressId;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Address [addressId=");
		builder.append(addressId);
		builder.append(", city=");
		builder.append(city);
		builder.append(", state=");
		builder.append(state);
		builder.append(", pinCode=");
		builder.append(pinCode);
		builder.append("]");
		return builder.toString();
	}
}
