package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.dtos.InstructionsPreviewDTO;
import com.isa.booking_entities.dtos.InstructionsSearchDTO;
import com.isa.booking_entities.models.entites.Instructions;

public interface IInstructionsService {
	Instructions getById(long id);
	Instructions save(Instructions instructions);
	List<InstructionsPreviewDTO> getAllInstructions(InstructionsSearchDTO instructionsSearchDTO);
}
