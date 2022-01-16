package com.isa.booking_entities.models.users;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isa.booking_entities.models.reports.Report;
import com.isa.booking_entities.models.requests.DeleteAccountRequestResponse;
import com.isa.booking_entities.models.requests.RegistrationRequest;

@Entity
@Table(name = "system_admins")
public class SystemAdmin extends Users {

	@JsonManagedReference
	@OneToMany(mappedBy = "systemAdminWhoReviewRequest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<DeleteAccountRequestResponse> deleteAccountRequestResponses = new HashSet<DeleteAccountRequestResponse>();

	@JsonManagedReference
	@OneToMany(mappedBy = "systemAdminWhoReviewRegistrationRequest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<RegistrationRequest> registrationRequests = new HashSet<RegistrationRequest>();

	@JsonManagedReference
	@OneToMany(mappedBy = "adminWhoReviewsReport", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Report> reports = new HashSet<Report>();

	public SystemAdmin() {
		// TODO Auto-generated constructor stub
	}

	public Set<DeleteAccountRequestResponse> getDeleteAccountRequestResponses() {
		return deleteAccountRequestResponses;
	}

	public void setDeleteAccountRequestResponses(Set<DeleteAccountRequestResponse> deleteAccountRequestResponses) {
		this.deleteAccountRequestResponses = deleteAccountRequestResponses;
	}

	public Set<RegistrationRequest> getRegistrationRequests() {
		return registrationRequests;
	}

	public void setRegistrationRequests(Set<RegistrationRequest> registrationRequests) {
		this.registrationRequests = registrationRequests;
	}

	public Set<Report> getReports() {
		return reports;
	}

	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}

}
