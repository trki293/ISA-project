package com.isa.booking_entities.dtos;

import java.time.Duration;
import java.time.LocalDateTime;

import com.isa.booking_entities.models.entites.Instructions;
import com.isa.booking_entities.models.reservations.StatusOfReservation;

public class InstructionsReservationHistoryDTO {
	private long id;
	private LocalDateTime beginDate;
	private double duration;
	private Instructions instructions;
	private double price;
	private StatusOfReservation statusOfReservation;
	
	public InstructionsReservationHistoryDTO() {
		// TODO Auto-generated constructor stub
	}

	public InstructionsReservationHistoryDTO(long id, LocalDateTime beginDate, LocalDateTime endDate, Instructions instructions, double price,
			StatusOfReservation statusOfReservation) {
		this.id = id;
		this.beginDate = beginDate;
		this.duration = Duration.between(beginDate, endDate).getSeconds()/3600;
		this.instructions = instructions;
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

	public Instructions getInstructions() {
		return instructions;
	}

	public void setInstructions(Instructions instructions) {
		this.instructions = instructions;
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
