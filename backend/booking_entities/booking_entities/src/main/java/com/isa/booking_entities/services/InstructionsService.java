package com.isa.booking_entities.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.isa.booking_entities.converter.InstructionsPreviewDTOConverter;
import com.isa.booking_entities.dtos.EntitySearchReservationDTO;
import com.isa.booking_entities.dtos.InstructionDisplayDTO;
import com.isa.booking_entities.dtos.InstructionsPreviewDTO;
import com.isa.booking_entities.dtos.InstructionsSearchDTO;
import com.isa.booking_entities.models.entites.Instructions;
import com.isa.booking_entities.models.reservations.InstructionsAvailabilityPeriod;
import com.isa.booking_entities.models.reservations.InstructionsQuickBooking;
import com.isa.booking_entities.models.reservations.InstructionsReservation;
import com.isa.booking_entities.models.reservations.QuickBooking;
import com.isa.booking_entities.repositories.IInstructionsRepository;
import com.isa.booking_entities.services.interfaces.IInstructionsService;

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
			EntitySearchReservationDTO instructionsSearchReservationDTO) {
		return instructionsPreviewDTOConverter
				.convertListInstructionsToListInstructionDisplayDTO(iInstructionsRepository.findAll().stream().filter(
						instructionIt -> doesInstructionsMeetParameters(instructionIt, instructionsSearchReservationDTO))
						.collect(Collectors.toList()));
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
		Boolean returnValueIsFreeForPeriod = isThereReservationForInstruction(instructions,
				instructionsSearchReservationDTO);
		Boolean returnValueExistAvailabilityPeriod = isTherePeriodOfAvailabilityForInstruction(instructions,
				instructionsSearchReservationDTO);

		return returnValueMaxAverageGrade && returnValueMinAverageGrade && returnValueMaxPricePerHour
				&& returnValueMinPricePerHour && returnValueIsFreeForPeriod && returnValueExistAvailabilityPeriod
				&& returnValueCity && returnValueCountry && !instructions.getDeleted();
	}

	private Boolean isTherePeriodOfAvailabilityForInstruction(Instructions instruction,
			EntitySearchReservationDTO entitySearchReservationDTO) {
		InstructionsAvailabilityPeriod instructionsAvailabilityPeriod = instruction.getInstructionsAvailabilityPeriods()
				.stream()
				.filter(availabilityPeriodIt -> (entitySearchReservationDTO.getBeginDate()
						.isAfter(availabilityPeriodIt.getBeginPeriod())
						&& entitySearchReservationDTO.getEndDate().isBefore(availabilityPeriodIt.getEndPeriod())))
				.findFirst().orElse(null);

		return instructionsAvailabilityPeriod == null ? false : true;
	}

	private Boolean isThereReservationForInstruction(Instructions instruction,
			EntitySearchReservationDTO entitySearchReservationDTO) {
		List<InstructionsReservation> instructionsReservation = instruction.getInstructionsReservations().stream()
				.filter(reservationIt -> isReservationBeforOrAfterPeriod(entitySearchReservationDTO, reservationIt))
				.collect(Collectors.toList());

		List<QuickBooking> quickBookings = instruction.getInstructionsQuickBookings().stream()
				.filter(quickBookingIt -> isQuickBookingBeforOrAfterPeriod(entitySearchReservationDTO, quickBookingIt))
				.collect(Collectors.toList());
		return (instructionsReservation.size() != instruction.getInstructionsReservations().size()
				|| quickBookings.size() != instruction.getInstructionsQuickBookings().size()) ? false : true;
	}

	private boolean isReservationBeforOrAfterPeriod(EntitySearchReservationDTO entitySearchReservationDTO,
			InstructionsReservation reservationIt) {
		return (reservationIt.getTimeOfBeginingReservation().isBefore(entitySearchReservationDTO.getBeginDate())
				&& reservationIt.getTimeOfBeginingReservation().isBefore(entitySearchReservationDTO.getEndDate()))
				|| (reservationIt.getTimeOfBeginingReservation().isAfter(entitySearchReservationDTO.getBeginDate())
						&& reservationIt.getTimeOfBeginingReservation()
								.isAfter(entitySearchReservationDTO.getEndDate()));
	}

	private boolean isQuickBookingBeforOrAfterPeriod(EntitySearchReservationDTO entitySearchReservationDTO,
			InstructionsQuickBooking quickBookingIt) {
		return (quickBookingIt.getTimeOfBeginingReservation().isBefore(entitySearchReservationDTO.getBeginDate())
				&& quickBookingIt.getTimeOfBeginingReservation().isBefore(entitySearchReservationDTO.getEndDate()))
				|| (quickBookingIt.getTimeOfBeginingReservation().isAfter(entitySearchReservationDTO.getBeginDate())
						&& quickBookingIt.getTimeOfBeginingReservation()
								.isAfter(entitySearchReservationDTO.getEndDate()));
	}

}
