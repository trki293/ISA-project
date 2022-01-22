package com.isa.booking_entities.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InstructionsReservationNewDTO {
	private long instructionsId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime timeOfBeginingReservation;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime timeOfEndingReservation;
	private int numberOfPerson; 
	private List<String> namesOfAdditionalsServices;
	private String clientEmail;
	
	public InstructionsReservationNewDTO() {
		// TODO Auto-generated constructor stub
	}

	public InstructionsReservationNewDTO(long instructionsId, LocalDateTime timeOfBeginingReservation,
			LocalDateTime timeOfEndingReservation, int numberOfPerson, List<String> namesOfAdditionalsServices,
			String clientEmail) {
		this.instructionsId = instructionsId;
		this.timeOfBeginingReservation = timeOfBeginingReservation;
		this.timeOfEndingReservation = timeOfEndingReservation;
		this.numberOfPerson = numberOfPerson;
		this.namesOfAdditionalsServices = namesOfAdditionalsServices;
		this.clientEmail = clientEmail;
	}

	public long getInstructionsId() {
		return instructionsId;
	}

	public void setInstructionsId(long instructionsId) {
		this.instructionsId = instructionsId;
	}

	public LocalDateTime getTimeOfBeginingReservation() {
		return timeOfBeginingReservation;
	}

	public void setTimeOfBeginingReservation(LocalDateTime timeOfBeginingReservation) {
		this.timeOfBeginingReservation = timeOfBeginingReservation;
	}

	public LocalDateTime getTimeOfEndingReservation() {
		return timeOfEndingReservation;
	}

	public void setTimeOfEndingReservation(LocalDateTime timeOfEndingReservation) {
		this.timeOfEndingReservation = timeOfEndingReservation;
	}

	public int getNumberOfPerson() {
		return numberOfPerson;
	}

	public void setNumberOfPerson(int numberOfPerson) {
		this.numberOfPerson = numberOfPerson;
	}

	public List<String> getNamesOfAdditionalsServices() {
		return namesOfAdditionalsServices;
	}

	public void setNamesOfAdditionalsServices(List<String> namesOfAdditionalsServices) {
		this.namesOfAdditionalsServices = namesOfAdditionalsServices;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	
}
