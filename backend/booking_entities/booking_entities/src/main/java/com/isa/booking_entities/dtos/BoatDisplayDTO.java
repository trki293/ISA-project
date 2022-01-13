package com.isa.booking_entities.dtos;

import com.isa.booking_entities.models.Address;

public class BoatDisplayDTO {
	private long id;
	private String title;
	private double averageGrade;
	private double pricePerHour;
	private Address address;
	private int capacity;
	
	public BoatDisplayDTO() {
		// TODO Auto-generated constructor stub
	}

	public BoatDisplayDTO(long id, String title, double averageGrade, double pricePerHour, Address address,
			int capacity) {
		this.id = id;
		this.title = title;
		this.averageGrade = averageGrade;
		this.pricePerHour = pricePerHour;
		this.address = address;
		this.capacity = capacity;
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

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
}
