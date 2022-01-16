package com.isa.booking_entities.dtos;

public class CottageComplaintNewDTO {
	private long cottageId;
	private String clientEmail;
	private String text;
	
	public CottageComplaintNewDTO() {
		// TODO Auto-generated constructor stub
	}

	public CottageComplaintNewDTO(long cottageId, String clientEmail, String text) {
		this.cottageId = cottageId;
		this.clientEmail = clientEmail;
		this.text = text;
	}

	public long getCottageId() {
		return cottageId;
	}

	public void setCottageId(long cottageId) {
		this.cottageId = cottageId;
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
