package com.example.demo.builder;

import com.example.demo.model.User;
import com.example.demo.vo.UserResponse;

public class UserResponseBuilder {

	public static UserResponse getUserReponse(User user) {
		UserResponse userResponse = new UserResponse();
		userResponse.setUserId(user.getUserId());
		userResponse.setFirstName(user.getFirstName());
		userResponse.setSirName(user.getSirName());
		userResponse.setDob(user.getDob());
		userResponse.setTitle(user.getTitle());
		userResponse.setAddressId(user.getAddressId());
		return userResponse;
	}
}
