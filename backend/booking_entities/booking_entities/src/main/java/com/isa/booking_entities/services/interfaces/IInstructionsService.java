package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.dtos.EntitySearchReservationDTO;
import com.isa.booking_entities.dtos.InstructionDisplayDTO;
import com.isa.booking_entities.dtos.InstructionsPreviewDTO;
import com.isa.booking_entities.dtos.InstructionsSearchDTO;
import com.isa.booking_entities.models.entites.Instructions;
import com.isa.booking_entities.models.users.Client;

public interface IInstructionsService {
	Instructions getById(long id);
	Instructions save(Instructions instructions);
	List<InstructionsPreviewDTO> getAllInstructions(InstructionsSearchDTO instructionsSearchDTO);
	List<InstructionDisplayDTO> getAllInstructionsForClient(EntitySearchReservationDTO instructionsSearchReservationDTO, Client client);
	List<String> getAdditionalServicesForInstructions(long id) throws Exception;
	List<InstructionsPreviewDTO> getAllNonDeletedInstructions();
}
