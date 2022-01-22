package com.isa.booking_entities.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.converter.QuickBookingDTOConverter;
import com.isa.booking_entities.dtos.CottageQuickBookingDisplayDTO;
import com.isa.booking_entities.models.SystemParameters;
import com.isa.booking_entities.models.entites.AdditionalServices;
import com.isa.booking_entities.models.reservations.CottageQuickBooking;
import com.isa.booking_entities.models.reservations.CottageReservation;
import com.isa.booking_entities.models.reservations.StatusOfReservation;
import com.isa.booking_entities.models.reservations.TypeOfReservation;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.repositories.ICottageQuickBookingRepository;
import com.isa.booking_entities.repositories.ISystemParametersRepository;
import com.isa.booking_entities.services.interfaces.ICottageQuickBookingService;

@Service
public class CottageQuickBookingService implements ICottageQuickBookingService {

	private QuickBookingDTOConverter quickBookingDTOConverter;

	private ICottageQuickBookingRepository iCottageQuickBookingRepository;

	private ISystemParametersRepository iSystemParametersRepository;
	
	@Autowired
	public CottageQuickBookingService(ICottageQuickBookingRepository iCottageQuickBookingRepository,
			ISystemParametersRepository iSystemParametersRepository) {
		this.quickBookingDTOConverter = new QuickBookingDTOConverter();
		this.iCottageQuickBookingRepository = iCottageQuickBookingRepository;
		this.iSystemParametersRepository = iSystemParametersRepository;
	}

	@Override
	public CottageQuickBooking getById(long id) {
		return iCottageQuickBookingRepository.findById(id).orElse(null);
	}

	@Override
	public List<CottageQuickBookingDisplayDTO> getFutureFreeQuickBookingsForCottage(long cottageId, Client client) {
		return quickBookingDTOConverter.convertListCottageQuickBookingToListCottageQuickBookingDisplayDTO(
				iCottageQuickBookingRepository.findAll().stream()
						.filter(cottageQuickBookingIt -> cottageQuickBookingIt.getClientForQuickBooking() == null
								&& cottageQuickBookingIt.getTimeOfBeginingReservation().isAfter(LocalDateTime.now())
								&& cottageQuickBookingIt.getCottageForQuickReservation().getId() == cottageId)
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
	public CottageReservation createCottageReservationByCottageQuickBooking(CottageQuickBooking cottageQuickBooking,
			Client client) {
		CottageReservation cottageReservation = new CottageReservation();
		cottageReservation.setAdditionalServicesFromClient(new HashSet<AdditionalServices>(cottageQuickBooking.getAdditionalServices()));
		cottageReservation.setClientForReservation(client);
		cottageReservation.setCottageForReservation(cottageQuickBooking.getCottageForQuickReservation());
		cottageReservation.setNumberOfPerson(cottageQuickBooking.getMaxNumberOfPerson());
		cottageReservation.setStatusOfReservation(StatusOfReservation.CREATED);
		cottageReservation.setTimeOfBeginingReservation(cottageQuickBooking.getTimeOfBeginingReservation());
		cottageReservation.setTimeOfEndingReservation(cottageQuickBooking.getTimeOfEndingReservation());
		cottageReservation.setTotalPrice(cottageQuickBooking.getTotalPrice()* getCoefficentForClient(client));
		cottageReservation.setTypeOfReservation(TypeOfReservation.COTTAGE_RESERVATION);
		return cottageReservation;
	}

	@Override
	public CottageQuickBooking save(CottageQuickBooking cottageQuickBooking) {
		return iCottageQuickBookingRepository.save(cottageQuickBooking);
	}
	
	@Override
	public CottageQuickBooking checkExistCottageQuickBookingForCottageReservation(CottageReservation cottageReservation) {
		return iCottageQuickBookingRepository.findAll().stream().filter(cottageQuickBookingIt -> cottageReservation
				.getTimeOfBeginingReservation().isEqual(cottageReservation.getTimeOfBeginingReservation())
				&& cottageReservation.getTimeOfEndingReservation().isEqual(cottageReservation.getTimeOfEndingReservation())
				&& cottageQuickBookingIt.getClientForQuickBooking().getId() == cottageReservation.getClientForReservation()
						.getId()
				&& cottageQuickBookingIt.getCottageForQuickReservation().getId() == cottageReservation.getCottageForReservation()
						.getId())
				.findAny().orElse(null);
	}
}
