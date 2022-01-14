package com.isa.booking_entities.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.converter.InstructionsReservationDTOConverter;
import com.isa.booking_entities.dtos.InstructionsReservationHistoryDTO;
import com.isa.booking_entities.models.reservations.InstructionsReservation;
import com.isa.booking_entities.models.reservations.StatusOfReservation;
import com.isa.booking_entities.repositories.IInstructionsReservationRepository;
import com.isa.booking_entities.services.interfaces.IInstructionsReservationService;

@Service
public class InstructionsReservationService implements IInstructionsReservationService {

	private IInstructionsReservationRepository iInstructionsReservationRepository;

	private InstructionsReservationDTOConverter instructionsReservationDTOConverter;

	@Autowired
	public InstructionsReservationService(IInstructionsReservationRepository iInstructionsReservationRepository) {
		this.iInstructionsReservationRepository = iInstructionsReservationRepository;
		this.instructionsReservationDTOConverter = new InstructionsReservationDTOConverter();
	}

	@Override
	public InstructionsReservation getById(long id) {
		return iInstructionsReservationRepository.findById(id).orElse(null);
	}

	@Override
	public InstructionsReservation save(InstructionsReservation instructionsReservation) {
		return iInstructionsReservationRepository.save(instructionsReservation);
	}

	@Override
	public List<InstructionsReservationHistoryDTO> getHistoryOfInstructionsReservations(String emailOfClient) {
		return instructionsReservationDTOConverter.convertListInstructionsReservationToListInstructionsReservationHistoryDTO(
				iInstructionsReservationRepository.findAll().stream().filter(instructionsReservationIt -> 
				instructionsReservationIt.getClientForReservation().getEmail().equals(emailOfClient)
						&& (instructionsReservationIt.getStatusOfReservation() == StatusOfReservation.CANCELED
								|| instructionsReservationIt.getStatusOfReservation() == StatusOfReservation.FINISHED
								|| instructionsReservationIt.getStatusOfReservation() == StatusOfReservation.MISSED
								|| instructionsReservationIt.getTimeOfEndingReservation().isBefore(LocalDateTime.now())))
						.collect(Collectors.toList()));
	}
}
