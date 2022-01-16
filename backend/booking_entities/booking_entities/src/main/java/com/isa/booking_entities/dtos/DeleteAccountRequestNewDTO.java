package com.isa.booking_entities.dtos;

import com.isa.booking_entities.models.users.Users;

public class DeleteAccountRequestNewDTO {
	private String text;
	private String userEmail;
	
	public DeleteAccountRequestNewDTO() {
		// TODO Auto-generated constructor stub
	}

	public DeleteAccountRequestNewDTO(String text, Users user) {
		this.text = text;
		this.userEmail = user.getEmail();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
