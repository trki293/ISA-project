package com.isa.booking_entities.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.isa.booking_entities.converter.CottagePreviewDTOConverter;
import com.isa.booking_entities.dtos.CottagePreviewDTO;
import com.isa.booking_entities.dtos.CottageSearchDTO;
import com.isa.booking_entities.models.entites.Cottage;
import com.isa.booking_entities.repositories.ICottageRepository;
import com.isa.booking_entities.services.interfaces.ICottageService;

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
}
