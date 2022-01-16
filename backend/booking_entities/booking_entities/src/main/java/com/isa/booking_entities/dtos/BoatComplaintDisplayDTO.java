package com.isa.booking_entities.dtos;

import com.isa.booking_entities.models.entites.Boat;
import com.isa.booking_entities.models.users.Client;

public class BoatComplaintDisplayDTO {
	private long id;
	private String clientFirstName;
	private String clientLastName;
	private String boatTitle;
	private String boatOwnerFirstName;
	private String boatOwnerLastName;
	
	public BoatComplaintDisplayDTO() {
		// TODO Auto-generated constructor stub
	}

	public BoatComplaintDisplayDTO(long id,Client client, Boat boat) {
		this.id = id;
		this.clientFirstName = client.getFirstName();
		this.clientLastName = client.getLastName();
		this.boatTitle = boat.getTitle();
		this.boatOwnerFirstName = boat.getOwnerOfBoat().getFirstName();
		this.boatOwnerLastName = boat.getOwnerOfBoat().getLastName();
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
