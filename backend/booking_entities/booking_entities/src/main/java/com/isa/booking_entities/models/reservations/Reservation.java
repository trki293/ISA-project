package com.isa.booking_entities.models.reservations;

import static javax.persistence.InheritanceType.JOINED;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.isa.booking_entities.models.entites.AdditionalServices;
import com.isa.booking_entities.models.users.Client;

@Entity
@Table(name = "reservations")
@Inheritance(strategy = JOINED)
public class Reservation {
	@Id
	@SequenceGenerator(name = "mySeqGenReservation", sequenceName = "mySeqReservation", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenReservation")
	private long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "UTC")
	private LocalDateTime timeOfBeginingReservation;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "UTC")
	private LocalDateTime timeOfEndingReservation;

	@Column(name = "numberOfPerson", unique = false, nullable = false)
	private int numberOfPerson;

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<AdditionalServices> additionalServicesFromClient = new HashSet<AdditionalServices>();

	@Column(name = "totalPrice", unique = false, nullable = false)
	private double totalPrice;
	
	@Enumerated(EnumType.ORDINAL)
	private TypeOfReservation typeOfReservation;

	@Enumerated(EnumType.ORDINAL)
	private StatusOfReservation statusOfReservation;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Client clientForReservation;

	public Reservation() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Set<AdditionalServices> getAdditionalServicesFromClient() {
		return additionalServicesFromClient;
	}

	public void setAdditionalServicesFromClient(Set<AdditionalServices> additionalServicesFromClient) {
		this.additionalServicesFromClient = additionalServicesFromClient;
	}

	public Client getClientForReservation() {
		return clientForReservation;
	}

	public void setClientForReservation(Client clientForReservation) {
		this.clientForReservation = clientForReservation;
	}

	public TypeOfReservation getTypeOfReservation() {
		return typeOfReservation;
	}

	public void setTypeOfReservation(TypeOfReservation typeOfReservation) {
		this.typeOfReservation = typeOfReservation;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public StatusOfReservation getStatusOfReservation() {
		return statusOfReservation;
	}

	public void setStatusOfReservation(StatusOfReservation statusOfReservation) {
		this.statusOfReservation = statusOfReservation;
	}
	
}
