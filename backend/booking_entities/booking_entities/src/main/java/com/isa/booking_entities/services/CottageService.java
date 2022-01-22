package com.isa.booking_entities.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.converter.CottagePreviewDTOConverter;
import com.isa.booking_entities.dtos.CottageDisplayDTO;
import com.isa.booking_entities.dtos.CottagePreviewDTO;
import com.isa.booking_entities.dtos.CottageSearchDTO;
import com.isa.booking_entities.dtos.EntitySearchReservationDTO;
import com.isa.booking_entities.models.entites.AdditionalServices;
import com.isa.booking_entities.models.entites.Cottage;
import com.isa.booking_entities.models.reservations.CottageAvailabilityPeriod;
import com.isa.booking_entities.models.reservations.CottageQuickBooking;
import com.isa.booking_entities.models.reservations.CottageReservation;
import com.isa.booking_entities.models.reservations.QuickBooking;
import com.isa.booking_entities.models.reservations.StatusOfReservation;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.repositories.ICottageRepository;
import com.isa.booking_entities.services.interfaces.ICottageService;

@Service
public class CottageService implements ICottageService {

	private ICottageRepository iCottageRepository;

	private CottagePreviewDTOConverter cottagePreviewDTOConverter;
	
	@Autowired
	public CottageService(ICottageRepository iCottageRepository) {
		this.iCottageRepository=iCottageRepository;
		this.cottagePreviewDTOConverter = new CottagePreviewDTOConverter();
	}
	
	@Override
	public Cottage getById(long id) {
		return iCottageRepository.findById(id).orElse(null);
	}

	@Override
	public Cottage save(Cottage cottage) {
		return iCottageRepository.save(cottage);
	}

	@Override
	public List<CottagePreviewDTO> getAllCottages(CottageSearchDTO cottageSearchDTO) {
		return cottagePreviewDTOConverter.convertListCottageToListCottagePreviewDTO(iCottageRepository.findAll()
				.stream().filter(cottageIt -> doesCottageMeetParameters(cottageIt, cottageSearchDTO))
				.collect(Collectors.toList()));
	}

	private Boolean doesCottageMeetParameters(Cottage cottage, CottageSearchDTO cottageSearchDTO) {
		Boolean returnValueCity = checkContainsString(cottageSearchDTO.getCity(), cottage.getAddress().getCity());
		Boolean returnValueCountry = checkContainsString(cottageSearchDTO.getCountry(),
				cottage.getAddress().getCountry());
		Boolean returnValueTitle = checkContainsString(cottageSearchDTO.getTitle(), cottage.getTitle());
		Boolean returnValueMinNumberOfBeds = checkMinValueInt(cottageSearchDTO.getMinNumberOfBeds(),
				cottage.getNumberOfBeds());
		Boolean returnValueMaxNumberOfBeds = checkMaxValueInt(cottageSearchDTO.getMaxNumberOfBeds(),
				cottage.getNumberOfBeds());
		Boolean returnValueMinNumberOfRooms = checkMinValueInt(cottageSearchDTO.getMinNumberOfRooms(),
				cottage.getNumberOfRooms());
		Boolean returnValueMaxNumberOfRooms = checkMaxValueInt(cottageSearchDTO.getMaxNumberOfRooms(),
				cottage.getNumberOfRooms());
		Boolean returnValueMinPricePerNight = checkMinValueDouble(cottageSearchDTO.getMinPricePerNight(),
				cottage.getPricePerNight());
		Boolean returnValueMaxPricePerNight = checkMaxValueDouble(cottageSearchDTO.getMaxPricePerNight(),
				cottage.getPricePerNight());
		Boolean returnValueMinAverageGrade = checkMinValueDouble(cottageSearchDTO.getMinAverageGrade(),
				cottage.getAverageGrade());
		Boolean returnValueMaxAverageGrade = checkMaxValueDouble(cottageSearchDTO.getMaxAverageGrade(),
				cottage.getAverageGrade());

		return returnValueMaxAverageGrade && returnValueMinAverageGrade && returnValueMaxPricePerNight
				&& returnValueMinPricePerNight && returnValueMaxNumberOfRooms && returnValueMinNumberOfRooms
				&& returnValueMaxNumberOfBeds && returnValueMinNumberOfBeds && returnValueTitle && returnValueCity
				&& returnValueCountry && !cottage.getDeleted();
	}

