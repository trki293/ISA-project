package com.isa.booking_entities.dtos;

public class SystemParametersNewDTO {
	private double bookingFee;
	private int pointsForClients;
	private int pointsForProviders;
	private int thresholdForSilver;
	private int thresholdForGold;
	private double discountForRegular;
	private double discountForSilver;
	private double discountForGold;
	
	public SystemParametersNewDTO() {
		// TODO Auto-generated constructor stub
	}

	public SystemParametersNewDTO(double bookingFee, int pointsForClients, int pointsForProviders,
			int thresholdForSilver, int thresholdForGold, double discountForRegular, double discountForSilver,
			double discountForGold) {
		this.bookingFee = bookingFee;
		this.pointsForClients = pointsForClients;
		this.pointsForProviders = pointsForProviders;
		this.thresholdForSilver = thresholdForSilver;
		this.thresholdForGold = thresholdForGold;
		this.discountForRegular = discountForRegular;
		this.discountForSilver = discountForSilver;
		this.discountForGold = discountForGold;
	}

	public double getBookingFee() {
		return bookingFee;
	}

	public void setBookingFee(double bookingFee) {
		this.bookingFee = bookingFee;
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
