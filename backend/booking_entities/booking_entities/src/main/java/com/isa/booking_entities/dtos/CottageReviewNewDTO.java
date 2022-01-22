package com.isa.booking_entities.dtos;

public class CottageReviewNewDTO {
	private long cottageReservationId;
	private String content;
	private int rating;
	private String clientEmail;
	
	public CottageReviewNewDTO() {
		// TODO Auto-generated constructor stub
	}

	public CottageReviewNewDTO(long cottageReservationId, String content, int rating, String clientEmail) {
		this.cottageReservationId = cottageReservationId;
		this.content = content;
		this.rating = rating;
		this.clientEmail = clientEmail;
	}
  
	public long getCottageReservationId() {
		return cottageReservationId;
	}

	public void setCottageReservationId(long cottageReservationId) {
		this.cottageReservationId = cottageReservationId;
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
