package com.isa.booking_entities.dtos;

import com.isa.booking_entities.models.Address;

public class UserUpdateDTO {
	private String email;
	private String firstName;
	private String lastName;
	private Address address;
	private String phoneNumber;
	
	public UserUpdateDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserUpdateDTO(String email, String firstName, String lastName,
			Address address, String phoneNumber) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
