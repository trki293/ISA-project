package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.reservations.InstructionsQuickBooking;

public interface IInstructionsQuickBookingRepository extends JpaRepository<InstructionsQuickBooking, Long> {

}