	private Boolean checkMinValueDouble(double numberDTO, double numberCottage) {
		Boolean returnValue = true;
		if (numberDTO != -1) {
			returnValue = numberCottage >= numberDTO ? true : false;
		}
		return returnValue;
	}

	private Boolean checkMinValueInt(int numberDTO, int numberCottage) {
		Boolean returnValue = true;
		if (numberDTO != -1) {
			returnValue = numberCottage >= numberDTO ? true : false;
		}
		return returnValue;
	}

	private Boolean checkMaxValueDouble(double numberDTO, double numberCottage) {
		Boolean returnValue = true;
		if (numberDTO != -1) {
			returnValue = numberCottage <= numberDTO ? true : false;
		}
		return returnValue;
	}

	private Boolean checkMaxValueInt(int numberDTO, int numberCottage) {
		Boolean returnValue = true;
		if (numberDTO != -1) {
			returnValue = numberCottage <= numberDTO ? true : false;
		}
		return returnValue;
	}

	private Boolean checkContainsString(String stringDTO, String stringCottage) {
		Boolean returnValue = true;
		if (!stringDTO.equals("")) {
			returnValue = stringCottage.toLowerCase().contains(stringDTO.toLowerCase());
		}
		return returnValue;
	}

	@Override
	public List<CottageDisplayDTO> getAllCottagesForClient(EntitySearchReservationDTO cottageSearchReservationDTO, Client client) {
		List<CottageDisplayDTO> convertedListOfCottage = cottagePreviewDTOConverter.convertListCottageToListCottageDisplayDTO(iCottageRepository.findAll().stream().filter(
				cottageIt -> doesCottageMeetParameters(cottageIt, cottageSearchReservationDTO))
				.collect(Collectors.toList()));
		convertedListOfCottage.forEach(cottageDisplayDTOIt -> cottageDisplayDTOIt.setUserSubscribe(isClientSubscribedOnCottage(client,cottageDisplayDTOIt.getId())));
		return convertedListOfCottage;
	}
	
	private boolean isClientSubscribedOnCottage(Client client, long cottageId) {
		return client.getCottageSubscriptions().stream().filter(cottageIt -> cottageIt.getId()==cottageId).findFirst().orElse(null) != null;
	}
	
	private Boolean doesCottageMeetParameters(Cottage cottage,
			EntitySearchReservationDTO cottageSearchReservationDTO) {
		Boolean returnValueCity = checkContainsString(cottageSearchReservationDTO.getCity(),
				cottage.getAddress().getCity());
		Boolean returnValueCountry = checkContainsString(cottageSearchReservationDTO.getCountry(),
				cottage.getAddress().getCountry());
		Boolean returnValueMinPricePerNight = checkMinValueDouble(cottageSearchReservationDTO.getMinPrice(),
				cottage.getPricePerNight());
		Boolean returnValueMaxPricePerNight = checkMaxValueDouble(cottageSearchReservationDTO.getMaxPrice(),
				cottage.getPricePerNight());
		Boolean returnValueMinAverageGrade = checkMinValueDouble(cottageSearchReservationDTO.getMinAverageGrade(),
				cottage.getAverageGrade());
		Boolean returnValueMaxAverageGrade = checkMaxValueDouble(cottageSearchReservationDTO.getMaxAverageGrade(),
				cottage.getAverageGrade());
		Boolean returnValueIsFreeForPeriod = isThereReservationForCottage(cottage,
				cottageSearchReservationDTO);
		Boolean returnValueExistAvailabilityPeriod = isTherePeriodOfAvailabilityForCottage(cottage,
				cottageSearchReservationDTO);

		return returnValueMaxAverageGrade && returnValueMinAverageGrade && returnValueMaxPricePerNight
				&& returnValueMinPricePerNight && returnValueIsFreeForPeriod && returnValueExistAvailabilityPeriod
				&& returnValueCity && returnValueCountry && !cottage.getDeleted();
	}

