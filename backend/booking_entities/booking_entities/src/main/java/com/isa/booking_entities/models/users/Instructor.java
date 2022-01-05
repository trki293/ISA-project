package com.isa.booking_entities.models.users;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isa.booking_entities.models.InstructorReport;
import com.isa.booking_entities.models.rating.InstructionsReview;

@Entity
@Table(name="instructors")
public class Instructor extends Users {
	
	@JsonManagedReference
	@OneToMany(mappedBy = "instructorForReview", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<InstructionsReview> instructorReviews = new HashSet<InstructionsReview>();

	@JsonManagedReference
	@OneToMany(mappedBy = "instructorWhoCreateReport", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<InstructorReport>  instructorReports= new HashSet<InstructorReport>();

}
