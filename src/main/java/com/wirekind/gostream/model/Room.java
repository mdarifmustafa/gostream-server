package com.wirekind.gostream.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Room {

	@Autowired
	Users users;

	private List<Map<String, String>> availableRooms = new LinkedList<Map<String, String>>();

	public List<Map<String, String>> getAvailableRooms() {
		return availableRooms;
	}

	public void addAvailableRoom(final String userName, final Map<String, String> map) {
		try {
			if (!validateRoom(map.get("address"))) {
				this.availableRooms.add(map);
				this.users.roomPublisher(userName, map.get("address"));
			}
		} catch (NullPointerException ex) {
		}
	}

	public boolean removeRoom(final String room) {
		try {
			if (validateRoom(room)) {
				this.availableRooms.removeIf(map -> (map.get("address").equals(room)));
				return true;
			} else {
				return false;
			}
		} catch (NullPointerException ex) {
			return false;
		}
	}
	
	public boolean removeRooms(final List<String> rooms) {
		try {
			rooms.stream().forEach((room) -> {
				if (validateRoom(room)) {
					this.availableRooms.removeIf(map -> (map.get("address").equals(room)));
				}
			});
			return true;
		} catch (NullPointerException ex) {
			return false;
		}
	}

	public boolean validateRoom(final String room) {
		return this.availableRooms.stream().anyMatch(map -> map.get("address").equals(room));
	}

}
