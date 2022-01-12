package com.isa.booking_entities.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.isa.booking_entities.converter.InstructionsPreviewDTOConverter;
import com.isa.booking_entities.dtos.InstructionsPreviewDTO;
import com.isa.booking_entities.dtos.InstructionsSearchDTO;
import com.isa.booking_entities.models.entites.Instructions;
import com.isa.booking_entities.repositories.IInstructionsRepository;
import com.isa.booking_entities.services.interfaces.IInstructionsService;

public class InstructionsService implements IInstructionsService {

	private IInstructionsRepository iInstructionsRepository;

	private InstructionsPreviewDTOConverter instructionsPreviewDTOConverter;

	@Autowired
	public InstructionsService(IInstructionsRepository iInstructionsRepository) {
		this.iInstructionsRepository = iInstructionsRepository;
		this.instructionsPreviewDTOConverter = new InstructionsPreviewDTOConverter();
	}

	@Override
	public Instructions getById(long id) {
		return iInstructionsRepository.findById(id).orElse(null);
	}

	@Override
	public Instructions save(Instructions instructions) {
		return iInstructionsRepository.save(instructions);
	}

	@Override
	public List<InstructionsPreviewDTO> getAllInstructions(InstructionsSearchDTO instructionsSearchDTO) {
		return instructionsPreviewDTOConverter
				.convertListInstructionsToListInstructionsPreviewDTO(iInstructionsRepository.findAll().stream()
						.filter(instructionsIt -> doesCottageMeetParameters(instructionsIt, instructionsSearchDTO))
						.collect(Collectors.toList()));
	}

	private Boolean doesCottageMeetParameters(Instructions instructions, InstructionsSearchDTO instructionsSearchDTO) {
		Boolean returnValueCity = checkContainsString(instructionsSearchDTO.getCity(),
				instructions.getAddress().getCity());
		Boolean returnValueCountry = checkContainsString(instructionsSearchDTO.getCountry(),
				instructions.getAddress().getCountry());
		Boolean returnValueTitle = checkContainsString(instructionsSearchDTO.getTitle(), instructions.getTitle());
		Boolean returnValueMinPricePerHour = checkMinValueDouble(instructionsSearchDTO.getMinPricePerHour(),
				instructions.getPricePerHour());
		Boolean returnValueMaxPricePerHour = checkMaxValueDouble(instructionsSearchDTO.getMaxPricePerHour(),
				instructions.getPricePerHour());
		Boolean returnValueMinAverageGrade = checkMinValueDouble(instructionsSearchDTO.getMinAverageGrade(),
				instructions.getAverageGrade());
		Boolean returnValueMaxAverageGrade = checkMaxValueDouble(instructionsSearchDTO.getMaxAverageGrade(),
				instructions.getAverageGrade());
		Boolean returnValueMinValueMaxNumberOfPeople = checkMinValueDouble(
				instructionsSearchDTO.getMinValueMaxNumberOfPeople(), instructions.getMaxNumberOfPeople());
		Boolean returnValueMaxValueMaxNumberOfPeople = checkMaxValueDouble(
				instructionsSearchDTO.getMaxValueMaxNumberOfPeople(), instructions.getMaxNumberOfPeople());

		return returnValueMaxAverageGrade && returnValueMinAverageGrade && returnValueMaxPricePerHour
				&& returnValueMinPricePerHour && returnValueMinValueMaxNumberOfPeople
				&& returnValueMaxValueMaxNumberOfPeople && returnValueTitle && returnValueCity && returnValueCountry
				&& !instructions.getDeleted();
	}

	private Boolean checkMinValueDouble(double numberDTO, double numberInstructions) {
		Boolean returnValue = true;
		if (numberDTO != -1) {
			returnValue = numberInstructions >= numberDTO ? true : false;
		}
		return returnValue;
	}

	private Boolean checkMaxValueDouble(double numberDTO, double numberInstructions) {
		Boolean returnValue = true;
		if (numberDTO != -1) {
			returnValue = numberInstructions <= numberDTO ? true : false;
		}
		return returnValue;
	}

	private Boolean checkContainsString(String stringDTO, String stringInstructions) {
		Boolean returnValue = true;
		if (!stringDTO.equals("")) {
			returnValue = stringInstructions.toLowerCase().contains(stringDTO.toLowerCase());
		}
		return returnValue;
	}

}
