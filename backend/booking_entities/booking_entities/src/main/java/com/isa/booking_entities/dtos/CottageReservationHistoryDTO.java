package com.isa.booking_entities.dtos;

import java.time.Duration;
import java.time.LocalDateTime;

import com.isa.booking_entities.models.entites.Cottage;
import com.isa.booking_entities.models.reservations.StatusOfReservation;

public class CottageReservationHistoryDTO {
	private long id;
	private LocalDateTime beginDate;
	private double duration;
	private Cottage cottage;
	private double price;
	private StatusOfReservation statusOfReservation;
	
	public CottageReservationHistoryDTO() {
		// TODO Auto-generated constructor stub
	}

	public CottageReservationHistoryDTO(long id, LocalDateTime beginDate, LocalDateTime endDate, Cottage cottage, double price,
			StatusOfReservation statusOfReservation) {
		this.id = id;
		this.beginDate = beginDate;
		this.duration = Duration.between(beginDate, endDate).getSeconds()/86400;
		this.cottage = cottage;
		this.price = price;
		this.statusOfReservation = statusOfReservation;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDateTime beginDate) {
		this.beginDate = beginDate;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}
	
	public Cottage getCottage() {
		return cottage;
	}

	public void setCottage(Cottage cottage) {
		this.cottage = cottage;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public StatusOfReservation getStatusOfReservation() {
		return statusOfReservation;
	}

	public void setStatusOfReservation(StatusOfReservation statusOfReservation) {
		this.statusOfReservation = statusOfReservation;
	}
	
}
