package com.isa.booking_entities.dtos;

import java.time.Duration;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isa.booking_entities.models.reservations.StatusOfReservation;
import com.isa.booking_entities.models.reservations.TypeOfReservation;

public class ReservationFutureDisplayDTO {
	private long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime beginDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime endDate;
	private double duration;
	private double price;
	private boolean possibleToCancel;
	private String title;
	private StatusOfReservation statusOfReservation;
	private TypeOfReservation typeOfReservation;
	
	public ReservationFutureDisplayDTO() {
		// TODO Auto-generated constructor stub
	}

	public ReservationFutureDisplayDTO(long id, LocalDateTime beginDate, LocalDateTime endDate,
			double price, StatusOfReservation statusOfReservation, TypeOfReservation typeOfReservation) {
		this.id = id;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.duration = typeOfReservation!=TypeOfReservation.COTTAGE_RESERVATION ? Duration.between(beginDate, endDate).getSeconds()/3600 : Duration.between(beginDate, endDate).getSeconds()/86400;
		this.price = price;
		this.possibleToCancel = LocalDateTime.now().plusDays(3).isBefore(beginDate);
		this.statusOfReservation = statusOfReservation;
		this.typeOfReservation = typeOfReservation;
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

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isPossibleToCancel() {
		return possibleToCancel;
	}

	public void setPossibleToCancel(boolean possibleToCancel) {
		this.possibleToCancel = possibleToCancel;
	}

	public StatusOfReservation getStatusOfReservation() {
		return statusOfReservation;
	}

	public void setStatusOfReservation(StatusOfReservation statusOfReservation) {
		this.statusOfReservation = statusOfReservation;
	}

	public TypeOfReservation getTypeOfReservation() {
		return typeOfReservation;
	}

	public void setTypeOfReservation(TypeOfReservation typeOfReservation) {
		this.typeOfReservation = typeOfReservation;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
