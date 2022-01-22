package com.isa.booking_entities.dtos;

import com.isa.booking_entities.models.Address;

public class InstructionDisplayDTO {
	private long id;
	private String title;
	private double averageGrade;
	private double pricePerHour;
	private Address address;
	private int maxNumberOfPeoples;
	private boolean userSubscribed;
	
	public InstructionDisplayDTO() {
		// TODO Auto-generated constructor stub
	}

	public InstructionDisplayDTO(long id, String title, double averageGrade, double pricePerHour, Address address,
			int maxNumberOfPeoples) {
		this.id = id;
		this.title = title;
		this.averageGrade = averageGrade;
		this.pricePerHour = pricePerHour;
		this.address = address;
		this.maxNumberOfPeoples = maxNumberOfPeoples;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}

	public double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public Address getAddress() {
		return address;
	}
	
	public String getCountryFromAddress() {
		return address.getCountry();
	}
	
	public String getCityFromAddress() {
		return address.getCity();
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getMaxNumberOfPeoples() {
		return maxNumberOfPeoples;
	}

	public void setMaxNumberOfPeoples(int maxNumberOfPeoples) {
		this.maxNumberOfPeoples = maxNumberOfPeoples;
	}

	public boolean isUserSubscribed() {
		return userSubscribed;
	}

	public void setUserSubscribed(boolean userSubscribed) {
		this.userSubscribed = userSubscribed;
	}
	
}
