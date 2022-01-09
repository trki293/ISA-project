package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.reports.CottageOwnerReport;

public interface ICottageOwnerReportRepository extends JpaRepository<CottageOwnerReport, Long> {

}
