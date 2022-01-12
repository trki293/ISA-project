package com.isa.booking_entities.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.converter.BoatPreviewDTOConverter;
import com.isa.booking_entities.dtos.BoatPreviewDTO;
import com.isa.booking_entities.dtos.BoatSearchDTO;
import com.isa.booking_entities.models.entites.Boat;
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

}
