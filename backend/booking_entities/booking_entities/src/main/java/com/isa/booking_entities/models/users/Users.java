package com.isa.booking_entities.models.users;

import static javax.persistence.InheritanceType.JOINED;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.isa.booking_entities.models.Address;

@Entity
@Table(name = "users")
@Inheritance(strategy = JOINED)
public class Users {
	
	@Id
	@SequenceGenerator(name = "mySeqGenUsers", sequenceName = "mySeqUsers", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenUsers")
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
	
	//Will be used when checking whether the user is confirmed - login
	@Column(name = "enabledLogin", unique = false, nullable = false)
	private boolean enabledLogin;
	
	public Users() {
		// TODO Auto-generated constructor stub
	}

	public Users(long id, String email, String password, String firstName, String lastName, String phoneNumber,
			Address residentalAddress, TypeOfUser typeOfUser) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.residentalAddress = residentalAddress;
		this.typeOfUser = typeOfUser;
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
	
}
