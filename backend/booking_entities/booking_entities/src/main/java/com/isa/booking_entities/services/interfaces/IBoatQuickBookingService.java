package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.dtos.BoatQuickBookingDisplayDTO;
import com.isa.booking_entities.models.reservations.BoatQuickBooking;
import com.isa.booking_entities.models.reservations.BoatReservation;
import com.isa.booking_entities.models.users.Client;

public interface IBoatQuickBookingService {
	BoatQuickBooking getById(long id);
	List<BoatQuickBookingDisplayDTO> getFutureFreeQuickBookingsForBoat(long boatId, Client client);
	BoatReservation createBoatReservationByBoatQuickBooking(BoatQuickBooking boatQuickBooking, Client client);
	BoatQuickBooking save(BoatQuickBooking boatQuickBooking);
	BoatQuickBooking checkExistBoatQuickBookingForBoatReservation(BoatReservation boatReservation);
}
