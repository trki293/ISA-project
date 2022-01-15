package com.isa.booking_entities.dtos;

import java.time.LocalDateTime;

import com.isa.booking_entities.models.entites.Boat;

public class BoatQuickBookingDisplayDTO {
	private long id;
	private Boat boat;
	private LocalDateTime beginDate;
	private LocalDateTime endDate;
	private double orginalPrice;
	private double priceWithDiscount;
	
	public BoatQuickBookingDisplayDTO() {
		// TODO Auto-generated constructor stub
	}

	public BoatQuickBookingDisplayDTO(long id, Boat boat, LocalDateTime beginDate, LocalDateTime endDate,
			double orginalPrice, double priceWithDiscount) {
		this.id = id;
		this.boat = boat;
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

	public Boat getBoat() {
		return boat;
	}

	public void setBoat(Boat boat) {
		this.boat = boat;
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
