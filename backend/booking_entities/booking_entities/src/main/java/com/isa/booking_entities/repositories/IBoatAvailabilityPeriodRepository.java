package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.reservations.BoatAvailabilityPeriod;

public interface IBoatAvailabilityPeriodRepository extends JpaRepository<BoatAvailabilityPeriod, Long> {

}
