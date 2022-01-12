package com.isa.booking_entities.dtos;

public class InstructionsSearchDTO {
	private String title;
	private double minAverageGrade;
	private double maxAverageGrade;
	private String city;
	private String country;
	private double minPricePerHour;
	private double maxPricePerHour;
	private double minValueMaxNumberOfPeople;
	private double maxValueMaxNumberOfPeople;
	
	public InstructionsSearchDTO() {
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

	public double getMinValueMaxNumberOfPeople() {
		return minValueMaxNumberOfPeople;
	}

	public void setMinValueMaxNumberOfPeople(double minValueMaxNumberOfPeople) {
		this.minValueMaxNumberOfPeople = minValueMaxNumberOfPeople;
	}

	public double getMaxValueMaxNumberOfPeople() {
		return maxValueMaxNumberOfPeople;
	}

	public void setMaxValueMaxNumberOfPeople(double maxValueMaxNumberOfPeople) {
		this.maxValueMaxNumberOfPeople = maxValueMaxNumberOfPeople;
	}
	
}
