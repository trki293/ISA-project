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
import com.isa.booking_entities.repositories.IBoatReservationRepository;
import com.isa.booking_entities.repositories.ICottageReservationRepository;
import com.isa.booking_entities.repositories.IInstructionsReservationRepository;
import com.isa.booking_entities.repositories.IReservationRepository;
import com.isa.booking_entities.services.interfaces.IReservationService;

@Service
public class ReservationService implements IReservationService {

	private IReservationRepository iReservationRepository;
	
	private ICottageReservationRepository iCottageReservationRepository;
	
	private IBoatReservationRepository iBoatReservationRepository;
	
	private IInstructionsReservationRepository iInstructionsReservationRepository;
	
	private ReservationDTOConverter reservationDTOConverter;
	
	@Autowired
	public ReservationService(IReservationRepository iReservationRepository,
			ICottageReservationRepository iCottageReservationRepository,
			IBoatReservationRepository iBoatReservationRepository,
			IInstructionsReservationRepository iInstructionsReservationRepository) {
		this.iReservationRepository = iReservationRepository;
		this.iCottageReservationRepository = iCottageReservationRepository;
		this.iBoatReservationRepository = iBoatReservationRepository;
		this.reservationDTOConverter = new ReservationDTOConverter();
		this.iInstructionsReservationRepository = iInstructionsReservationRepository;
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
		List<ReservationFutureDisplayDTO> convertedListReservation = reservationDTOConverter.convertListReservationToListReservationFutureDisplayDTO(iReservationRepository
				.findAll().stream()
				.filter(reservationIt -> reservationIt.getTimeOfBeginingReservation().isAfter(LocalDateTime.now())
						&& reservationIt.getStatusOfReservation() == StatusOfReservation.CREATED)
				.collect(Collectors.toList()));
		convertedListReservation.forEach(reservationFutureDisplayDTOIt -> reservationFutureDisplayDTOIt.setTitle(getTitleByReservationFutureDisplayDTO(reservationFutureDisplayDTOIt.getId())));
		return convertedListReservation;
	}

	private String getTitleByReservationFutureDisplayDTO(long id) {
		System.out.println(id);
		Reservation reservation = iReservationRepository.findById(id).orElse(null);
		if (reservation==null) System.out.println("Nije pronasao");
		switch (reservation.getTypeOfReservation()) {
		case COTTAGE_RESERVATION:
			return iCottageReservationRepository.getById(id).getCottageForReservation().getTitle();
		case BOAT_RESERVATION:
			return iBoatReservationRepository.getById(id).getBoatForReservation().getTitle();
		case INSTRUCTIONS_RESERVATION:
			return iInstructionsReservationRepository.getById(id).getInstructionsForReservation().getTitle();
		default:
			return null;
		}
	}

}
