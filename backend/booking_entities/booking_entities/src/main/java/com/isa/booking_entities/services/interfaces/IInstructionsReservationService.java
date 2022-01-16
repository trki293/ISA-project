package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.dtos.InstructionsReservationHistoryDTO;
import com.isa.booking_entities.dtos.InstructionsReservationNewDTO;
import com.isa.booking_entities.models.entites.Instructions;
import com.isa.booking_entities.models.reservations.InstructionsReservation;
import com.isa.booking_entities.models.users.Client;

public interface IInstructionsReservationService {
	InstructionsReservation getById(long id);
	InstructionsReservation save(InstructionsReservation instructionsReservation);
	List<InstructionsReservationHistoryDTO> getHistoryOfInstructionsReservations(String emailOfClient);
	InstructionsReservation createReservation(InstructionsReservationNewDTO instructionsReservationNewDTO,
			Instructions instructions, Client client);
	List<InstructionsReservation> getHistoryInstructionsReservationsForClient(Client client);
}
