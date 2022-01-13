package com.isa.booking_entities.converter;

import java.util.ArrayList;
import java.util.List;

import com.isa.booking_entities.dtos.InstructionDisplayDTO;
import com.isa.booking_entities.dtos.InstructionsPreviewDTO;
import com.isa.booking_entities.models.entites.Instructions;

public class InstructionsPreviewDTOConverter {
	
	public InstructionsPreviewDTOConverter() {
		// TODO Auto-generated constructor stub
	}
	
	public List<InstructionsPreviewDTO> convertListInstructionsToListInstructionsPreviewDTO(List<Instructions> instructions){
		List<InstructionsPreviewDTO> instructionsPreviewDTOs = new ArrayList<InstructionsPreviewDTO>();
		for (Instructions instructionsIt : instructions) {
			instructionsPreviewDTOs.add(new InstructionsPreviewDTO(instructionsIt.getId(),instructionsIt.getTitle(), instructionsIt.getAddress(), instructionsIt.getPromotionalDescription(), instructionsIt.getInstructorBiography(), instructionsIt.getAverageGrade()));
		}
		return instructionsPreviewDTOs;
	}
	
	public List<InstructionDisplayDTO> convertListInstructionsToListInstructionDisplayDTO(List<Instructions> instructions){
		List<InstructionDisplayDTO> instructionDisplayDTOs = new ArrayList<InstructionDisplayDTO>();
		for (Instructions instructionsIt : instructions) {
			instructionDisplayDTOs.add(new InstructionDisplayDTO(instructionsIt.getId(),instructionsIt.getTitle(),instructionsIt.getAverageGrade(),instructionsIt.getPricePerHour(),instructionsIt.getAddress(),instructionsIt.getMaxNumberOfPeople()));
		}
		return instructionDisplayDTOs;
	}
}
