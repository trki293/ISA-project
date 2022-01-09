package com.isa.booking_entities.models.requests;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.Address;
import com.isa.booking_entities.models.users.SystemAdmin;
import com.isa.booking_entities.models.users.TypeOfUser;

@Entity
@Table(name = "registration_requests")
public class RegistrationRequest {
	@Id
	@SequenceGenerator(name = "mySeqGenRegistrationRequest", sequenceName = "mySeqRegistrationRequest", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenRegistrationRequest")
	private long id;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "password", unique = false, nullable = false)
	private String password;

	@Column(name = "firstName", unique = false, nullable = false)
	private String firstName;

	@Column(name = "lastName", unique = false, nullable = false)
	private String lastName;

	@Column(name = "phoneNumber", unique = false, nullable = false)
	private String phoneNumber;

	private Address residentalAddress;

	@Enumerated(EnumType.ORDINAL)
	private TypeOfUser typeOfUser;

	@Column(name = "registrationReason", unique = false, nullable = false)
	private String registrationReason;

	@Enumerated(EnumType.ORDINAL)
	private StateOfRequest stateOfRequest;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private SystemAdmin systemAdminWhoReviewRegistrationRequest;

	public RegistrationRequest() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getResidentalAddress() {
		return residentalAddress;
	}

	public void setResidentalAddress(Address residentalAddress) {
		this.residentalAddress = residentalAddress;
	}

	public TypeOfUser getTypeOfUser() {
		return typeOfUser;
	}

	public void setTypeOfUser(TypeOfUser typeOfUser) {
		this.typeOfUser = typeOfUser;
	}

	public String getRegistrationReason() {
		return registrationReason;
	}

	public void setRegistrationReason(String registrationReason) {
		this.registrationReason = registrationReason;
	}

	public StateOfRequest getStateOfRequest() {
		return stateOfRequest;
	}

	public void setStateOfRequest(StateOfRequest stateOfRequest) {
		this.stateOfRequest = stateOfRequest;
	}

	public SystemAdmin getSystemAdminWhoReviewRegistrationRequest() {
		return systemAdminWhoReviewRegistrationRequest;
	}

	public void setSystemAdminWhoReviewRegistrationRequest(SystemAdmin systemAdminWhoReviewRegistrationRequest) {
		this.systemAdminWhoReviewRegistrationRequest = systemAdminWhoReviewRegistrationRequest;
	}

}
