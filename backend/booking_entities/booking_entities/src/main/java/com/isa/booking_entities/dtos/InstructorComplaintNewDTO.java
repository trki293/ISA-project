package com.isa.booking_entities.dtos;

public class InstructorComplaintNewDTO {
	private long instructorId;
	private String clientEmail;
	private String text;
	
	public InstructorComplaintNewDTO() {
		// TODO Auto-generated constructor stub
	}

	public InstructorComplaintNewDTO(long instructorId, String clientEmail, String text) {
		this.instructorId = instructorId;
		this.clientEmail = clientEmail;
		this.text = text;
	}

	public long getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(long instructorId) {
		this.instructorId = instructorId;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
