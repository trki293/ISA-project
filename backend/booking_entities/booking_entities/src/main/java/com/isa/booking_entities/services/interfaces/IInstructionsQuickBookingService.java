package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.dtos.InstructionsQuickBookingDisplayDTO;
import com.isa.booking_entities.models.reservations.InstructionsQuickBooking;
import com.isa.booking_entities.models.reservations.InstructionsReservation;
import com.isa.booking_entities.models.users.Client;

public interface IInstructionsQuickBookingService {
	InstructionsQuickBooking getById(long id);
	List<InstructionsQuickBookingDisplayDTO> getFutureFreeQuickBookingsForInstructions(long instructionsId, Client client);
	InstructionsQuickBooking save(InstructionsQuickBooking instructionsQuickBooking);
	InstructionsReservation createInstructionsReservationByInstructionsQuickBooking(
			InstructionsQuickBooking instructionsQuickBooking, Client client);
}
