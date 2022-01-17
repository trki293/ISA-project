package com.isa.booking_entities.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.models.reports.CottageOwnerReport;
import com.isa.booking_entities.models.reports.StatusOfReport;
import com.isa.booking_entities.models.reports.TypeOfReport;
import com.isa.booking_entities.repositories.ICottageOwnerReportRepository;
import com.isa.booking_entities.services.interfaces.ICottageOwnerReportService;

@Service
public class CottageOwnerReportService implements ICottageOwnerReportService {

	private ICottageOwnerReportRepository iCottageOwnerReportRepository;
	
	@Autowired
	public CottageOwnerReportService(ICottageOwnerReportRepository iCottageOwnerReportRepository) {
		this.iCottageOwnerReportRepository = iCottageOwnerReportRepository;
	}

	@Override
	public CottageOwnerReport save(CottageOwnerReport cottageOwnerReport) {
		return iCottageOwnerReportRepository.save(cottageOwnerReport);
	}

	@Override
	public CottageOwnerReport getById(long id) {
		return iCottageOwnerReportRepository.findById(id).orElse(null);
	}

	@Override
	public List<CottageOwnerReport> getReportsForSystemAdmin() {
		return iCottageOwnerReportRepository.findAll().stream().filter(cottageOwnerReportIt -> cottageOwnerReportIt.getTypeOfReport()==TypeOfReport.THE_CLIENT_SHOULD_BE_SANCTIONED && cottageOwnerReportIt.getStatusOfReport()==StatusOfReport.CREATED).collect(Collectors.toList());
	}

}
