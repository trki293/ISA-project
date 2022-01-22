package com.isa.booking_entities.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isa.booking_entities.models.entites.Instructions;

public class InstructionsQuickBookingDisplayDTO {
	private long id;
	private Instructions instructions;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime beginDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime endDate;
	
	private double orginalPrice;
	private double priceWithDiscount;
	
	public InstructionsQuickBookingDisplayDTO() {
		// TODO Auto-generated constructor stub
	}

	public InstructionsQuickBookingDisplayDTO(long id, Instructions instructions, LocalDateTime beginDate,
			LocalDateTime endDate, double orginalPrice, double priceWithDiscount) {
		this.id = id;
		this.instructions = instructions;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.orginalPrice = orginalPrice;
		this.priceWithDiscount = priceWithDiscount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Instructions getInstructions() {
		return instructions;
	}

	public void setInstructions(Instructions instructions) {
		this.instructions = instructions;
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

	public double getOrginalPrice() {
		return orginalPrice;
	}

	public void setOrginalPrice(double orginalPrice) {
		this.orginalPrice = orginalPrice;
	}

	public double getPriceWithDiscount() {
		return priceWithDiscount;
	}

	public void setPriceWithDiscount(double priceWithDiscount) {
		this.priceWithDiscount = priceWithDiscount;
	}
	
}
