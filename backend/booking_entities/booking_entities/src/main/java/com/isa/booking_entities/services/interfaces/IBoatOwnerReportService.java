package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.models.reports.BoatOwnerReport;

public interface IBoatOwnerReportService {
	BoatOwnerReport save(BoatOwnerReport boatOwnerReport);
	BoatOwnerReport getById(long id);
	List<BoatOwnerReport> getReportsForSystemAdmin();
}
