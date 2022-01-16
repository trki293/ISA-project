package com.isa.booking_entities.dtos;

public class CottageComplaintDTO {
	private long cottageId;
	private String cottageTitle;
	private String cottageOwnerFirstName;
	private String cottageOwnerLastName;
	
	public CottageComplaintDTO() {
		// TODO Auto-generated constructor stub
	}

	public CottageComplaintDTO(long cottageId, String cottageTitle, String cottageOwnerFirstName,
			String cottageOwnerLastName) {
		this.cottageId = cottageId;
		this.cottageTitle = cottageTitle;
		this.cottageOwnerFirstName = cottageOwnerFirstName;
		this.cottageOwnerLastName = cottageOwnerLastName;
	}

	public long getCottageId() {
		return cottageId;
	}

	public void setCottageId(long cottageId) {
		this.cottageId = cottageId;
	}

	public String getCottageTitle() {
		return cottageTitle;
	}

	public void setCottageTitle(String cottageTitle) {
		this.cottageTitle = cottageTitle;
	}

	public String getCottageOwnerFirstName() {
		return cottageOwnerFirstName;
	}

	public void setCottageOwnerFirstName(String cottageOwnerFirstName) {
		this.cottageOwnerFirstName = cottageOwnerFirstName;
	}

	public String getCottageOwnerLastName() {
		return cottageOwnerLastName;
	}

	public void setCottageOwnerLastName(String cottageOwnerLastName) {
		this.cottageOwnerLastName = cottageOwnerLastName;
	}
	
}
