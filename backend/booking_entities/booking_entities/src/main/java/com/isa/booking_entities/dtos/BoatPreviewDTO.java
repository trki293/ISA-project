package com.isa.booking_entities.dtos;

import java.util.List;

import com.isa.booking_entities.models.Address;
import com.isa.booking_entities.models.entites.AdditionalServices;

public class BoatPreviewDTO {
	private long id;
	private String title;
	private Address address;
	private String promotionalDescription;
	private double averageGrade;
	private List<AdditionalServices> additionalServices;
	private double pricePerHour;
	
	public BoatPreviewDTO() {
		// TODO Auto-generated constructor stub
	}

	public BoatPreviewDTO(long id,String title, Address address, String promotionalDescription, double averageGrade,
			List<AdditionalServices> additionalServices, double pricePerHour) {
		this.id = id;
		this.title = title;
		this.address = address;
		this.promotionalDescription = promotionalDescription;
		this.averageGrade = averageGrade;
		this.additionalServices = additionalServices;
		this.pricePerHour = pricePerHour;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPromotionalDescription() {
		return promotionalDescription;
	}

	public void setPromotionalDescription(String promotionalDescription) {
		this.promotionalDescription = promotionalDescription;
	}

	public double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}

	public List<AdditionalServices> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(List<AdditionalServices> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}
	
}
