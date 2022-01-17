package com.isa.booking_entities.dtos;

import com.isa.booking_entities.models.reports.StatusOfReport;

public class InstructorReportUpdateDTO {
	private StatusOfReport statusOfReport;
	private long instructorReportId;
	
	public InstructorReportUpdateDTO() {
		// TODO Auto-generated constructor stub
	}

	public InstructorReportUpdateDTO(StatusOfReport statusOfReport, long instructorReportId) {
		this.statusOfReport = statusOfReport;
		this.instructorReportId = instructorReportId;
	}

	public StatusOfReport getStatusOfReport() {
		return statusOfReport;
	}

	public void setStatusOfReport(StatusOfReport statusOfReport) {
		this.statusOfReport = statusOfReport;
	}

	public long getInstructorReportId() {
		return instructorReportId;
	}

	public void setInstructorReportId(long instructorReportId) {
		this.instructorReportId = instructorReportId;
	}

}
