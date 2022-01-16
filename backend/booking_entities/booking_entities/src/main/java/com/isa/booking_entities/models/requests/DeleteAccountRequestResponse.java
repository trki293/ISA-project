package com.isa.booking_entities.models.requests;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.users.SystemAdmin;

@Entity
@Table(name = "delete_account_request_responses")
public class DeleteAccountRequestResponse {
	
	@Id
	@SequenceGenerator(name = "mySeqGenDeleteAccountRequestResponse", sequenceName = "mySeqDeleteAccountRequestResponse", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenDeleteAccountRequestResponse")
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private DeleteAccountRequest deleteAccountRequest;
	
	@Column(name = "text", unique = false, nullable = false)
	private String text;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) 
	private SystemAdmin systemAdminWhoReviewRequest; 
	
	public DeleteAccountRequestResponse() {
		// TODO Auto-generated constructor stub
	}

	public DeleteAccountRequest getDeleteAccountRequest() {
		return deleteAccountRequest;
	}

	public void setDeleteAccountRequest(DeleteAccountRequest deleteAccountRequest) {
		this.deleteAccountRequest = deleteAccountRequest;
	}

	public SystemAdmin getSystemAdminWhoReviewRequest() {
		return systemAdminWhoReviewRequest;
	}

	public void setSystemAdminWhoReviewRequest(SystemAdmin systemAdminWhoReviewRequest) {
		this.systemAdminWhoReviewRequest = systemAdminWhoReviewRequest;
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
	
}
