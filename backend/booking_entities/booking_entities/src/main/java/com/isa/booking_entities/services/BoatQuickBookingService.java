package com.isa.booking_entities.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.converter.QuickBookingDTOConverter;
import com.isa.booking_entities.dtos.BoatQuickBookingDisplayDTO;
import com.isa.booking_entities.models.SystemParameters;
import com.isa.booking_entities.models.reservations.BoatQuickBooking;
import com.isa.booking_entities.models.reservations.BoatQuickBooking;
import com.isa.booking_entities.models.reservations.BoatReservation;
import com.isa.booking_entities.models.reservations.StatusOfReservation;
import com.isa.booking_entities.models.reservations.TypeOfReservation;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.repositories.IBoatQuickBookingRepository;
import com.isa.booking_entities.repositories.ISystemParametersRepository;
import com.isa.booking_entities.services.interfaces.IBoatQuickBookingService;

@Service
public class BoatQuickBookingService implements IBoatQuickBookingService {

	private QuickBookingDTOConverter quickBookingDTOConverter;

	private IBoatQuickBookingRepository iBoatQuickBookingRepository;

	private ISystemParametersRepository iSystemParametersRepository;

	@Autowired
	public BoatQuickBookingService(IBoatQuickBookingRepository iBoatQuickBookingRepository,
			ISystemParametersRepository iSystemParametersRepository) {
		this.quickBookingDTOConverter = new QuickBookingDTOConverter();
		this.iBoatQuickBookingRepository = iBoatQuickBookingRepository;
		this.iSystemParametersRepository = iSystemParametersRepository;
	}

	@Override
	public BoatQuickBooking getById(long id) {
		return iBoatQuickBookingRepository.findById(id).orElse(null);
	}

	@Override
	public List<BoatQuickBookingDisplayDTO> getFutureFreeQuickBookingsForBoat(long boatId, Client client) {
		return quickBookingDTOConverter.convertListBoatQuickBookingToListBoatQuickBookingDisplayDTO(
				iBoatQuickBookingRepository.findAll().stream()
						.filter(boatQuickBookingIt -> boatQuickBookingIt.getClientForQuickBooking() == null
								&& boatQuickBookingIt.getTimeOfBeginingReservation().isAfter(LocalDateTime.now())
								&& boatQuickBookingIt.getBoatForQuickReservation().getId() == boatId)
						.collect(Collectors.toList()),
				getCoefficentForClient(client));
	}

	private double getCoefficentForClient(Client client) {
		SystemParameters systemParameters = iSystemParametersRepository.findAll().stream().findFirst().orElse(null);
		switch (client.getStatuseOfUser()) {
		case SILVER:
			return (100 - systemParameters.getDiscountForSilver()) / 100;
		case GOLD:
			return (100 - systemParameters.getDiscountForGold()) / 100;
		case REGULAR:
			return (100 - systemParameters.getDiscountForRegular()) / 100;
		default:
			return 1;
		}
	}

	@Override
	public BoatReservation createBoatReservationByBoatQuickBooking(BoatQuickBooking boatQuickBooking, Client client) {
		BoatReservation boatReservation = new BoatReservation();
		boatReservation.setAdditionalServicesFromClient(boatQuickBooking.getAdditionalServices());
		boatReservation.setClientForReservation(client);
		boatReservation.setBoatForReservation(boatQuickBooking.getBoatForQuickReservation());
		boatReservation.setNumberOfPerson(boatQuickBooking.getMaxNumberOfPerson());
		boatReservation.setStatusOfReservation(StatusOfReservation.CREATED);
		boatReservation.setTimeOfBeginingReservation(boatQuickBooking.getTimeOfBeginingReservation());
		boatReservation.setTimeOfEndingReservation(boatQuickBooking.getTimeOfEndingReservation());
		boatReservation.setTotalPrice(boatQuickBooking.getTotalPrice() * getCoefficentForClient(client));
		boatReservation.setTypeOfReservation(TypeOfReservation.BOAT_RESERVATION);
		return boatReservation;
	}

	@Override
	public BoatQuickBooking save(BoatQuickBooking boatQuickBooking) {
		return iBoatQuickBookingRepository.save(boatQuickBooking);
	}

	@Override
	public BoatQuickBooking checkExistBoatQuickBookingForBoatReservation(BoatReservation boatReservation) {
		return iBoatQuickBookingRepository.findAll().stream().filter(boatQuickBookingIt -> boatReservation
				.getTimeOfBeginingReservation().isEqual(boatReservation.getTimeOfBeginingReservation())
				&& boatReservation.getTimeOfEndingReservation().isEqual(boatReservation.getTimeOfEndingReservation())
				&& boatQuickBookingIt.getClientForQuickBooking().getId() == boatReservation.getClientForReservation()
						.getId()
				&& boatQuickBookingIt.getBoatForQuickReservation().getId() == boatReservation.getBoatForReservation()
						.getId())
				.findAny().orElse(null);
	}

}
