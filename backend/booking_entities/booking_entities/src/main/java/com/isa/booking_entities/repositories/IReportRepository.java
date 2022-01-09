package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.reports.Report;

public interface IReportRepository extends JpaRepository<Report, Long> {

}
