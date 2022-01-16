package com.isa.booking_entities.dtos;

public class BoatComplaintNewDTO {
	private long boatId;
	private String clientEmail;
	private String text;
	
	public BoatComplaintNewDTO() {
		// TODO Auto-generated constructor stub
	}

	public BoatComplaintNewDTO(long boatId, String clientEmail, String text) {
		this.boatId = boatId;
		this.clientEmail = clientEmail;
		this.text = text;
	}

	public long getBoatId() {
		return boatId;
	}

	public void setBoatId(long boatId) {
		this.boatId = boatId;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
