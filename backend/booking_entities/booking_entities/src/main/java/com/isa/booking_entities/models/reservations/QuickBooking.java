package com.isa.booking_entities.models.reservations;

import static javax.persistence.InheritanceType.JOINED;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isa.booking_entities.models.entites.AdditionalServices;

@Entity
@Table(name = "quick_bookings")
@Inheritance(strategy = JOINED)
public class QuickBooking {
	
	@Id
	@SequenceGenerator(name = "mySeqGenQuickBooking", sequenceName = "mySeqQuickBooking", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenQuickBooking")
	private long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone = "UTC")
	private LocalDateTime timeOfBeginingReservation;
	
	@Column(name = "duration", unique = false, nullable = false)
	private int duration;
	
	@Column(name = "maxNumberOfPerson", unique = false, nullable = false)
	private int maxNumberOfPerson;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<AdditionalServices> additionalServices = new HashSet<AdditionalServices>();
	
	@Column(name = "price", unique = false, nullable = false)
	private double price;
	
	public QuickBooking() {
		// TODO Auto-generated constructor stub
	}

	public QuickBooking(long id, LocalDateTime timeOfBeginingReservation, int duration, int maxNumberOfPerson,  double price) {
		super();
		this.id = id;
		this.timeOfBeginingReservation = timeOfBeginingReservation;
		this.duration = duration;
		this.maxNumberOfPerson = maxNumberOfPerson;
		this.price = price;
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

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getMaxNumberOfPerson() {
		return maxNumberOfPerson;
	}

	public void setMaxNumberOfPerson(int maxNumberOfPerson) {
		this.maxNumberOfPerson = maxNumberOfPerson;
	}

	public Set<AdditionalServices> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(Set<AdditionalServices> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
