package com.isa.booking_entities.models;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	private String streetName;
	
	private String streetNumber;
	
	private String city;
	
	private String country;
	
	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Address(String streetName, String streetNumber, String city, String country) {
		super();
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.city = city;
		this.country = country;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
