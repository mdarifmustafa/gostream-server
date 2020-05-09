package com.wirekind.gostream.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class User {

	private String userName;
	private String fullName;
	private Date dateOfBirth;
	private String emailId;
	private String joinedRoom;
	private List<String> roomPublishing;

	public List<String> getRoomPublishing() {
		return roomPublishing;
	}

	public boolean addRoomPublishing(String room) {
		if (!roomPublishing.contains(room)) {
			return roomPublishing.add(room);
		} else {
			return false;
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		try {
			this.dateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse(dateOfBirth);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getEmailId() {
		return emailId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((joinedRoom == null) ? 0 : joinedRoom.hashCode());
		result = prime * result + ((roomPublishing == null) ? 0 : roomPublishing.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (joinedRoom == null) {
			if (other.joinedRoom != null)
				return false;
		} else if (!joinedRoom.equals(other.joinedRoom))
			return false;
		if (roomPublishing == null) {
			if (other.roomPublishing != null)
				return false;
		} else if (!roomPublishing.equals(other.roomPublishing))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getJoinedRoom() {
		return joinedRoom;
	}

	public void setJoinedRoom(String joinedRoom) {
		this.joinedRoom = joinedRoom;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", fullName=" + fullName + ", dateOfBirth=" + dateOfBirth + ", emailId="
				+ emailId + ", joinedRoom=" + joinedRoom + ", roomPublishing=" + roomPublishing + "]";
	}

}
