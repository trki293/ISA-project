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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CottageComplaintDTO other = (CottageComplaintDTO) obj;
		if (cottageId != other.cottageId)
			return false;
		if (cottageOwnerFirstName == null) {
			if (other.cottageOwnerFirstName != null)
				return false;
		} else if (!cottageOwnerFirstName.equals(other.cottageOwnerFirstName))
			return false;
		if (cottageOwnerLastName == null) {
			if (other.cottageOwnerLastName != null)
				return false;
		} else if (!cottageOwnerLastName.equals(other.cottageOwnerLastName))
			return false;
		if (cottageTitle == null) {
			if (other.cottageTitle != null)
				return false;
		} else if (!cottageTitle.equals(other.cottageTitle))
			return false;
		return true;
	}
	
}
