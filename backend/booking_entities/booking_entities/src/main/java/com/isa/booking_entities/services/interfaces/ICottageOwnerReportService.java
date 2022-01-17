package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.models.reports.CottageOwnerReport;

public interface ICottageOwnerReportService {
	CottageOwnerReport save(CottageOwnerReport cottageOwnerReport);
	CottageOwnerReport getById(long id);
	List<CottageOwnerReport> getReportsForSystemAdmin();
}
