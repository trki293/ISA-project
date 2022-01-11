package com.isa.booking_entities.dtos;

import com.isa.booking_entities.models.Address;

public class UserCreateDTO {
	private String email;
	private String password;
	private String confirmationPassword;
	private String firstName;
	private String lastName;
	private Address address;
	private String phoneNumber;

	public UserCreateDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserCreateDTO(String email, String password, String confirmationPassword, String firstName, String lastName,
			Address address, String phoneNumber) {
		this.email = email;
		this.password = password;
		this.confirmationPassword = confirmationPassword;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmationPassword() {
		return confirmationPassword;
	}

	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
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
