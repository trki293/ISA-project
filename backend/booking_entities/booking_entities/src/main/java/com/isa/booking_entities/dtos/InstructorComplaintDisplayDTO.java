package com.isa.booking_entities.dtos;

import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.models.users.Instructor;

public class InstructorComplaintDisplayDTO {
	private long id;
	private String clientFirstName;
	private String clientLastName;
	private String instructorFirstName;
	private String instructorLastName;
	
	public InstructorComplaintDisplayDTO() {
		// TODO Auto-generated constructor stub
	}

	public InstructorComplaintDisplayDTO(long id, Instructor instructor, Client client) {
		this.id = id;
		this.clientFirstName = client.getFirstName();
		this.clientLastName = client.getLastName();
		this.instructorFirstName = instructor.getFirstName();
		this.instructorLastName = instructor.getLastName();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClientFirstName() {
		return clientFirstName;
	}

	public void setClientFirstName(String clientFirstName) {
		this.clientFirstName = clientFirstName;
	}

	public String getClientLastName() {
		return clientLastName;
	}

	public void setClientLastName(String clientLastName) {
		this.clientLastName = clientLastName;
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
