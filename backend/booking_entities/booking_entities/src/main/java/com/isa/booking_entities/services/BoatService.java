package com.isa.booking_entities.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.converter.BoatPreviewDTOConverter;
import com.isa.booking_entities.dtos.BoatDisplayDTO;
import com.isa.booking_entities.dtos.BoatPreviewDTO;
import com.isa.booking_entities.dtos.BoatSearchDTO;
import com.isa.booking_entities.dtos.EntitySearchReservationDTO;
import com.isa.booking_entities.models.entites.AdditionalServices;
import com.isa.booking_entities.models.entites.Boat;
import com.isa.booking_entities.models.entites.Boat;
import com.isa.booking_entities.models.reservations.BoatAvailabilityPeriod;
import com.isa.booking_entities.models.reservations.BoatQuickBooking;
import com.isa.booking_entities.models.reservations.BoatReservation;
import com.isa.booking_entities.models.reservations.QuickBooking;
import com.isa.booking_entities.repositories.IBoatRepository;
import com.isa.booking_entities.services.interfaces.IBoatService;

@Service
public class BoatService implements IBoatService {

	private IBoatRepository iBoatRepository;

	private BoatPreviewDTOConverter boatPreviewDTOConverter;

	@Autowired
	public BoatService(IBoatRepository iBoatRepository) {
		this.iBoatRepository = iBoatRepository;
		this.boatPreviewDTOConverter = new BoatPreviewDTOConverter();
	}

	@Override
	public Boat getById(long id) {
		return iBoatRepository.findById(id).orElse(null);
	}

	@Override
	public Boat save(Boat boat) {
		return iBoatRepository.save(boat);
	}

	@Override
	public List<BoatPreviewDTO> getAllBoats(BoatSearchDTO boatSearchDTO) {
		return boatPreviewDTOConverter.convertListBoatToListBoatPreviewDTO(iBoatRepository.findAll()
				.stream().filter(boatIt -> doesBoatMeetParameters(boatIt, boatSearchDTO))
				.collect(Collectors.toList()));
	}

	private Boolean doesBoatMeetParameters(Boat boat, BoatSearchDTO boatSearchDTO) {
		Boolean returnValueCity = checkContainsString(boatSearchDTO.getCity(), boat.getAddress().getCity());
		Boolean returnValueCountry = checkContainsString(boatSearchDTO.getCountry(), boat.getAddress().getCountry());
		Boolean returnValueTitle = checkContainsString(boatSearchDTO.getTitle(), boat.getTitle());
		Boolean returnValueMinPricePerHour = checkMinValueDouble(boatSearchDTO.getMinPricePerHour(),
				boat.getPricePerHour());
		Boolean returnValueMaxPricePerHour = checkMaxValueDouble(boatSearchDTO.getMaxPricePerHour(),
				boat.getPricePerHour());
		Boolean returnValueMinAverageGrade = checkMinValueDouble(boatSearchDTO.getMinAverageGrade(),
				boat.getAverageGrade());
		Boolean returnValueMaxAverageGrade = checkMaxValueDouble(boatSearchDTO.getMaxAverageGrade(),
				boat.getAverageGrade());
		Boolean returnValueMinCapacity = checkMinValueInt(boatSearchDTO.getMinCapacity(), boat.getCapacity());
		Boolean returnValueMaxCapacity = checkMaxValueInt(boatSearchDTO.getMaxCapacity(), boat.getCapacity());

		return returnValueMaxAverageGrade && returnValueMinAverageGrade && returnValueMaxPricePerHour
				&& returnValueMinPricePerHour && returnValueMinCapacity && returnValueMaxCapacity && returnValueTitle
				&& returnValueCity && returnValueCountry && !boat.getDeleted();
	}

	private Boolean checkMinValueDouble(double numberDTO, double numberBoat) {
		Boolean returnValue = true;
		if (numberDTO != -1) {
			returnValue = numberBoat >= numberDTO ? true : false;
		}
		return returnValue;
	}

	private Boolean checkMinValueInt(int numberDTO, int numberBoat) {
		Boolean returnValue = true;
		if (numberDTO != -1) {
			returnValue = numberBoat >= numberDTO ? true : false;
		}
		return returnValue;
	}

	private Boolean checkMaxValueDouble(double numberDTO, double numberBoat) {
		Boolean returnValue = true;
		if (numberDTO != -1) {
			returnValue = numberBoat <= numberDTO ? true : false;
		}
		return returnValue;
	}

	private Boolean checkMaxValueInt(int numberDTO, int numberBoat) {
		Boolean returnValue = true;
		if (numberDTO != -1) {
			returnValue = numberBoat <= numberDTO ? true : false;
		}
		return returnValue;
	}

	private Boolean checkContainsString(String stringDTO, String stringBoat) {
		Boolean returnValue = true;
		if (!stringDTO.equals("")) {
			returnValue = stringBoat.toLowerCase().contains(stringDTO.toLowerCase());
		}
		return returnValue;
	}

	@Override
	public List<BoatDisplayDTO> getAllBoatsForClient(EntitySearchReservationDTO boatSearchReservationDTO) {
		return boatPreviewDTOConverter
				.convertListBoatToListBoatDisplayDTO(iBoatRepository.findAll().stream().filter(
						boatIt -> doesBoatMeetParameters(boatIt, boatSearchReservationDTO))
						.collect(Collectors.toList()));
	}
	
