package com.isa.booking_entities.models.reports;

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
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.models.users.SystemAdmin;


@Entity
@Table(name = "reports")
@Inheritance(strategy = JOINED)
public class Report {
	@Id
	@SequenceGenerator(name = "mySeqGenReport", sequenceName = "mySeqReport", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenReport")
	private long id;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Client reportingClient;

	@Column(name = "picturesPaths", unique = false, nullable = true)
	private String content;

	@Enumerated(EnumType.ORDINAL)
	private TypeOfReport typeOfReport;

	@Enumerated(EnumType.ORDINAL)
	private StatusOfReport statusOfReport;

	@Enumerated(EnumType.ORDINAL)
	private ReportingType reportingType;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private SystemAdmin adminWhoReviewsReport;

	public Report() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Client getReportingClient() {
		return reportingClient;
	}

	public void setReportingClient(Client reportingClient) {
		this.reportingClient = reportingClient;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public TypeOfReport getTypeOfReport() {
		return typeOfReport;
	}

	public void setTypeOfReport(TypeOfReport typeOfReport) {
		this.typeOfReport = typeOfReport;
	}

	public StatusOfReport getStatusOfReport() {
		return statusOfReport;
	}

	public void setStatusOfReport(StatusOfReport statusOfReport) {
		this.statusOfReport = statusOfReport;
	}

	public SystemAdmin getAdminWhoReviewsReport() {
		return adminWhoReviewsReport;
	}

	public void setAdminWhoReviewsReport(SystemAdmin adminWhoReviewsReport) {
		this.adminWhoReviewsReport = adminWhoReviewsReport;
	}

	public ReportingType getReportingType() {
		return reportingType;
	}

	public void setReportingType(ReportingType reportingType) {
		this.reportingType = reportingType;
	}

}
