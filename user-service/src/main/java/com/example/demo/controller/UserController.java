package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.builder.UserResponseBuilder;
import com.example.demo.config.kafka.JsonKafkaProducer;
import com.example.demo.config.kafka.KafkaProducer;
import com.example.demo.exception.DuplicateUserFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Address;
import com.example.demo.model.ResponseDefObject;
import com.example.demo.model.User;
import com.example.demo.service.IUserService;
import com.example.demo.utility.Util;
import com.example.demo.vo.UserResponse;

@RestController // Annotation for Rest Controller, It is a combination of @ResponseBody,
				// @Controller annotations and others
public class UserController {

	// creation of logger variable for log generation
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	IUserService userService; // Auto wiring user service object inside user controller
	
	@Autowired
	RestTemplate restTemplate; // Auto wiring Rest template object inside user controller
	
	@Autowired
	private KafkaProducer kafkaProducer;
	
	@Autowired
	private JsonKafkaProducer jsonKafkaProducer;

	/*
	 * Create user end point to save a user data into database if user does not
	 * exist
	 */
	@PostMapping("/saveuser")
	public ResponseEntity<ResponseDefObject<User>> createUser(@RequestBody User user) {
		logger.info("saveuser end point is called.");
		if (!userService.isUserExist(user.getUserId())) {
			User savedUser = userService.createUser(user);
			kafkaProducer.sendMessage(savedUser.toString());
			return new ResponseEntity<ResponseDefObject<User>>(
					new ResponseDefObject<User>(HttpStatus.CREATED.value(), Util.SUCCESS, savedUser),
					HttpStatus.ACCEPTED);
		} else {
			logger.warn("User already exist with the givent userId.");
			throw new DuplicateUserFoundException();
		}
	}

	/*
	 * update user end point to update the user data if user exist
	 */
	@PostMapping("/updateuser")
	public ResponseEntity<ResponseDefObject<User>> updateUser(@RequestBody User user) {
		logger.info("updateuser end point is called.");
		if (userService.isUserExist(user.getUserId())) {
			logger.info(String.format("Json message sent: %s", user));
			jsonKafkaProducer.sendJsonMessage(user);
			return new ResponseEntity<ResponseDefObject<User>>(
					new ResponseDefObject<User>(HttpStatus.CREATED.value(), Util.SUCCESS, userService.createUser(user)),
					HttpStatus.ACCEPTED);
		} else {
			logger.warn("User with given id does not exist.");
			throw new UserNotFoundException();
		}
	}

	/*
	 * get all the users
	 */
	@GetMapping("/getusers")
	public ResponseEntity<ResponseDefObject<List<User>>> getUsers() {
		logger.info("getusers end point is called.");
		return new ResponseEntity<ResponseDefObject<List<User>>>(
				new ResponseDefObject<List<User>>(HttpStatus.CREATED.value(), Util.SUCCESS, userService.readUser()),
				HttpStatus.ACCEPTED);
	}

	/*
	 * deleting the user in database if user exist
	 */
	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<ResponseDefObject<String>> deleteUser(@PathVariable("id") String id) {
		logger.info("deleteuser end point is called.");
		if (userService.isUserExist(id)) {
			return new ResponseEntity<ResponseDefObject<String>>(
					new ResponseDefObject<String>(HttpStatus.CREATED.value(), Util.SUCCESS, userService.deleteUser(id)), HttpStatus.ACCEPTED);
		} else {
			logger.warn("User does not exist with the given userId.");
			throw new UserNotFoundException();
		}
	}
	
	@GetMapping("/getuser/{userId}")
	public ResponseEntity<ResponseDefObject<UserResponse>> getUserById(@PathVariable("userId") String userId) {
		logger.info("getuser end point is called.");
		if (userService.isUserExist(userId)) {
			UserResponse userResponse = UserResponseBuilder.getUserReponse(userService.getUserById(userId));
			userResponse.setAddress(restTemplate.getForObject("http://localhost:9090/getaddress/" + userService.getUserById(userId).getAddressId(), Address.class));
			return new ResponseEntity<ResponseDefObject<UserResponse>>(
					new ResponseDefObject<UserResponse>(HttpStatus.CREATED.value(), Util.SUCCESS, userResponse),
					HttpStatus.ACCEPTED);
		} else {
			logger.warn("User already exist with the givent userId.");
			throw new DuplicateUserFoundException();
		}
	}

}
