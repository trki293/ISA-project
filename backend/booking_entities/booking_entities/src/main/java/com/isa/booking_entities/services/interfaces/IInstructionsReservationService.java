package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.dtos.InstructionsReservationHistoryDTO;
import com.isa.booking_entities.models.reservations.InstructionsReservation;

public interface IInstructionsReservationService {
	InstructionsReservation getById(long id);
	InstructionsReservation save(InstructionsReservation instructionsReservation);
	List<InstructionsReservationHistoryDTO> getHistoryOfInstructionsReservations(String emailOfClient);
}
