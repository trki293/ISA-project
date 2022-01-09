package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.complaints.BoatComplaintResponse;

public interface IBoatComplaintResponseRepository extends JpaRepository<BoatComplaintResponse, Long> {

}
