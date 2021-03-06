package com.isa.booking_entities.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.converter.InstructionsPreviewDTOConverter;
import com.isa.booking_entities.dtos.EntitySearchReservationDTO;
import com.isa.booking_entities.dtos.InstructionDisplayDTO;
import com.isa.booking_entities.dtos.InstructionsPreviewDTO;
import com.isa.booking_entities.dtos.InstructionsSearchDTO;
import com.isa.booking_entities.models.entites.AdditionalServices;
import com.isa.booking_entities.models.entites.Instructions;
import com.isa.booking_entities.models.reservations.InstructionsAvailabilityPeriod;
import com.isa.booking_entities.models.reservations.InstructionsQuickBooking;
import com.isa.booking_entities.models.reservations.InstructionsReservation;
import com.isa.booking_entities.models.reservations.QuickBooking;
import com.isa.booking_entities.models.reservations.StatusOfReservation;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.repositories.IInstructionsRepository;
import com.isa.booking_entities.services.interfaces.IInstructionsService;

@Service
public class InstructionsService implements IInstructionsService {

	private IInstructionsRepository iInstructionsRepository;

	private InstructionsPreviewDTOConverter instructionsPreviewDTOConverter;

	@Autowired
	public InstructionsService(IInstructionsRepository iInstructionsRepository) {
		this.iInstructionsRepository = iInstructionsRepository;
		this.instructionsPreviewDTOConverter = new InstructionsPreviewDTOConverter();
	}

	@Override
	public Instructions getById(long id) {
		return iInstructionsRepository.findById(id).orElse(null);
	}

	@Override
	public Instructions save(Instructions instructions) {
		return iInstructionsRepository.save(instructions);
	}

	@Override
	public List<InstructionsPreviewDTO> getAllInstructions(InstructionsSearchDTO instructionsSearchDTO) {
		return instructionsPreviewDTOConverter
				.convertListInstructionsToListInstructionsPreviewDTO(iInstructionsRepository.findAll().stream()
						.filter(instructionsIt -> doesInstructionsMeetParameters(instructionsIt, instructionsSearchDTO))
						.collect(Collectors.toList()));
	}

	private Boolean doesInstructionsMeetParameters(Instructions instructions, InstructionsSearchDTO instructionsSearchDTO) {
		Boolean returnValueCity = checkContainsString(instructionsSearchDTO.getCity(),
				instructions.getAddress().getCity());
		Boolean returnValueCountry = checkContainsString(instructionsSearchDTO.getCountry(),
				instructions.getAddress().getCountry());
		Boolean returnValueTitle = checkContainsString(instructionsSearchDTO.getTitle(), instructions.getTitle());
		Boolean returnValueMinPricePerHour = checkMinValueDouble(instructionsSearchDTO.getMinPricePerHour(),
				instructions.getPricePerHour());
		Boolean returnValueMaxPricePerHour = checkMaxValueDouble(instructionsSearchDTO.getMaxPricePerHour(),
				instructions.getPricePerHour());
		Boolean returnValueMinAverageGrade = checkMinValueDouble(instructionsSearchDTO.getMinAverageGrade(),
				instructions.getAverageGrade());
		Boolean returnValueMaxAverageGrade = checkMaxValueDouble(instructionsSearchDTO.getMaxAverageGrade(),
				instructions.getAverageGrade());
		Boolean returnValueMinValueMaxNumberOfPeople = checkMinValueDouble(
				instructionsSearchDTO.getMinValueMaxNumberOfPeople(), instructions.getMaxNumberOfPeople());
		Boolean returnValueMaxValueMaxNumberOfPeople = checkMaxValueDouble(
				instructionsSearchDTO.getMaxValueMaxNumberOfPeople(), instructions.getMaxNumberOfPeople());

		return returnValueMaxAverageGrade && returnValueMinAverageGrade && returnValueMaxPricePerHour
				&& returnValueMinPricePerHour && returnValueMinValueMaxNumberOfPeople
				&& returnValueMaxValueMaxNumberOfPeople && returnValueTitle && returnValueCity && returnValueCountry
				&& !instructions.getDeleted();
	}

