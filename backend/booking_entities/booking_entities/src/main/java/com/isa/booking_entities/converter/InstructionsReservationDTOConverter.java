package com.isa.booking_entities.converter;

import java.util.ArrayList;
import java.util.List;

import com.isa.booking_entities.dtos.InstructionsReservationHistoryDTO;
import com.isa.booking_entities.models.reservations.InstructionsReservation;

public class InstructionsReservationDTOConverter {
	public InstructionsReservationDTOConverter() {
		// TODO Auto-generated constructor stub
	}

	public List<InstructionsReservationHistoryDTO> convertListInstructionsReservationToListInstructionsReservationHistoryDTO(
			List<InstructionsReservation> instructionsReservations) {
		List<InstructionsReservationHistoryDTO> instructionsReservationHistoryDTOs = new ArrayList<InstructionsReservationHistoryDTO>();
		for (InstructionsReservation instructionsReservationIt : instructionsReservations) {
			instructionsReservationHistoryDTOs
					.add(new InstructionsReservationHistoryDTO(instructionsReservationIt.getId(),instructionsReservationIt.getTimeOfBeginingReservation(),
							instructionsReservationIt.getTimeOfEndingReservation(), instructionsReservationIt.getInstructionsForReservation(),
							instructionsReservationIt.getTotalPrice(), instructionsReservationIt.getStatusOfReservation()));
		}
		return instructionsReservationHistoryDTOs;
	}
}
