package com.isa.booking_entities.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "system_parameters")
public class SystemParameters {
	@Id
	@SequenceGenerator(name = "mySeqGenSystemParameters", sequenceName = "mySeqSystemParameters", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenSystemParameters")
	private long id;
	
	@Column(name = "bookingFee", unique = false, nullable = false)
	private double bookingFee;
	
	@Column(name = "incomeFromReservations", unique = false, nullable = false)
	private double incomeFromReservations;
	
	@Column(name = "pointsForClients", unique = false, nullable = false)
	private int pointsForClients;
	
	@Column(name = "pointsForProviders", unique = false, nullable = false)
	private int pointsForProviders;
	
	@Column(name = "thresholdForSilver", unique = false, nullable = false)
	private int thresholdForSilver;
	
	@Column(name = "thresholdForGold", unique = false, nullable = false)
	private int thresholdForGold;
	
	@Column(name = "discountForRegular", unique = false, nullable = false)
	private double discountForRegular;
	
	@Column(name = "discountForSilver", unique = false, nullable = false)
	private double discountForSilver;
	
	private double discountForGold;
	
	public SystemParameters() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getBookingFee() {
		return bookingFee;
	}

	public void setBookingFee(double bookingFee) {
		this.bookingFee = bookingFee;
	}

	public double getIncomeFromReservations() {
		return incomeFromReservations;
	}

	public void setIncomeFromReservations(double incomeFromReservations) {
		this.incomeFromReservations = incomeFromReservations;
	}

	public int getPointsForClients() {
		return pointsForClients;
	}

	public void setPointsForClients(int pointsForClients) {
		this.pointsForClients = pointsForClients;
	}

	public int getPointsForProviders() {
		return pointsForProviders;
	}

	public void setPointsForProviders(int pointsForProviders) {
		this.pointsForProviders = pointsForProviders;
	}

	public int getThresholdForSilver() {
		return thresholdForSilver;
	}

	public void setThresholdForSilver(int thresholdForSilver) {
		this.thresholdForSilver = thresholdForSilver;
	}

	public int getThresholdForGold() {
		return thresholdForGold;
	}

	public void setThresholdForGold(int thresholdForGold) {
		this.thresholdForGold = thresholdForGold;
	}

	public double getDiscountForRegular() {
		return discountForRegular;
	}

	public void setDiscountForRegular(double discountForRegular) {
		this.discountForRegular = discountForRegular;
	}

	public double getDiscountForSilver() {
		return discountForSilver;
	}

	public void setDiscountForSilver(double discountForSilver) {
		this.discountForSilver = discountForSilver;
	}

	public double getDiscountForGold() {
		return discountForGold;
	}

	public void setDiscountForGold(double discountForGold) {
		this.discountForGold = discountForGold;
	}
	
}
