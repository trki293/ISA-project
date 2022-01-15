package com.isa.booking_entities.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.converter.ReservationDTOConverter;
import com.isa.booking_entities.dtos.ReservationFutureDisplayDTO;
import com.isa.booking_entities.models.reservations.Reservation;
import com.isa.booking_entities.models.reservations.StatusOfReservation;
import com.isa.booking_entities.repositories.IReservationRepository;
import com.isa.booking_entities.services.interfaces.IReservationService;

@Service
public class ReservationService implements IReservationService {

	private IReservationRepository iReservationRepository;

	private ReservationDTOConverter reservationDTOConverter;

	@Autowired
	public ReservationService(IReservationRepository iReservationRepository) {
		this.iReservationRepository = iReservationRepository;
		this.reservationDTOConverter = new ReservationDTOConverter();
	}

	@Override
	public Reservation getById(long id) {
		return iReservationRepository.findById(id).orElse(null);
	}

	@Override
	public Reservation save(Reservation reservation) {
		return iReservationRepository.save(reservation);
	}

	@Override
	public List<ReservationFutureDisplayDTO> getFutureReservationsForClient(String emailOfClient) {
		return reservationDTOConverter.convertListReservationToListReservationFutureDisplayDTO(iReservationRepository
				.findAll().stream()
				.filter(reservationIt -> reservationIt.getTimeOfBeginingReservation().isAfter(LocalDateTime.now())
						&& reservationIt.getStatusOfReservation() == StatusOfReservation.CREATED)
				.collect(Collectors.toList()));
	}

}