	private Boolean doesBoatMeetParameters(Boat boat,
			EntitySearchReservationDTO boatSearchReservationDTO) {
		Boolean returnValueCity = checkContainsString(boatSearchReservationDTO.getCity(),
				boat.getAddress().getCity());
		Boolean returnValueCountry = checkContainsString(boatSearchReservationDTO.getCountry(),
				boat.getAddress().getCountry());
		Boolean returnValueMinPricePerHour = checkMinValueDouble(boatSearchReservationDTO.getMinPrice(),
				boat.getPricePerHour());
		Boolean returnValueMaxPricePerHour = checkMaxValueDouble(boatSearchReservationDTO.getMaxPrice(),
				boat.getPricePerHour());
		Boolean returnValueMinAverageGrade = checkMinValueDouble(boatSearchReservationDTO.getMinAverageGrade(),
				boat.getAverageGrade());
		Boolean returnValueMaxAverageGrade = checkMaxValueDouble(boatSearchReservationDTO.getMaxAverageGrade(),
				boat.getAverageGrade());
		Boolean returnValueIsFreeForPeriod = isThereReservationForInstruction(boat,
				boatSearchReservationDTO);
		Boolean returnValueExistAvailabilityPeriod = isTherePeriodOfAvailabilityForInstruction(boat,
				boatSearchReservationDTO);

		return returnValueMaxAverageGrade && returnValueMinAverageGrade && returnValueMaxPricePerHour
				&& returnValueMinPricePerHour && returnValueIsFreeForPeriod && returnValueExistAvailabilityPeriod
				&& returnValueCity && returnValueCountry && !boat.getDeleted();
	}

	private Boolean isTherePeriodOfAvailabilityForInstruction(Boat boat,
			EntitySearchReservationDTO entitySearchReservationDTO) {
		BoatAvailabilityPeriod boatAvailabilityPeriod = boat.getBoatAvailabilityPeriods()
				.stream()
				.filter(availabilityPeriodIt -> (entitySearchReservationDTO.getBeginDate()
						.isAfter(availabilityPeriodIt.getBeginPeriod())
						&& entitySearchReservationDTO.getEndDate().isBefore(availabilityPeriodIt.getEndPeriod())))
				.findFirst().orElse(null);

		return boatAvailabilityPeriod == null ? false : true;
	}

	private Boolean isThereReservationForInstruction(Boat boat,
			EntitySearchReservationDTO entitySearchReservationDTO) {
		List<BoatReservation> boatReservation = boat.getBoatReservations().stream()
				.filter(reservationIt -> isReservationBeforOrAfterPeriod(entitySearchReservationDTO, reservationIt))
				.collect(Collectors.toList());

		List<QuickBooking> quickBookings = boat.getBoatQuickBookings().stream()
				.filter(quickBookingIt -> isQuickBookingBeforOrAfterPeriod(entitySearchReservationDTO, quickBookingIt))
				.collect(Collectors.toList());
		return (boatReservation.size() != boat.getBoatReservations().size()
				|| quickBookings.size() != boat.getBoatQuickBookings().size()) ? false : true;
	}

	private boolean isReservationBeforOrAfterPeriod(EntitySearchReservationDTO entitySearchReservationDTO,
			BoatReservation reservationIt) {
		return (reservationIt.getTimeOfBeginingReservation().isBefore(entitySearchReservationDTO.getBeginDate())
				&& reservationIt.getTimeOfBeginingReservation().isBefore(entitySearchReservationDTO.getEndDate()))
				|| (reservationIt.getTimeOfBeginingReservation().isAfter(entitySearchReservationDTO.getBeginDate())
						&& reservationIt.getTimeOfBeginingReservation()
								.isAfter(entitySearchReservationDTO.getEndDate()));
	}

	private boolean isQuickBookingBeforOrAfterPeriod(EntitySearchReservationDTO entitySearchReservationDTO,
			BoatQuickBooking quickBookingIt) {
		return (quickBookingIt.getTimeOfBeginingReservation().isBefore(entitySearchReservationDTO.getBeginDate())
				&& quickBookingIt.getTimeOfBeginingReservation().isBefore(entitySearchReservationDTO.getEndDate()))
				|| (quickBookingIt.getTimeOfBeginingReservation().isAfter(entitySearchReservationDTO.getBeginDate())
						&& quickBookingIt.getTimeOfBeginingReservation()
								.isAfter(entitySearchReservationDTO.getEndDate()));
	}

	@Override
	public List<String> getAdditionalServicesForBoat(long id) throws Exception {
		Boat boat = iBoatRepository.findById(id).orElse(null);
		if (boat==null) {
			throw new Exception("Don't exist boat with id '"+id+"'!");
		}
		return getNamesOfAdditionalServices(boat.getAdditionalServices());
	}
	
	private List<String> getNamesOfAdditionalServices(Set<AdditionalServices> additionalServices){
		List<String> list = new ArrayList<String>();
		additionalServices.forEach(additionalServiceIt -> list.add(additionalServiceIt.getName()));
		return list;
	}
}
