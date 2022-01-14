package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.dtos.BoatReservationHistoryDTO;
import com.isa.booking_entities.dtos.BoatReservationNewDTO;
import com.isa.booking_entities.models.entites.Boat;
import com.isa.booking_entities.models.reservations.BoatReservation;
import com.isa.booking_entities.models.users.Client;

public interface IBoatReservationService {
	BoatReservation getById(long id);
	BoatReservation save(BoatReservation boatReservation);
	List<BoatReservationHistoryDTO> getHistoryOfBoatReservations(String emailOfClient);
	BoatReservation createReservation(BoatReservationNewDTO boatReservationNewDTO, Boat boatForReservation, Client clientForReservation);
}
