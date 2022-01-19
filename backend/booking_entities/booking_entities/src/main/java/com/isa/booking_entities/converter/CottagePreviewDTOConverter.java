package com.isa.booking_entities.converter;

import java.util.ArrayList;
import java.util.List;

import com.isa.booking_entities.dtos.CottageDisplayDTO;
import com.isa.booking_entities.dtos.CottagePreviewDTO;
import com.isa.booking_entities.models.entites.Cottage;

public class CottagePreviewDTOConverter {
	public CottagePreviewDTOConverter() {
		// TODO Auto-generated constructor stub
	}
	
	public List<CottagePreviewDTO> convertListCottageToListCottagePreviewDTO(List<Cottage> cottages) {
		List<CottagePreviewDTO> cottagePreviewDTOs = new ArrayList<CottagePreviewDTO>();
		for (Cottage cottageIt : cottages) {
			cottagePreviewDTOs.add(new CottagePreviewDTO(cottageIt .getId(),cottageIt.getTitle(), cottageIt.getAddress(), cottageIt.getPromotionalDescription(), cottageIt.getAverageGrade(),cottageIt.getPricePerNight()));
		}
		return cottagePreviewDTOs;
	}

	public List<CottageDisplayDTO> convertListCottageToListCottageDisplayDTO(List<Cottage> cottages) {
		List<CottageDisplayDTO> cottageDisplayDTOs = new ArrayList<CottageDisplayDTO>();
		for (Cottage cottageIt : cottages) {
			cottageDisplayDTOs.add(new CottageDisplayDTO(cottageIt .getId(),cottageIt.getTitle(), cottageIt.getAverageGrade(), cottageIt.getPricePerNight(),cottageIt.getAddress(), cottageIt.getNumberOfRooms(),cottageIt.getNumberOfBeds()));
		}
		return cottageDisplayDTOs;
	}
}
