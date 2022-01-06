package com.isa.booking_entities.models.complaints;

import static javax.persistence.InheritanceType.JOINED;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.users.SystemAdmin;

@Entity
@Table(name = "complaint_responses")
@Inheritance(strategy = JOINED)
public class ComplaintResponse {
	@Id
	@SequenceGenerator(name = "mySeqGenComplaintResponse", sequenceName = "mySeqComplaintResponse", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenComplaintResponse")
	private long id;
	
	@Column(name="text", unique=false, nullable=false)
	private String text;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private SystemAdmin systemAdminWhoCreateResponse;
	
	@Enumerated(EnumType.ORDINAL)
	private TypeOfComplaintResponse typeOfComplaintResponse;
	
	public ComplaintResponse() {
		// TODO Auto-generated constructor stub
	}

	public ComplaintResponse(long id, String text, SystemAdmin systemAdminWhoCreateResponse) {
		this.id = id;
		this.text = text;
		this.systemAdminWhoCreateResponse = systemAdminWhoCreateResponse;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public SystemAdmin getSystemAdminWhoCreateResponse() {
		return systemAdminWhoCreateResponse;
	}

	public void setSystemAdminWhoCreateResponse(SystemAdmin systemAdminWhoCreateResponse) {
		this.systemAdminWhoCreateResponse = systemAdminWhoCreateResponse;
	}
	
}
