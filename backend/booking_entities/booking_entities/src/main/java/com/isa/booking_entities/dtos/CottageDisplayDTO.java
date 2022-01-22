package com.isa.booking_entities.dtos;

import com.isa.booking_entities.models.Address;

public class CottageDisplayDTO {
	private long id;
	private String title;
	private double averageGrade;
	private double pricePerNight;
	private Address address;
	private int numberOfRooms;
	private int numberOfBeds;
	private boolean userSubscribe;
	
	public CottageDisplayDTO() {
		// TODO Auto-generated constructor stub
	}

	public CottageDisplayDTO(long id, String title, double averageGrade, double pricePerNight, Address address,
			int numberOfRooms, int numberOfBeds) {
		this.id = id;
		this.title = title;
		this.averageGrade = averageGrade;
		this.pricePerNight = pricePerNight;
		this.address = address;
		this.numberOfRooms = numberOfRooms;
		this.numberOfBeds = numberOfBeds;
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

	public double getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	public Address getAddress() {
		return address;
	}
	
	public String getCityFromAddress() {
		return address.getCity();
	}
	
	public String getCountryFromAddress() {
		return address.getCountry();
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public boolean isUserSubscribe() {
		return userSubscribe;
	}

	public void setUserSubscribe(boolean userSubscribe) {
		this.userSubscribe = userSubscribe;
	}
	
}
