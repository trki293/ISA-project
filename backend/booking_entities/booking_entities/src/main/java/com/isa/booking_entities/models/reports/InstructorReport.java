package com.isa.booking_entities.models.reports;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.users.Instructor;

@Entity
@Table(name = "instructor_reports")
public class InstructorReport extends Report{
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Instructor instructorWhoCreateReport;
	
	public InstructorReport() {
		// TODO Auto-generated constructor stub
	}

	public Instructor getInstructorWhoCreateReport() {
		return instructorWhoCreateReport;
	}

	public void setInstructorWhoCreateReport(Instructor instructorWhoCreateReport) {
		this.instructorWhoCreateReport = instructorWhoCreateReport;
	}
	
}
