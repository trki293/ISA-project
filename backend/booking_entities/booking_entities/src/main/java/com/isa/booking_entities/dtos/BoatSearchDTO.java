package com.isa.booking_entities.dtos;

public class BoatSearchDTO {
	private String title;
	private double minAverageGrade;
	private double maxAverageGrade;
	private String city;
	private String country;
	private double minPricePerHour;
	private double maxPricePerHour;
	private int minCapacity;
	private int maxCapacity;

	public BoatSearchDTO() {
		// TODO Auto-generated constructor stub
	}

	public BoatSearchDTO(String title, double minAverageGrade, double maxAverageGrade, String city, String country,
			double minPricePerHour, double maxPricePerHour, int minCapacity, int maxCapacity) {
		this.title = title;
		this.minAverageGrade = minAverageGrade;
		this.maxAverageGrade = maxAverageGrade;
		this.city = city;
		this.country = country;
		this.minPricePerHour = minPricePerHour;
		this.maxPricePerHour = maxPricePerHour;
		this.minCapacity = minCapacity;
		this.maxCapacity = maxCapacity;
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

	public double getMinPricePerHour() {
		return minPricePerHour;
	}

	public void setMinPricePerHour(double minPricePerHour) {
		this.minPricePerHour = minPricePerHour;
	}

	public double getMaxPricePerHour() {
		return maxPricePerHour;
	}

	public void setMaxPricePerHour(double maxPricePerHour) {
		this.maxPricePerHour = maxPricePerHour;
	}

	public int getMinCapacity() {
		return minCapacity;
	}

	public void setMinCapacity(int minCapacity) {
		this.minCapacity = minCapacity;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

}