	private Boolean checkMinValueDouble(double numberDTO, double numberInstructions) {
		Boolean returnValue = true;
		if (numberDTO != -1) {
			returnValue = numberInstructions >= numberDTO ? true : false;
		}
		return returnValue;
	}

	private Boolean checkMaxValueDouble(double numberDTO, double numberInstructions) {
		Boolean returnValue = true;
		if (numberDTO != -1) {
			returnValue = numberInstructions <= numberDTO ? true : false;
		}
		return returnValue;
	}

	private Boolean checkContainsString(String stringDTO, String stringInstructions) {
		Boolean returnValue = true;
		if (!stringDTO.equals("")) {
			returnValue = stringInstructions.toLowerCase().contains(stringDTO.toLowerCase());
		}
		return returnValue;
	}

	@Override
	public List<InstructionDisplayDTO> getAllInstructionsForClient(
			EntitySearchReservationDTO instructionsSearchReservationDTO, Client client) {
		List<InstructionDisplayDTO> convertedListOfInstructions = instructionsPreviewDTOConverter.convertListInstructionsToListInstructionDisplayDTO(iInstructionsRepository.findAll().stream().filter(
				instructionsIt -> doesInstructionsMeetParameters(instructionsIt, instructionsSearchReservationDTO))
				.collect(Collectors.toList()));
		convertedListOfInstructions.forEach(instructionsDisplayDTOIt -> instructionsDisplayDTOIt.setUserSubscribed(isClientSubscribedOnInstructions(client,instructionsDisplayDTOIt.getId())));
		return convertedListOfInstructions;
	}
	
	private boolean isClientSubscribedOnInstructions(Client client, long boatId) {
		return client.getInstructionsSubscriptions().stream().filter(boatIt -> boatIt.getId()==boatId).findFirst().orElse(null) != null;
	}

	private Boolean doesInstructionsMeetParameters(Instructions instructions,
			EntitySearchReservationDTO instructionsSearchReservationDTO) {
		Boolean returnValueCity = checkContainsString(instructionsSearchReservationDTO.getCity(),
				instructions.getAddress().getCity());
		Boolean returnValueCountry = checkContainsString(instructionsSearchReservationDTO.getCountry(),
				instructions.getAddress().getCountry());
		Boolean returnValueMinPricePerHour = checkMinValueDouble(instructionsSearchReservationDTO.getMinPrice(),
				instructions.getPricePerHour());
		Boolean returnValueMaxPricePerHour = checkMaxValueDouble(instructionsSearchReservationDTO.getMaxPrice(),
				instructions.getPricePerHour());
		Boolean returnValueMinAverageGrade = checkMinValueDouble(instructionsSearchReservationDTO.getMinAverageGrade(),
				instructions.getAverageGrade());
		Boolean returnValueMaxAverageGrade = checkMaxValueDouble(instructionsSearchReservationDTO.getMaxAverageGrade(),
				instructions.getAverageGrade());
		Boolean returnValueIsFreeForPeriod = isThereReservationForInstructions(instructions,
				instructionsSearchReservationDTO);
		Boolean returnValueExistAvailabilityPeriod = isTherePeriodOfAvailabilityForInstruction(instructions,
				instructionsSearchReservationDTO);

		return returnValueMaxAverageGrade && returnValueMinAverageGrade && returnValueMaxPricePerHour
				&& returnValueMinPricePerHour && returnValueIsFreeForPeriod && returnValueExistAvailabilityPeriod
				&& returnValueCity && returnValueCountry && !instructions.getDeleted();
	}

