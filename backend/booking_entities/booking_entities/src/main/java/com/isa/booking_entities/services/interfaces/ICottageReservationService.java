package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.dtos.CottageReservationHistoryDTO;
import com.isa.booking_entities.models.reservations.CottageReservation;

public interface ICottageReservationService {
	CottageReservation getById(long id);
	CottageReservation save(CottageReservation cottageReservation);
	List<CottageReservationHistoryDTO> getHistoryOfCottageReservations(String emailOfClient);
}
