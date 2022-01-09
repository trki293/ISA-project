package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.complaints.ComplaintResponse;

public interface IComplaintResponseRepository extends JpaRepository<ComplaintResponse, Long> {

}
