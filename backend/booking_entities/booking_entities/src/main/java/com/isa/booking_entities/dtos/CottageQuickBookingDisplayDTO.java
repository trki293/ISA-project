package com.isa.booking_entities.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isa.booking_entities.models.entites.Cottage;

public class CottageQuickBookingDisplayDTO {
	private long id;
	private Cottage cottage;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime beginDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime endDate;
	private double orginalPrice;
	private double priceWithDiscount;
	
	public CottageQuickBookingDisplayDTO() {
		// TODO Auto-generated constructor stub
	}

	public CottageQuickBookingDisplayDTO(long id, Cottage cottage, LocalDateTime beginDate,
			LocalDateTime endDate, double orginalPrice, double priceWithDiscount) {
		this.id = id;
		this.cottage = cottage;
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

	public Cottage getCottage() {
		return cottage;
	}

	public void setCottage(Cottage cottage) {
		this.cottage = cottage;
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
