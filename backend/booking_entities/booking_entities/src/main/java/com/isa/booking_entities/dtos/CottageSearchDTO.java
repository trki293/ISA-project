package com.isa.booking_entities.dtos;

public class CottageSearchDTO {
	private String title;
	private double minAverageGrade;
	private double maxAverageGrade;
	private String city;
	private String country;
	private int minNumberOfRooms;
	private int maxNumberOfRooms;
	private int minNumberOfBeds;
	private int maxNumberOfBeds;
	private double minPricePerNight;
	private double maxPricePerNight;
	
	public CottageSearchDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getMinAverageGrade() {
		return minAverageGrade;
	}

	public void setMinAverageGrade(double minAverageGrade) {
		this.minAverageGrade = minAverageGrade;
	}

	public double getMaxAverageGrade() {
		return maxAverageGrade;
	}

	public void setMaxAverageGrade(double maxAverageGrade) {
		this.maxAverageGrade = maxAverageGrade;
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

	public double getMinPricePerNight() {
		return minPricePerNight;
	}

	public void setMinPricePerNight(double minPricePerNight) {
		this.minPricePerNight = minPricePerNight;
	}

	public double getMaxPricePerNight() {
		return maxPricePerNight;
	}

	public void setMaxPricePerNight(double maxPricePerNight) {
		this.maxPricePerNight = maxPricePerNight;
	}

	public int getMinNumberOfRooms() {
		return minNumberOfRooms;
	}

	public void setMinNumberOfRooms(int minNumberOfRooms) {
		this.minNumberOfRooms = minNumberOfRooms;
	}

	public int getMaxNumberOfRooms() {
		return maxNumberOfRooms;
	}

	public void setMaxNumberOfRooms(int maxNumberOfRooms) {
		this.maxNumberOfRooms = maxNumberOfRooms;
	}

	public int getMinNumberOfBeds() {
		return minNumberOfBeds;
	}

	public void setMinNumberOfBeds(int minNumberOfBeds) {
		this.minNumberOfBeds = minNumberOfBeds;
	}

	public int getMaxNumberOfBeds() {
		return maxNumberOfBeds;
	}

	public void setMaxNumberOfBeds(int maxNumberOfBeds) {
		this.maxNumberOfBeds = maxNumberOfBeds;
	}
	
}