	private Boolean isTherePeriodOfAvailabilityForCottage(Cottage cottage,
			EntitySearchReservationDTO entitySearchReservationDTO) {
		if (entitySearchReservationDTO.getBeginDate()==null && entitySearchReservationDTO.getEndDate()==null) return true;
		CottageAvailabilityPeriod cottageAvailabilityPeriod = cottage.getCottageAvailabilityPeriods()
				.stream()
				.filter(availabilityPeriodIt -> (entitySearchReservationDTO.getBeginDate()
						.isAfter(availabilityPeriodIt.getBeginPeriod())
						&& entitySearchReservationDTO.getEndDate().isBefore(availabilityPeriodIt.getEndPeriod())))
				.findFirst().orElse(null);

		return cottageAvailabilityPeriod == null ? false : true;
	}

	private Boolean isThereReservationForCottage(Cottage cottage,
			EntitySearchReservationDTO entitySearchReservationDTO) {
		if (entitySearchReservationDTO.getBeginDate()==null && entitySearchReservationDTO.getEndDate()==null) return true;
		List<CottageReservation> cottageReservation = cottage.getCottageReservations().stream()
				.filter(reservationIt -> isReservationBeforOrAfterPeriod(entitySearchReservationDTO, reservationIt))
				.collect(Collectors.toList());

		List<QuickBooking> quickBookings = cottage.getCottageQuickBookings().stream()
				.filter(quickBookingIt -> isQuickBookingBeforOrAfterPeriod(entitySearchReservationDTO, quickBookingIt))
				.collect(Collectors.toList());
		return (cottageReservation.size() != cottage.getCottageReservations().size()
				|| quickBookings.size() != cottage.getCottageQuickBookings().size()) ? false : true;
	}

	private boolean isReservationBeforOrAfterPeriod(EntitySearchReservationDTO entitySearchReservationDTO,
			CottageReservation reservationIt) {
		return reservationIt.getStatusOfReservation()!=StatusOfReservation.CREATED || (reservationIt.getTimeOfBeginingReservation().isAfter(entitySearchReservationDTO.getBeginDate())
				&& reservationIt.getTimeOfBeginingReservation().isAfter(entitySearchReservationDTO.getEndDate()))
				|| (reservationIt.getTimeOfEndingReservation().isBefore(entitySearchReservationDTO.getBeginDate())
						&& reservationIt.getTimeOfEndingReservation()
								.isBefore(entitySearchReservationDTO.getEndDate()));
	}

	private boolean isQuickBookingBeforOrAfterPeriod(EntitySearchReservationDTO entitySearchReservationDTO,
			CottageQuickBooking quickBookingIt) {
		return (quickBookingIt.getTimeOfBeginingReservation().isAfter(entitySearchReservationDTO.getBeginDate())
				&& quickBookingIt.getTimeOfBeginingReservation().isAfter(entitySearchReservationDTO.getEndDate()))
				|| (quickBookingIt.getTimeOfEndingReservation().isBefore(entitySearchReservationDTO.getBeginDate())
						&& quickBookingIt.getTimeOfEndingReservation()
								.isBefore(entitySearchReservationDTO.getEndDate()));
	}
	
	@Override
	public List<String> getAdditionalServicesForCottage(long id) throws Exception {
		Cottage cottage = iCottageRepository.findById(id).orElse(null);
		if (cottage==null) {
			throw new Exception("Don't exist cottage with id '"+id+"'!");
		}
		return getNamesOfAdditionalServices(cottage.getAdditionalServices());
	}
	
	private List<String> getNamesOfAdditionalServices(Set<AdditionalServices> additionalServices){
		List<String> list = new ArrayList<String>();
		additionalServices.forEach(additionalServiceIt -> list.add(additionalServiceIt.getName()));
		return list;
	}

	@Override
	public List<CottagePreviewDTO> getAllNonDeletedCottages() {
		return cottagePreviewDTOConverter.convertListCottageToListCottagePreviewDTO(iCottageRepository.findAll().stream().filter(cottageIt -> !cottageIt.getDeleted()).collect(Collectors.toList())); 
	}
}
