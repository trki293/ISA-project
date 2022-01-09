package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.complaints.CottageComplaintResponse;

public interface ICottageComplaintResponseRepository extends JpaRepository<CottageComplaintResponse, Long> {

}
