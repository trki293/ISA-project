package com.isa.booking_entities.models.reservations;

import static javax.persistence.InheritanceType.JOINED;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "availability_periods")
@Inheritance(strategy = JOINED)
public class AvailabilityPeriod {
	@Id
	@SequenceGenerator(name = "mySeqGenQuickBooking", sequenceName = "mySeqQuickBooking", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenQuickBooking")
	private long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone = "UTC")
	private LocalDateTime beginPeriod;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone = "UTC")
	private LocalDateTime endPeriod;
	
	public AvailabilityPeriod() {
		// TODO Auto-generated constructor stub
	}

	public AvailabilityPeriod(long id, LocalDateTime beginPeriod, LocalDateTime endPeriod) {
		this.id = id;
		this.beginPeriod = beginPeriod;
		this.endPeriod = endPeriod;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getBeginPeriod() {
		return beginPeriod;
	}

	public void setBeginPeriod(LocalDateTime beginPeriod) {
		this.beginPeriod = beginPeriod;
	}

	public LocalDateTime getEndPeriod() {
		return endPeriod;
	}

	public void setEndPeriod(LocalDateTime endPeriod) {
		this.endPeriod = endPeriod;
	}
	
}
