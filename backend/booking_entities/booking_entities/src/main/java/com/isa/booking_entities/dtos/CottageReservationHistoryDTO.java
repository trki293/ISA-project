package com.isa.booking_entities.dtos;

import java.time.Duration;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isa.booking_entities.models.entites.Cottage;
import com.isa.booking_entities.models.reservations.StatusOfReservation;

public class CottageReservationHistoryDTO {
	private long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime beginDate;
	private double duration;
	private Cottage cottage;
	private double price;
	private StatusOfReservation statusOfReservation;
	private boolean userReviewed;
	
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

	public boolean isUserReviewed() {
		return userReviewed;
	}

	public void setUserReviewed(boolean userReviewed) {
		this.userReviewed = userReviewed;
	}
	
}