	private Boolean isTherePeriodOfAvailabilityForInstruction(Instructions instruction,
			EntitySearchReservationDTO entitySearchReservationDTO) {
		if (entitySearchReservationDTO.getBeginDate()==null && entitySearchReservationDTO.getEndDate()==null) return true;
		InstructionsAvailabilityPeriod instructionsAvailabilityPeriod = instruction.getInstructionsAvailabilityPeriods()
				.stream()
				.filter(availabilityPeriodIt -> (entitySearchReservationDTO.getBeginDate()
						.isAfter(availabilityPeriodIt.getBeginPeriod())
						&& entitySearchReservationDTO.getEndDate().isBefore(availabilityPeriodIt.getEndPeriod())))
				.findFirst().orElse(null);

		return instructionsAvailabilityPeriod == null ? false : true;
	}

	private Boolean isThereReservationForInstructions(Instructions instructions,
			EntitySearchReservationDTO entitySearchReservationDTO) {
		if (entitySearchReservationDTO.getBeginDate()==null && entitySearchReservationDTO.getEndDate()==null) return true;
		List<InstructionsReservation> instructionsReservation = instructions.getInstructionsReservations().stream()
				.filter(reservationIt -> isReservationBeforOrAfterPeriod(entitySearchReservationDTO, reservationIt))
				.collect(Collectors.toList());

		List<QuickBooking> quickBookings = instructions.getInstructionsQuickBookings().stream()
				.filter(quickBookingIt -> isQuickBookingBeforOrAfterPeriod(entitySearchReservationDTO, quickBookingIt))
				.collect(Collectors.toList());
		return (instructionsReservation.size() != instructions.getInstructionsReservations().size()
				|| quickBookings.size() != instructions.getInstructionsQuickBookings().size()) ? false : true;
	}

	private boolean isReservationBeforOrAfterPeriod(EntitySearchReservationDTO entitySearchReservationDTO,
			InstructionsReservation reservationIt) {
		return reservationIt.getStatusOfReservation()!=StatusOfReservation.CREATED || (reservationIt.getTimeOfBeginingReservation().isAfter(entitySearchReservationDTO.getBeginDate())
				&& reservationIt.getTimeOfBeginingReservation().isAfter(entitySearchReservationDTO.getEndDate()))
				|| (reservationIt.getTimeOfEndingReservation().isBefore(entitySearchReservationDTO.getBeginDate())
						&& reservationIt.getTimeOfEndingReservation()
								.isBefore(entitySearchReservationDTO.getEndDate()));
	}

	private boolean isQuickBookingBeforOrAfterPeriod(EntitySearchReservationDTO entitySearchReservationDTO,
			InstructionsQuickBooking quickBookingIt) {
		return (quickBookingIt.getTimeOfBeginingReservation().isAfter(entitySearchReservationDTO.getBeginDate())
				&& quickBookingIt.getTimeOfBeginingReservation().isAfter(entitySearchReservationDTO.getEndDate()))
				|| (quickBookingIt.getTimeOfEndingReservation().isBefore(entitySearchReservationDTO.getBeginDate())
						&& quickBookingIt.getTimeOfEndingReservation()
								.isBefore(entitySearchReservationDTO.getEndDate()));
	}

	@Override
	public List<String> getAdditionalServicesForInstructions(long id) throws Exception {
		Instructions instructions = iInstructionsRepository.findById(id).orElse(null);
		if (instructions==null) {
			throw new Exception("Don't exist instructions with id '"+id+"'!");
		}
		return getNamesOfAdditionalServices(instructions.getAdditionalServices());
	}
	
	private List<String> getNamesOfAdditionalServices(Set<AdditionalServices> additionalServices){
		List<String> list = new ArrayList<String>();
		additionalServices.forEach(additionalServiceIt -> list.add(additionalServiceIt.getName()));
		return list;
	}

	@Override
	public List<InstructionsPreviewDTO> getAllNonDeletedInstructions() {
		return instructionsPreviewDTOConverter.convertListInstructionsToListInstructionsPreviewDTO(iInstructionsRepository.findAll().stream().filter(instructionsIt -> !instructionsIt.getDeleted()).collect(Collectors.toList()));
	}
	
}
