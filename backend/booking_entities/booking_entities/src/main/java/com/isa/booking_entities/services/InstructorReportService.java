package com.isa.booking_entities.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.models.reports.InstructorReport;
import com.isa.booking_entities.models.reports.StatusOfReport;
import com.isa.booking_entities.models.reports.TypeOfReport;
import com.isa.booking_entities.repositories.IInstructorReportRepository;
import com.isa.booking_entities.services.interfaces.IInstructorReportService;

@Service
public class InstructorReportService implements IInstructorReportService {
	
	private IInstructorReportRepository iInstructorReportRepository;
	
	@Autowired
	public InstructorReportService(IInstructorReportRepository iInstructorReportRepository) {
		this.iInstructorReportRepository = iInstructorReportRepository;
	}

	@Override
	public InstructorReport save(InstructorReport instructorReport) {
		return iInstructorReportRepository.save(instructorReport);
	}

	@Override
	public InstructorReport getById(long id) {
		return iInstructorReportRepository.findById(id).orElse(null);
	}

	@Override
	public List<InstructorReport> getReportsForSystemAdmin() {
		return iInstructorReportRepository.findAll().stream().filter(instructorReportIt -> instructorReportIt.getTypeOfReport()==TypeOfReport.THE_CLIENT_SHOULD_BE_SANCTIONED && instructorReportIt.getStatusOfReport()==StatusOfReport.CREATED).collect(Collectors.toList());
	}

}
