package com.isa.booking_entities.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.converter.CottageReservationDTOConverter;
import com.isa.booking_entities.dtos.CottageReservationHistoryDTO;
import com.isa.booking_entities.models.reservations.CottageReservation;
import com.isa.booking_entities.models.reservations.StatusOfReservation;
import com.isa.booking_entities.repositories.ICottageReservationRepository;
import com.isa.booking_entities.services.interfaces.ICottageReservationService;

@Service
public class CottageReservationService implements ICottageReservationService {
	
	private ICottageReservationRepository iCottageReservationRepository;

	private CottageReservationDTOConverter cottageReservationDTOConverter;

	@Autowired
	public CottageReservationService(ICottageReservationRepository iCottageReservationRepository) {
		this.iCottageReservationRepository = iCottageReservationRepository;
		this.cottageReservationDTOConverter = new CottageReservationDTOConverter();
	}

	@Override
	public CottageReservation getById(long id) {
		return iCottageReservationRepository.findById(id).orElse(null);
	}

	@Override
	public CottageReservation save(CottageReservation cottageReservation) {
		return iCottageReservationRepository.save(cottageReservation);
	}

	@Override
	public List<CottageReservationHistoryDTO> getHistoryOfCottageReservations(String emailOfClient) {
		return cottageReservationDTOConverter.convertListCottageReservationToListCottageReservationHistoryDTO(
				iCottageReservationRepository.findAll().stream().filter(cottageReservationIt -> 
				cottageReservationIt.getClientForReservation().getEmail().equals(emailOfClient)
						&& (cottageReservationIt.getStatusOfReservation() == StatusOfReservation.CANCELED
								|| cottageReservationIt.getStatusOfReservation() == StatusOfReservation.FINISHED
								|| cottageReservationIt.getStatusOfReservation() == StatusOfReservation.MISSED
								|| cottageReservationIt.getTimeOfEndingReservation().isBefore(LocalDateTime.now())))
						.collect(Collectors.toList()));
	}

}
