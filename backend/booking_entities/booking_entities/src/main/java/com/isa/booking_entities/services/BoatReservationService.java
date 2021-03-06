package com.isa.booking_entities.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.converter.BoatReservationDTOConverter;
import com.isa.booking_entities.dtos.BoatReservationHistoryDTO;
import com.isa.booking_entities.dtos.BoatReservationNewDTO;
import com.isa.booking_entities.models.SystemParameters;
import com.isa.booking_entities.models.entites.AdditionalServices;
import com.isa.booking_entities.models.entites.Boat;
import com.isa.booking_entities.models.reservations.BoatReservation;
import com.isa.booking_entities.models.reservations.StatusOfReservation;
import com.isa.booking_entities.models.reservations.TypeOfReservation;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.models.users.StatusOfUser;
import com.isa.booking_entities.repositories.IAdditionalServicesRepository;
import com.isa.booking_entities.repositories.IBoatReservationRepository;
import com.isa.booking_entities.repositories.IBoatReviewRepository;
import com.isa.booking_entities.repositories.ISystemParametersRepository;
import com.isa.booking_entities.services.interfaces.IBoatReservationService;

@Service
public class BoatReservationService implements IBoatReservationService {

	private IBoatReservationRepository iBoatReservationRepository;

	private ISystemParametersRepository iSystemParametersRepository;
	
	private IAdditionalServicesRepository iAdditionalServicesRepository;
	
	private BoatReservationDTOConverter boatReservationDTOConverter;
	
	private IBoatReviewRepository iBoatReviewRepository;

	@Autowired
	public BoatReservationService(IBoatReviewRepository iBoatReviewRepository, IBoatReservationRepository iBoatReservationRepository,
			IAdditionalServicesRepository iAdditionalServicesRepository,ISystemParametersRepository iSystemParametersRepository) {
		this.iBoatReservationRepository = iBoatReservationRepository;
		this.boatReservationDTOConverter = new BoatReservationDTOConverter();
		this.iAdditionalServicesRepository = iAdditionalServicesRepository;
		this.iSystemParametersRepository = iSystemParametersRepository;
		this.iBoatReviewRepository = iBoatReviewRepository;
	}

	@Override
	public BoatReservation getById(long id) {
		return iBoatReservationRepository.findById(id).orElse(null);
	}

	@Override
	public BoatReservation save(BoatReservation boatReservation) {
		return iBoatReservationRepository.save(boatReservation);
	}

	@Override
	public List<BoatReservationHistoryDTO> getHistoryOfBoatReservations(String emailOfClient) {
		List<BoatReservationHistoryDTO> convertedListBoatReservation = boatReservationDTOConverter.convertListBoatReservationToListBoatReservationHistoryDTO(
				iBoatReservationRepository.findAll().stream().filter(boatReservationIt -> boatReservationIt
						.getClientForReservation().getEmail().equals(emailOfClient)
						&& (boatReservationIt.getStatusOfReservation() == StatusOfReservation.CANCELED
								|| boatReservationIt.getStatusOfReservation() == StatusOfReservation.FINISHED
								|| boatReservationIt.getStatusOfReservation() == StatusOfReservation.MISSED
								|| boatReservationIt.getTimeOfEndingReservation().isBefore(LocalDateTime.now())))
						.collect(Collectors.toList()));
		convertedListBoatReservation.forEach(boatReservationHistoryDTOIt -> boatReservationHistoryDTOIt.setUserReviewed(isHaveBoatReviewForReservation(emailOfClient, boatReservationHistoryDTOIt.getId())));
		return convertedListBoatReservation;
	}

	@Override
	public BoatReservation createReservation(BoatReservationNewDTO boatReservationNewDTO, Boat boatForReservation, Client clientForReservation) {
		BoatReservation boatReservation = new BoatReservation();
		SystemParameters systemParameters = iSystemParametersRepository.findAll().stream().findFirst().orElse(null);
		double coefficient = clientForReservation.getStatuseOfUser() == StatusOfUser.REGULAR
				? (100 - systemParameters.getDiscountForRegular()) / 100
				: (clientForReservation.getStatuseOfUser() == StatusOfUser.SILVER
						? (100 - systemParameters.getDiscountForSilver()) / 100
						: (100 - systemParameters.getDiscountForGold()) / 100);
		boatReservation.setNumberOfPerson(boatReservationNewDTO.getNumberOfPerson());
		boatReservation.setClientForReservation(clientForReservation);
		boatReservation.setBoatForReservation(boatForReservation);
		boatReservation.setStatusOfReservation(StatusOfReservation.CREATED);
		boatReservation.setTypeOfReservation(TypeOfReservation.BOAT_RESERVATION);
		boatReservation.setTimeOfBeginingReservation(boatReservationNewDTO.getTimeOfBeginingReservation());
		boatReservation.setTimeOfEndingReservation(boatReservationNewDTO.getTimeOfEndingReservation());
		boatReservation.setTotalPrice((Duration.between(boatReservationNewDTO.getTimeOfBeginingReservation(),
				boatReservationNewDTO.getTimeOfEndingReservation()).getSeconds() / 3600)* boatForReservation.getPricePerHour() * coefficient);
		boatReservation.setAdditionalServicesFromClient(getSetAdditionalServicesByListAdditionalServicesNames(boatReservationNewDTO.getNamesOfAdditionalsServices()));
		return boatReservation;
	}

	private Set<AdditionalServices> getSetAdditionalServicesByListAdditionalServicesNames(List<String> list){
		Set<AdditionalServices> additionalServices = new HashSet<AdditionalServices>();
		list.forEach(nameIt -> additionalServices.add(getAdditionalServicesByName(nameIt)));
		return additionalServices;
	}
	
	private AdditionalServices getAdditionalServicesByName(String name) {
		return iAdditionalServicesRepository.findAll().stream().filter(additionalServicesIt -> additionalServicesIt.getName().equals(name)).findFirst().orElse(null);
	}
	
	@Override
	public List<BoatReservation> getHistoryBoatReservationsForClient(Client client) {
		return iBoatReservationRepository.findAll().stream()
				.filter(boatReservationIt ->boatReservationIt.getClientForReservation().getId()==client.getId() && boatReservationIt.getTimeOfEndingReservation()
				.isBefore(LocalDateTime.now()) && boatReservationIt.getStatusOfReservation() != StatusOfReservation.MISSED)
				.collect(Collectors.toList());
	}
	
	private boolean isHaveBoatReviewForReservation(String emailOfClient, long reservationId) {
		return iBoatReviewRepository.findAll().stream().filter(boatReviewIt -> boatReviewIt.getClientWhoEvaluating().getEmail().equals(emailOfClient) && boatReviewIt.getReservationBeingEvaluated().getId()==reservationId).findFirst().orElse(null) != null;
	}
	
}
