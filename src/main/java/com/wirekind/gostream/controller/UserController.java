package com.wirekind.gostream.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wirekind.gostream.model.User;
import com.wirekind.gostream.model.Users;

@RestController
public class UserController {

	@Autowired
	Users users;

	@RequestMapping(value = "/create-user", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> createUser(@RequestBody User user) {
		return users.addUser(user);
	}

	@RequestMapping(value = "/registered-users", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<User>> registeredUsers() {
		return new ResponseEntity<List<User>>(users.getUsers(), HttpStatus.OK);
	}

	@RequestMapping(value = "/validate-user", method = RequestMethod.GET)
	public Boolean validateRoom(@RequestParam(name = "username") String userName) {
		return users.registeredUser(userName);
	}

	@RequestMapping(value = "/remove-user", method = RequestMethod.GET)
	public Boolean removeRoom(@RequestParam(name = "username") String userName) {
		return users.removeUser(userName);
	}

}
