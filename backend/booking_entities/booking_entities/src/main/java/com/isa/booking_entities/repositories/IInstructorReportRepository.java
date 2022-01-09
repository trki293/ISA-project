package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.reports.InstructorReport;

public interface IInstructorReportRepository extends JpaRepository<InstructorReport, Long> {

}
