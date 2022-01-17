package com.isa.booking_entities.dtos;

import com.isa.booking_entities.models.reports.StatusOfReport;

public class BoatOwnerReportUpdateDTO {
	private StatusOfReport statusOfReport;
	private long boatOwnerReportId;
	
	public BoatOwnerReportUpdateDTO() {
		// TODO Auto-generated constructor stub
	}

	public BoatOwnerReportUpdateDTO(StatusOfReport statusOfReport, long boatOwnerReportId) {
		this.statusOfReport = statusOfReport;
		this.boatOwnerReportId = boatOwnerReportId;
	}

	public StatusOfReport getStatusOfReport() {
		return statusOfReport;
	}

	public void setStatusOfReport(StatusOfReport statusOfReport) {
		this.statusOfReport = statusOfReport;
	}

	public long getBoatOwnerReportId() {
		return boatOwnerReportId;
	}

	public void setBoatOwnerReportId(long boatOwnerReportId) {
		this.boatOwnerReportId = boatOwnerReportId;
	}
	
}
