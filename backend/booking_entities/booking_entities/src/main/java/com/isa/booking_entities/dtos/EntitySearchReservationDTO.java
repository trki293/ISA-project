package com.isa.booking_entities.dtos;

import java.time.LocalDateTime;

public class EntitySearchReservationDTO {
	private LocalDateTime beginDate;
	private LocalDateTime endDate;
	private String city;
	private String country;
	private double minAverageGrade;
	private double maxAverageGrade;
	private double minPrice;
	private double maxPrice;
	
	public EntitySearchReservationDTO() {
		// TODO Auto-generated constructor stub
	}

	public EntitySearchReservationDTO(LocalDateTime beginDate, LocalDateTime endDate, String city, String country,
			double minAverageGrade, double maxAverageGrade, double minPrice, double maxPrice) {
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.city = city;
		this.country = country;
		this.minAverageGrade = minAverageGrade;
		this.maxAverageGrade = maxAverageGrade;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	public LocalDateTime getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDateTime beginDate) {
		this.beginDate = beginDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
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

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}
	
}
