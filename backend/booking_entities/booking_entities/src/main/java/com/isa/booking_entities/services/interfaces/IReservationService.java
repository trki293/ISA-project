package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.dtos.ReservationFutureDisplayDTO;
import com.isa.booking_entities.models.reservations.Reservation;

public interface IReservationService {
	Reservation getById(long id);
	Reservation save(Reservation reservation);
	List<ReservationFutureDisplayDTO> getFutureReservationsForClient(String emailOfClient);
}
