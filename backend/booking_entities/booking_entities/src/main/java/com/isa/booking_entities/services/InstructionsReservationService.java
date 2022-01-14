package com.isa.booking_entities.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.converter.InstructionsReservationDTOConverter;
import com.isa.booking_entities.converter.InstructionsReservationDTOConverter;
import com.isa.booking_entities.dtos.InstructionsReservationNewDTO;
import com.isa.booking_entities.dtos.InstructionsReservationHistoryDTO;
import com.isa.booking_entities.models.SystemParameters;
import com.isa.booking_entities.models.entites.AdditionalServices;
import com.isa.booking_entities.models.entites.Instructions;
import com.isa.booking_entities.models.reservations.InstructionsReservation;
import com.isa.booking_entities.models.reservations.InstructionsReservation;
import com.isa.booking_entities.models.reservations.StatusOfReservation;
import com.isa.booking_entities.models.reservations.TypeOfReservation;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.models.users.StatusOfUser;
import com.isa.booking_entities.repositories.IAdditionalServicesRepository;
import com.isa.booking_entities.repositories.IInstructionsReservationRepository;
import com.isa.booking_entities.repositories.ISystemParametersRepository;
import com.isa.booking_entities.services.interfaces.IInstructionsReservationService;

@Service
public class InstructionsReservationService implements IInstructionsReservationService {

	private IInstructionsReservationRepository iInstructionsReservationRepository;

	private InstructionsReservationDTOConverter instructionsReservationDTOConverter;
	
	private IAdditionalServicesRepository iAdditionalServicesRepository;
	
	private ISystemParametersRepository iSystemParametersRepository;
	
	@Autowired
	public InstructionsReservationService(IInstructionsReservationRepository iInstructionsReservationRepository) {
		this.iInstructionsReservationRepository = iInstructionsReservationRepository;
		this.instructionsReservationDTOConverter = new InstructionsReservationDTOConverter();
		this.iAdditionalServicesRepository = iAdditionalServicesRepository;
		this.iSystemParametersRepository = iSystemParametersRepository;
	}

	@Override
	public InstructionsReservation getById(long id) {
		return iInstructionsReservationRepository.findById(id).orElse(null);
	}

	@Override
	public InstructionsReservation save(InstructionsReservation instructionsReservation) {
		return iInstructionsReservationRepository.save(instructionsReservation);
	}

	@Override
	public List<InstructionsReservationHistoryDTO> getHistoryOfInstructionsReservations(String emailOfClient) {
		return instructionsReservationDTOConverter.convertListInstructionsReservationToListInstructionsReservationHistoryDTO(
				iInstructionsReservationRepository.findAll().stream().filter(instructionsReservationIt -> 
				instructionsReservationIt.getClientForReservation().getEmail().equals(emailOfClient)
						&& (instructionsReservationIt.getStatusOfReservation() == StatusOfReservation.CANCELED
								|| instructionsReservationIt.getStatusOfReservation() == StatusOfReservation.FINISHED
								|| instructionsReservationIt.getStatusOfReservation() == StatusOfReservation.MISSED
								|| instructionsReservationIt.getTimeOfEndingReservation().isBefore(LocalDateTime.now())))
						.collect(Collectors.toList()));
	}
	
	@Override
	public InstructionsReservation createReservation(InstructionsReservationNewDTO instructionsReservationNewDTO, Instructions instructionsForReservation, Client clientForReservation) {
		InstructionsReservation instructionsReservation = new InstructionsReservation();
		SystemParameters systemParameters = iSystemParametersRepository.findAll().stream().findFirst().orElse(null);
		double coefficient = clientForReservation.getStatuseOfUser() == StatusOfUser.REGULAR
				? (100 - systemParameters.getDiscountForRegular()) / 100
				: (clientForReservation.getStatuseOfUser() == StatusOfUser.SILVER
						? (100 - systemParameters.getDiscountForSilver()) / 100
						: (100 - systemParameters.getDiscountForGold()) / 100);
		instructionsReservation.setNumberOfPerson(instructionsReservationNewDTO.getNumberOfPerson());
		instructionsReservation.setClientForReservation(clientForReservation);
		instructionsReservation.setInstructionsForReservation(instructionsForReservation);
		instructionsReservation.setStatusOfReservation(StatusOfReservation.CREATED);
		instructionsReservation.setTypeOfReservation(TypeOfReservation.INSTRUCTIONS_RESERVATION);
		instructionsReservation.setTimeOfBeginingReservation(instructionsReservationNewDTO.getTimeOfBeginingReservation());
		instructionsReservation.setTimeOfEndingReservation(instructionsReservationNewDTO.getTimeOfEndingReservation());
		instructionsReservation.setTotalPrice((Duration.between(instructionsReservationNewDTO.getTimeOfBeginingReservation(),
				instructionsReservationNewDTO.getTimeOfEndingReservation()).getSeconds() / 3600)* instructionsForReservation.getPricePerHour() * coefficient);
		instructionsReservation.setAdditionalServicesFromClient(getSetAdditionalServicesByListAdditionalServicesNames(instructionsReservationNewDTO.getNamesOfAdditionalsServices()));
		return instructionsReservation;
	}

	private Set<AdditionalServices> getSetAdditionalServicesByListAdditionalServicesNames(List<String> list){
		Set<AdditionalServices> additionalServices = new HashSet<AdditionalServices>();
		list.forEach(nameIt -> additionalServices.add(getAdditionalServicesByName(nameIt)));
		return additionalServices;
	}
	
	private AdditionalServices getAdditionalServicesByName(String name) {
		return iAdditionalServicesRepository.findAll().stream().filter(additionalServicesIt -> additionalServicesIt.getName().equals(name)).findFirst().orElse(null);
	}
}
