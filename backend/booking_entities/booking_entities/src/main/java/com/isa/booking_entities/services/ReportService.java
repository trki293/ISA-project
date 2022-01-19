package com.isa.booking_entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.models.reports.Report;
import com.isa.booking_entities.repositories.IReportRepository;
import com.isa.booking_entities.services.interfaces.IReportService;

@Service
public class ReportService implements IReportService {

	private IReportRepository iReportRepository;
	
	@Autowired
	public ReportService(IReportRepository iReportRepository) {
		this.iReportRepository = iReportRepository;
	}

	@Override
	public Report save(Report report) {
		return iReportRepository.save(report);
	}

}
