package com.isa.booking_entities.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.models.reports.BoatOwnerReport;
import com.isa.booking_entities.models.reports.StatusOfReport;
import com.isa.booking_entities.models.reports.TypeOfReport;
import com.isa.booking_entities.repositories.IBoatOwnerReportRepository;
import com.isa.booking_entities.services.interfaces.IBoatOwnerReportService;


@Service
public class BoatOwnerReportService implements IBoatOwnerReportService {

	private IBoatOwnerReportRepository iBoatOwnerReportRepository;
	
	@Autowired
	public BoatOwnerReportService(IBoatOwnerReportRepository iBoatOwnerReportRepository) {
		this.iBoatOwnerReportRepository = iBoatOwnerReportRepository;
	}

	@Override
	public BoatOwnerReport save(BoatOwnerReport boatOwnerReport) {
		return iBoatOwnerReportRepository.save(boatOwnerReport);
	}

	@Override
	public BoatOwnerReport getById(long id) {
		return iBoatOwnerReportRepository.findById(id).orElse(null);
	}

	@Override
	public List<BoatOwnerReport> getReportsForSystemAdmin() {
		return iBoatOwnerReportRepository.findAll().stream().filter(boatOwnerReportIt -> boatOwnerReportIt.getTypeOfReport()==TypeOfReport.THE_CLIENT_SHOULD_BE_SANCTIONED && boatOwnerReportIt.getStatusOfReport()==StatusOfReport.CREATED).collect(Collectors.toList());
	}

}
