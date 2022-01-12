package com.isa.booking_entities.dtos;

import com.isa.booking_entities.models.Address;

public class InstructionsPreviewDTO {
	private long id;
	private String title;
	private Address address;
	private String promotionalDescription;
	private String instructorBiography;
	private double averageGrade;

	public InstructionsPreviewDTO() {
		// TODO Auto-generated constructor stub
	}

	public InstructionsPreviewDTO(long id, String title, Address address, String promotionalDescription,
			String instructorBiography, double averageGrade) {
		this.id = id;
		this.title = title;
		this.address = address;
		this.promotionalDescription = promotionalDescription;
		this.instructorBiography = instructorBiography;
		this.averageGrade = averageGrade;
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

	public String getInstructorBiography() {
		return instructorBiography;
	}

	public void setInstructorBiography(String instructorBiography) {
		this.instructorBiography = instructorBiography;
	}

	public double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}

}
