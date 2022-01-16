package com.isa.booking_entities.dtos;

public class InstructorComplaintDTO {
	private long instructorId;
	private String instructorFirstName;
	private String instructorLastName;
	
	public InstructorComplaintDTO() {
		// TODO Auto-generated constructor stub
	}

	public InstructorComplaintDTO(long instructorId, String instructorFirstName, String instructorLastName) {
		this.instructorId = instructorId;
		this.instructorFirstName = instructorFirstName;
		this.instructorLastName = instructorLastName;
	}

	public long getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(long instructorId) {
		this.instructorId = instructorId;
	}

	public String getInstructorFirstName() {
		return instructorFirstName;
	}

	public void setInstructorFirstName(String instructorFirstName) {
		this.instructorFirstName = instructorFirstName;
	}

	public String getInstructorLastName() {
		return instructorLastName;
	}

	public void setInstructorLastName(String instructorLastName) {
		this.instructorLastName = instructorLastName;
	}
	
}
