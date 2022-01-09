package com.isa.booking_entities.models.requests;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.users.SystemAdmin;
import com.isa.booking_entities.models.users.Users;

public class DeleteAccountRequest {
	@Id
	@SequenceGenerator(name = "mySeqGenDeleteAccountRequest", sequenceName = "mySeqDeleteAccountRequest", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenDeleteAccountRequest")
	private long id;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Users userWhoDeleteAccount;

	@Column(name = "text", unique = false, nullable = false)
	private String text;

	@Enumerated(EnumType.ORDINAL)
	private StateOfDeleteAccountRequest stateOfRequest;

	public DeleteAccountRequest() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Users getUserWhoDeleteAccount() {
		return userWhoDeleteAccount;
	}

	public void setUserWhoDeleteAccount(Users userWhoDeleteAccount) {
		this.userWhoDeleteAccount = userWhoDeleteAccount;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public StateOfDeleteAccountRequest getStateOfRequest() {
		return stateOfRequest;
	}

	public void setStateOfRequest(StateOfDeleteAccountRequest stateOfRequest) {
		this.stateOfRequest = stateOfRequest;
	}

}
