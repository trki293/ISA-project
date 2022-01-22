package com.isa.booking_entities.models.users;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isa.booking_entities.models.complaints.InstructionsComplaint;
import com.isa.booking_entities.models.entites.Instructions;
import com.isa.booking_entities.models.rating.InstructionsReview;
import com.isa.booking_entities.models.reports.InstructorReport;

@Entity
@Table(name="instructors")
public class Instructor extends Users {

	@Column(name = "loyaltyPoints", unique = false, nullable = false)
	private long loyaltyPoints;
	
	@Enumerated(EnumType.ORDINAL)
	private StatusOfUser statuseOfUser;
	
	@JsonManagedReference(value="instructor-instructions")
	@OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Instructions>  instructions= new HashSet<Instructions>();

	@JsonManagedReference(value="instructor-report")
	@OneToMany(mappedBy = "instructorWhoCreateReport", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<InstructorReport>  instructorReports= new HashSet<InstructorReport>();
	
	@JsonManagedReference(value="instructor-complaint")
	@OneToMany(mappedBy = "instructorForComplaint", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<InstructionsComplaint> instructorComplaints = new HashSet<InstructionsComplaint>();
	
	public Instructor() {
		// TODO Auto-generated constructor stub
	}

	public long getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(long loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

	public StatusOfUser getStatuseOfUser() {
		return statuseOfUser;
	}

	public void setStatuseOfUser(StatusOfUser statuseOfUser) {
		this.statuseOfUser = statuseOfUser;
	}

	public Set<Instructions> getInstructions() {
		return instructions;
	}

	public void setInstructions(Set<Instructions> instructions) {
		this.instructions = instructions;
	}

	public Set<InstructorReport> getInstructorReports() {
		return instructorReports;
	}

	public void setInstructorReports(Set<InstructorReport> instructorReports) {
		this.instructorReports = instructorReports;
	}

	public Set<InstructionsComplaint> getInstructorComplaints() {
		return instructorComplaints;
	}

	public void setInstructorComplaints(Set<InstructionsComplaint> instructorComplaints) {
		this.instructorComplaints = instructorComplaints;
	}
	
}
