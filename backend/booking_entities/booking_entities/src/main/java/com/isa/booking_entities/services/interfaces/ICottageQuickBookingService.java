package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.dtos.CottageQuickBookingDisplayDTO;
import com.isa.booking_entities.models.reservations.CottageQuickBooking;
import com.isa.booking_entities.models.reservations.CottageReservation;
import com.isa.booking_entities.models.users.Client;

public interface ICottageQuickBookingService {
	CottageQuickBooking getById(long id);
	CottageQuickBooking save(CottageQuickBooking cottageQuickBooking);
	List<CottageQuickBookingDisplayDTO> getFutureFreeQuickBookingsForCottage(long cottageId,Client client);
	CottageReservation createCottageReservationByCottageQuickBooking(CottageQuickBooking cottageQuickBooking, Client client);
	CottageQuickBooking checkExistCottageQuickBookingForCottageReservation(CottageReservation cottageReservation);
}
