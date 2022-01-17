package com.isa.booking_entities.dtos;

public class BoatReviewNewDTO {
	private long boatReservationId;
	private String content;
	private int rating;
	private String clientEmail;

	public BoatReviewNewDTO() {
		// TODO Auto-generated constructor stub
	}

	public BoatReviewNewDTO(long boatReservationId, String content, int rating, String clientEmail) {
		this.boatReservationId = boatReservationId;
		this.content = content;
		this.rating = rating;
		this.clientEmail = clientEmail;
	}

	public long getBoatReservationId() {
		return boatReservationId;
	}

	public void setBoatReservationId(long boatReservationId) {
		this.boatReservationId = boatReservationId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

}
