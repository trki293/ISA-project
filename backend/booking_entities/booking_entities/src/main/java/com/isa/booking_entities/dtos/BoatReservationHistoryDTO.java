package com.isa.booking_entities.dtos;

import java.time.Duration;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isa.booking_entities.models.entites.Boat;
import com.isa.booking_entities.models.reservations.StatusOfReservation;

public class BoatReservationHistoryDTO {
	private long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime beginDate;
	private double duration;
	private Boat boat;
	private double price;
	private StatusOfReservation statusOfReservation;
	private	boolean userReviewed;
	
	public BoatReservationHistoryDTO() {
		// TODO Auto-generated constructor stub
	}

	public BoatReservationHistoryDTO(long id,LocalDateTime beginDate, LocalDateTime endDate, Boat boat, double price,
			StatusOfReservation statusOfReservation) {
		this.id = id;
		this.beginDate = beginDate;
		this.duration = Duration.between(beginDate, endDate).getSeconds()/3600;
		this.boat = boat;
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

	public Boat getBoat() {
		return boat;
	}

	public void setBoat(Boat boat) {
		this.boat = boat;
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
