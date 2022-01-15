package com.isa.booking_entities.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.converter.QuickBookingDTOConverter;
import com.isa.booking_entities.dtos.InstructionsQuickBookingDisplayDTO;
import com.isa.booking_entities.models.SystemParameters;
import com.isa.booking_entities.models.reservations.InstructionsQuickBooking;
import com.isa.booking_entities.models.reservations.InstructionsReservation;
import com.isa.booking_entities.models.reservations.StatusOfReservation;
import com.isa.booking_entities.models.reservations.TypeOfReservation;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.repositories.IInstructionsQuickBookingRepository;
import com.isa.booking_entities.repositories.ISystemParametersRepository;
import com.isa.booking_entities.services.interfaces.IInstructionsQuickBookingService;

@Service
public class InstructionsQuickBookingService implements IInstructionsQuickBookingService {

	private QuickBookingDTOConverter quickBookingDTOConverter;

	private IInstructionsQuickBookingRepository iInstructionsQuickBookingRepository;

	private ISystemParametersRepository iSystemParametersRepository;
	
	@Autowired
	public InstructionsQuickBookingService(IInstructionsQuickBookingRepository iInstructionsQuickBookingRepository,
			ISystemParametersRepository iSystemParametersRepository) {
		this.quickBookingDTOConverter = new QuickBookingDTOConverter();
		this.iInstructionsQuickBookingRepository = iInstructionsQuickBookingRepository;
		this.iSystemParametersRepository = iSystemParametersRepository;
	}

	@Override
	public InstructionsQuickBooking getById(long id) {
		return iInstructionsQuickBookingRepository.findById(id).orElse(null);
	}

	@Override
	public List<InstructionsQuickBookingDisplayDTO> getFutureFreeQuickBookingsForInstructions(long instructionsId, Client client) {
		return quickBookingDTOConverter.convertListInstructionsQuickBookingToListInstructionsQuickBookingDisplayDTO(
				iInstructionsQuickBookingRepository.findAll().stream()
						.filter(instructionsQuickBookingIt -> instructionsQuickBookingIt.getClientForQuickBooking() == null
								&& instructionsQuickBookingIt.getTimeOfBeginingReservation().isAfter(LocalDateTime.now())
								&& instructionsQuickBookingIt.getInstructionsForQuickReservation().getId() == instructionsId)
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
	public InstructionsReservation createInstructionsReservationByInstructionsQuickBooking(InstructionsQuickBooking instructionsQuickBooking,
			Client client) {
		InstructionsReservation instructionsReservation = new InstructionsReservation();
		instructionsReservation.setAdditionalServicesFromClient(instructionsQuickBooking.getAdditionalServices());
		instructionsReservation.setClientForReservation(client);
		instructionsReservation.setInstructionsForReservation(instructionsQuickBooking.getInstructionsForQuickReservation());
		instructionsReservation.setNumberOfPerson(instructionsQuickBooking.getMaxNumberOfPerson());
		instructionsReservation.setStatusOfReservation(StatusOfReservation.CREATED);
		instructionsReservation.setTimeOfBeginingReservation(instructionsQuickBooking.getTimeOfBeginingReservation());
		instructionsReservation.setTimeOfEndingReservation(instructionsQuickBooking.getTimeOfEndingReservation());
		instructionsReservation.setTotalPrice(instructionsQuickBooking.getTotalPrice() * getCoefficentForClient(client));
		instructionsReservation.setTypeOfReservation(TypeOfReservation.INSTRUCTIONS_RESERVATION);
		return instructionsReservation;
	}

	@Override
	public InstructionsQuickBooking save(InstructionsQuickBooking instructionsQuickBooking) {
		return iInstructionsQuickBookingRepository.save(instructionsQuickBooking);
	}
}
