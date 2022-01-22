package com.isa.booking_entities.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.converter.CottageReservationDTOConverter;
import com.isa.booking_entities.dtos.CottageReservationHistoryDTO;
import com.isa.booking_entities.dtos.CottageReservationNewDTO;
import com.isa.booking_entities.models.SystemParameters;
import com.isa.booking_entities.models.entites.AdditionalServices;
import com.isa.booking_entities.models.entites.Cottage;
import com.isa.booking_entities.models.reservations.CottageReservation;
import com.isa.booking_entities.models.reservations.StatusOfReservation;
import com.isa.booking_entities.models.reservations.TypeOfReservation;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.models.users.StatusOfUser;
import com.isa.booking_entities.repositories.IAdditionalServicesRepository;
import com.isa.booking_entities.repositories.ICottageReservationRepository;
import com.isa.booking_entities.repositories.ICottageReviewRepository;
import com.isa.booking_entities.repositories.ISystemParametersRepository;
import com.isa.booking_entities.services.interfaces.ICottageReservationService;

@Service
public class CottageReservationService implements ICottageReservationService {

	private ICottageReservationRepository iCottageReservationRepository;
	
	private ICottageReviewRepository iCottageReviewRepository;

	private CottageReservationDTOConverter cottageReservationDTOConverter;

	private ISystemParametersRepository iSystemParametersRepository;

	private IAdditionalServicesRepository iAdditionalServicesRepository;

	@Autowired
	public CottageReservationService(ICottageReservationRepository iCottageReservationRepository,
			ICottageReviewRepository iCottageReviewRepository,
			ISystemParametersRepository iSystemParametersRepository,
			IAdditionalServicesRepository iAdditionalServicesRepository) {
		this.iCottageReservationRepository = iCottageReservationRepository;
		this.iCottageReviewRepository = iCottageReviewRepository;
		this.cottageReservationDTOConverter = new CottageReservationDTOConverter();
		this.iSystemParametersRepository = iSystemParametersRepository;
		this.iAdditionalServicesRepository = iAdditionalServicesRepository;
	}

	@Override
	public CottageReservation getById(long id) {
		return iCottageReservationRepository.findById(id).orElse(null);
	}

	@Override
	public CottageReservation save(CottageReservation cottageReservation) {
		return iCottageReservationRepository.save(cottageReservation);
	}

	@Override
	public List<CottageReservationHistoryDTO> getHistoryOfCottageReservations(String emailOfClient) {
		List<CottageReservationHistoryDTO> convertedListCottageReservation = cottageReservationDTOConverter.convertListCottageReservationToListCottageReservationHistoryDTO(
				iCottageReservationRepository.findAll().stream().filter(cottageReservationIt -> cottageReservationIt
						.getClientForReservation().getEmail().equals(emailOfClient)
						&& (cottageReservationIt.getStatusOfReservation() == StatusOfReservation.CANCELED
								|| cottageReservationIt.getStatusOfReservation() == StatusOfReservation.FINISHED
								|| cottageReservationIt.getStatusOfReservation() == StatusOfReservation.MISSED
								|| cottageReservationIt.getTimeOfEndingReservation().isBefore(LocalDateTime.now())))
						.collect(Collectors.toList()));
		convertedListCottageReservation.forEach(cottageReservationHistoryDTOIt -> cottageReservationHistoryDTOIt.setUserReviewed(isHaveCottageReviewForReservation(emailOfClient, cottageReservationHistoryDTOIt.getId())) );
		return convertedListCottageReservation;
	}

	
	private boolean isHaveCottageReviewForReservation(String emailOfClient, long reservationId) {
		return iCottageReviewRepository.findAll().stream().filter(cottageReviewIt -> cottageReviewIt.getClientWhoEvaluating().getEmail().equals(emailOfClient) && cottageReviewIt.getReservationBeingEvaluated().getId()==reservationId).findFirst().orElse(null) != null;
	}

	@Override
	public CottageReservation createReservation(CottageReservationNewDTO cottageReservationNewDTO,
			Cottage cottageForReservation, Client clientForReservation) {
		CottageReservation cottageReservation = new CottageReservation();
		SystemParameters systemParameters = iSystemParametersRepository.findAll().stream().findFirst().orElse(null);
		double coefficient = clientForReservation.getStatuseOfUser() == StatusOfUser.REGULAR
				? (100 - systemParameters.getDiscountForRegular()) / 100
				: (clientForReservation.getStatuseOfUser() == StatusOfUser.SILVER
						? (100 - systemParameters.getDiscountForSilver()) / 100
						: (100 - systemParameters.getDiscountForGold()) / 100);
		cottageReservation.setNumberOfPerson(cottageReservationNewDTO.getNumberOfPerson());
		cottageReservation.setClientForReservation(clientForReservation);
		cottageReservation.setCottageForReservation(cottageForReservation);
		cottageReservation.setStatusOfReservation(StatusOfReservation.CREATED);
		cottageReservation.setTypeOfReservation(TypeOfReservation.COTTAGE_RESERVATION);
		cottageReservation.setTimeOfBeginingReservation(cottageReservationNewDTO.getTimeOfBeginingReservation());
		cottageReservation.setTimeOfEndingReservation(cottageReservationNewDTO.getTimeOfEndingReservation());
		cottageReservation.setTotalPrice((Duration.between(cottageReservationNewDTO.getTimeOfBeginingReservation(),
				cottageReservationNewDTO.getTimeOfEndingReservation()).getSeconds() / 86400)
				* cottageForReservation.getPricePerNight() * coefficient);
		cottageReservation.setAdditionalServicesFromClient(getSetAdditionalServicesByListAdditionalServicesNames(
				cottageReservationNewDTO.getNamesOfAdditionalsServices()));
		return cottageReservation;
	}

	private Set<AdditionalServices> getSetAdditionalServicesByListAdditionalServicesNames(List<String> list) {
		Set<AdditionalServices> additionalServices = new HashSet<AdditionalServices>();
		list.forEach(nameIt -> additionalServices.add(getAdditionalServicesByName(nameIt)));
		return additionalServices;
	}

	private AdditionalServices getAdditionalServicesByName(String name) {
		return iAdditionalServicesRepository.findAll().stream()
				.filter(additionalServicesIt -> additionalServicesIt.getName().equals(name)).findFirst().orElse(null);
	}
	
	@Override
	public List<CottageReservation> getHistoryCottageReservationsForClient(Client client) {
		return iCottageReservationRepository.findAll().stream()
				.filter(cottageReservationIt ->cottageReservationIt.getClientForReservation().getId()==client.getId() && cottageReservationIt.getTimeOfEndingReservation()
				.isBefore(LocalDateTime.now()) && cottageReservationIt.getStatusOfReservation() != StatusOfReservation.MISSED)
				.collect(Collectors.toList());
	}
}
