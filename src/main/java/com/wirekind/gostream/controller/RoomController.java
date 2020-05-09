package com.wirekind.gostream.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wirekind.gostream.model.Room;
import com.wirekind.gostream.model.Users;

@RestController
@RequestMapping("/api")
public class RoomController {

	@Autowired
	Room room;

	@Autowired
	Users users;

	@RequestMapping(value = "/create-room", method = RequestMethod.GET, produces = "application/json")
	public Map<String, String> createRoom(@RequestParam(name = "username") String userName) {
		if (users.registeredUser(userName)) {
			UUID uuid = UUID.randomUUID();
			Map<String, String> map = new HashMap<String, String>();
			map.put("version", Integer.toString(uuid.version()));
			map.put("variant", Integer.toString(uuid.variant()));
			map.put("created", LocalDateTime.now().toString());
			map.put("address", uuid.toString());
			map.put("owner", userName);
			room.addAvailableRoom(userName, map);
			return map;
		} else {
			return new HashMap<String, String>();
		}
	}

	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public boolean validateRoom(@RequestParam(name = "room") String requestedRoom) {
		return room.validateRoom(requestedRoom);
	}

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public boolean removeRoom(@RequestParam(name = "room") String requestedRoom) {
		return room.removeRoom(requestedRoom);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/remove-rooms", method = RequestMethod.PUT)
	public boolean removeRooms(@RequestParam(name = "rooms") Object requestedRooms) {
		try {
			Map<String, List<String>> map = (Map<String, List<String>>) requestedRooms;
			List<String> rooms = map.get("rooms");
			return room.removeRooms(rooms);
		} catch (Exception ex) {
			return false;
		}
	}

	@RequestMapping(value = "/available-rooms", method = RequestMethod.GET)
	public List<String> getAvailableRooms() {
		return room.getAvailableRooms().stream().map(map -> map.get("address")).collect(Collectors.toList());
	}

	@RequestMapping(value = "/join-room", method = RequestMethod.GET)
	public boolean joinRoom(@RequestParam(name = "userName") String userName,
			@RequestParam(name = "room") String room) {
		return users.joinRoom(userName, room);
	}

}
