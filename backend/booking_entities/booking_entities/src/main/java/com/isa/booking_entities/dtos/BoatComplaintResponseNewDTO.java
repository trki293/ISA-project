package com.isa.booking_entities.dtos;

public class BoatComplaintResponseNewDTO {
	private long boatComplaintId;
	private String adminSystemEmail;
	private String text;
	
	public BoatComplaintResponseNewDTO() {
		// TODO Auto-generated constructor stub
	}

	public BoatComplaintResponseNewDTO(long boatComplaintId, String adminSystemEmail, String text) {
		this.boatComplaintId = boatComplaintId;
		this.adminSystemEmail = adminSystemEmail;
		this.text = text;
	}

	public long getBoatComplaintId() {
		return boatComplaintId;
	}

	public void setBoatComplaintId(long boatComplaintId) {
		this.boatComplaintId = boatComplaintId;
	}

	public String getAdminSystemEmail() {
		return adminSystemEmail;
	}

	public void setAdminSystemEmail(String adminSystemEmail) {
		this.adminSystemEmail = adminSystemEmail;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
