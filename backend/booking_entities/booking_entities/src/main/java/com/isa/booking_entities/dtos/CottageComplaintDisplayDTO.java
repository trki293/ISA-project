package com.isa.booking_entities.dtos;

import com.isa.booking_entities.models.entites.Cottage;
import com.isa.booking_entities.models.users.Client;

public class CottageComplaintDisplayDTO {
	private long id;
	private String clientFirstName;
	private String clientLastName;
	private String cottageTitle;
	private String cottageOwnerFirstName;
	private String cottageOwnerLastName;
	
	public CottageComplaintDisplayDTO() {
		// TODO Auto-generated constructor stub
	}

	public CottageComplaintDisplayDTO(long id,Client client, Cottage cottage) {
		this.id = id;
		this.clientFirstName = client.getFirstName();
		this.clientLastName = client.getLastName();
		this.cottageTitle = cottage.getTitle();
		this.cottageOwnerFirstName = cottage.getOwnerOfCottage().getFirstName();
		this.cottageOwnerLastName = cottage.getOwnerOfCottage().getLastName();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClientFirstName() {
		return clientFirstName;
	}

	public void setClientFirstName(String clientFirstName) {
		this.clientFirstName = clientFirstName;
	}

	public String getClientLastName() {
		return clientLastName;
	}

	public void setClientLastName(String clientLastName) {
		this.clientLastName = clientLastName;
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
