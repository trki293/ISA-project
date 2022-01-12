package com.isa.booking_entities.models.users;

import static javax.persistence.InheritanceType.JOINED;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isa.booking_entities.models.Address;
import com.isa.booking_entities.models.requests.DeleteAccountRequest;

@Entity
@Table(name = "users")
@Inheritance(strategy = JOINED)
public class Users implements UserDetails  {

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

	// Will be used when checking whether the user is confirmed - login
	@Column(name = "enabledLogin", unique = false, nullable = false)
	private boolean enabledLogin;

	@JsonManagedReference
	@OneToMany(mappedBy = "userWhoDeleteAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<DeleteAccountRequest> deleteAccountRequests = new HashSet<DeleteAccountRequest>();

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Authority> authorities;
	
	@Column(name = "deleted", unique = false, nullable = false)
	private Boolean deleted;
	
	public Users() {
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

	public boolean isEnabledLogin() {
		return enabledLogin;
	}

	public void setEnabledLogin(boolean enabledLogin) {
		this.enabledLogin = enabledLogin;
	}

	public Set<DeleteAccountRequest> getDeleteAccountRequests() {
		return deleteAccountRequests;
	}

	public void setDeleteAccountRequests(Set<DeleteAccountRequest> deleteAccountRequests) {
		this.deleteAccountRequests = deleteAccountRequests;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabledLogin;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}
