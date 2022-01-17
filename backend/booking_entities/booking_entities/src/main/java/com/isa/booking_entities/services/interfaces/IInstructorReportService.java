package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.models.reports.InstructorReport;

public interface IInstructorReportService {
	InstructorReport save(InstructorReport instructorReport);
	InstructorReport getById(long id);
	List<InstructorReport> getReportsForSystemAdmin();
}
