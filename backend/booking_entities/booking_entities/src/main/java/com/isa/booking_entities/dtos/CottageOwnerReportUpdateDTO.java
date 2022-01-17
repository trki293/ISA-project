package com.isa.booking_entities.dtos;

import com.isa.booking_entities.models.reports.StatusOfReport;

public class CottageOwnerReportUpdateDTO {
	private StatusOfReport statusOfReport;
	private long cottageOwnerReportId;
	
	public CottageOwnerReportUpdateDTO() {
		// TODO Auto-generated constructor stub
	}

	public CottageOwnerReportUpdateDTO(StatusOfReport statusOfReport, long cottageOwnerReportId) {
		this.statusOfReport = statusOfReport;
		this.cottageOwnerReportId = cottageOwnerReportId;
	}

	public StatusOfReport getStatusOfReport() {
		return statusOfReport;
	}

	public void setStatusOfReport(StatusOfReport statusOfReport) {
		this.statusOfReport = statusOfReport;
	}

	public long getCottageOwnerReportId() {
		return cottageOwnerReportId;
	}

	public void setCottageOwnerReportId(long cottageOwnerReportId) {
		this.cottageOwnerReportId = cottageOwnerReportId;
	}
	
}
