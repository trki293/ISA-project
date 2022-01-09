package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.reservations.CottageReservation;

public interface ICottageReservationRespository extends JpaRepository<CottageReservation, Long> {

}
