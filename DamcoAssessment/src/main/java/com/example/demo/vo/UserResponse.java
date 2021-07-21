package com.example.demo.vo;

import java.util.Date;

import com.example.demo.model.Address;

public class UserResponse {

	private String userId;
	private String firstName;
	private String sirName;
	private Date dob;
	private String title;
	private Address address;
	private int addressId;
	public UserResponse(String userId, String firstName, String sirName, Date dob, String title, Address address,
			int addressId) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.sirName = sirName;
		this.dob = dob;
		this.title = title;
		this.address = address;
		this.addressId = addressId;
	}
	public UserResponse() {
		super();
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserResponse [userId=");
		builder.append(userId);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", sirName=");
		builder.append(sirName);
		builder.append(", dob=");
		builder.append(dob);
		builder.append(", title=");
		builder.append(title);
		builder.append(", address=");
		builder.append(address);
		builder.append(", addressId=");
		builder.append(addressId);
		builder.append("]");
		return builder.toString();
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSirName() {
		return sirName;
	}
	public void setSirName(String sirName) {
		this.sirName = sirName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

}
