package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.reservations.Reservation;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {

}
