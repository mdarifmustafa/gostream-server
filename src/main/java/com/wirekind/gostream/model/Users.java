package com.wirekind.gostream.model;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Users {

	private List<User> users = new LinkedList<User>();

	public List<User> getUsers() {
		return users;
	}

	public ResponseEntity<String> addUser(final User user) {
		try {
			if (!registeredUser(user.getUserName())) {
				this.users.add(user);
				return new ResponseEntity<String>("User created", HttpStatus.CREATED);
			} else {
				return new ResponseEntity<String>("User already exists", HttpStatus.CONFLICT);
			}
		} catch (NullPointerException ex) {
			System.err.println(ex);
			return new ResponseEntity<String>("500 | Internal Server Error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public boolean removeUser(final String userName) {
		try {
			if (registeredUser(userName)) {
				this.users.removeIf(user -> (user.getUserName().equals(userName)));
				return true;
			} else {
				return false;
			}
		} catch (NullPointerException ex) {
			return false;
		}
	}

	public boolean registeredUser(final String userName) {
		return this.users.stream().anyMatch(user -> user.getUserName().equals(userName));
	}

	public void roomPublisher(final String userName, final String room) {
		this.users.forEach(user -> {
			if (user.getUserName().equals(userName)) {
				user.setRoomPublisher(true);
			}
		});
	}

	public boolean joinRoom(final String userName, final String room) {
		this.users.forEach(user -> {
			if (user.getUserName().equals(userName)) {
				user.setJoinedRoom(room);
			}
		});
		return true;
	}

}
