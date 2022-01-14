package com.isa.booking_entities.services;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.converter.BoatReservationDTOConverter;
import com.isa.booking_entities.dtos.BoatReservationHistoryDTO;
import com.isa.booking_entities.models.reservations.BoatReservation;
import com.isa.booking_entities.models.reservations.StatusOfReservation;
import com.isa.booking_entities.repositories.IBoatReservationRepository;
import com.isa.booking_entities.services.interfaces.IBoatReservationService;

@Service
public class BoatReservationService implements IBoatReservationService {

	private IBoatReservationRepository iBoatReservationRepository;

	private BoatReservationDTOConverter boatReservationDTOConverter;

	@Autowired
	public BoatReservationService(IBoatReservationRepository iBoatReservationRepository) {
		this.iBoatReservationRepository = iBoatReservationRepository;
		this.boatReservationDTOConverter = new BoatReservationDTOConverter();
	}

	@Override
	public BoatReservation getById(long id) {
		return iBoatReservationRepository.findById(id).orElse(null);
	}

	@Override
	public BoatReservation save(BoatReservation boatReservation) {
		return iBoatReservationRepository.save(boatReservation);
	}

	@Override
	public List<BoatReservationHistoryDTO> getHistoryOfBoatReservations(String emailOfClient) {
		return boatReservationDTOConverter.convertListBoatReservationToListBoatReservationHistoryDTO(
				iBoatReservationRepository.findAll().stream().filter(boatReservationIt -> 
				boatReservationIt.getClientForReservation().getEmail().equals(emailOfClient)
						&& (boatReservationIt.getStatusOfReservation() == StatusOfReservation.CANCELED
								|| boatReservationIt.getStatusOfReservation() == StatusOfReservation.FINISHED
								|| boatReservationIt.getStatusOfReservation() == StatusOfReservation.MISSED
								|| boatReservationIt.getTimeOfEndingReservation().isBefore(LocalDateTime.now())))
						.collect(Collectors.toList()));
	}

}
