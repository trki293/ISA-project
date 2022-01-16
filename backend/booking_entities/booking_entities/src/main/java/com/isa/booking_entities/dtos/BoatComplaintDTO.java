package com.isa.booking_entities.dtos;

public class BoatComplaintDTO {
	private long boatId;
	private String boatTitle;
	private String boatOwnerFirstName;
	private String boatOwnerLastName;
	
	public BoatComplaintDTO() {
		// TODO Auto-generated constructor stub
	}

	public BoatComplaintDTO(long boatId, String boatTitle, String boatOwnerFirstName, String boatOwnerLastName) {
		this.boatId = boatId;
		this.boatTitle = boatTitle;
		this.boatOwnerFirstName = boatOwnerFirstName;
		this.boatOwnerLastName = boatOwnerLastName;
	}

	public long getBoatId() {
		return boatId;
	}

	public void setBoatId(long boatId) {
		this.boatId = boatId;
	}

	public String getBoatTitle() {
		return boatTitle;
	}

	public void setBoatTitle(String boatTitle) {
		this.boatTitle = boatTitle;
	}

	public String getBoatOwnerFirstName() {
		return boatOwnerFirstName;
	}

	public void setBoatOwnerFirstName(String boatOwnerFirstName) {
		this.boatOwnerFirstName = boatOwnerFirstName;
	}

	public String getBoatOwnerLastName() {
		return boatOwnerLastName;
	}

	public void setBoatOwnerLastName(String boatOwnerLastName) {
		this.boatOwnerLastName = boatOwnerLastName;
	}
	
}
