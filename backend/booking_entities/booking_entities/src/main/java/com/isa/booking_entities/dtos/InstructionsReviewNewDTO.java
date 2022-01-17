package com.isa.booking_entities.dtos;

public class InstructionsReviewNewDTO {
	private long instructionsReservationId;
	private String content;
	private int rating;
	private String clientEmail;
	
	public InstructionsReviewNewDTO() {
		// TODO Auto-generated constructor stub
	}

	public InstructionsReviewNewDTO(long instructionsReservationId, String content, int rating, String clientEmail) {
		this.instructionsReservationId = instructionsReservationId;
		this.content = content;
		this.rating = rating;
		this.clientEmail = clientEmail;
	}

	public long getInstructionsReservationId() {
		return instructionsReservationId;
	}

	public void setInstructionsReservationId(long instructionsReservationId) {
		this.instructionsReservationId = instructionsReservationId;
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
