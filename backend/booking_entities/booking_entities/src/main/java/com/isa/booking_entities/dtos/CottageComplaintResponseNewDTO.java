package com.isa.booking_entities.dtos;

public class CottageComplaintResponseNewDTO {
	private long cottageComplaintId;
	private String systemAdminEmail;
	private String text;
	
	public CottageComplaintResponseNewDTO() {
		// TODO Auto-generated constructor stub
	}

	public CottageComplaintResponseNewDTO(long cottageComplaintId, String systemAdminEmail, String text) {
		this.cottageComplaintId = cottageComplaintId;
		this.systemAdminEmail = systemAdminEmail;
		this.text = text;
	}

	public long getCottageComplaintId() {
		return cottageComplaintId;
	}

	public void setCottageComplaintId(long cottageComplaintId) {
		this.cottageComplaintId = cottageComplaintId;
	}

	public String getSystemAdminEmail() {
		return systemAdminEmail;
	}

	public void setSystemAdminEmail(String systemAdminEmail) {
		this.systemAdminEmail = systemAdminEmail;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
