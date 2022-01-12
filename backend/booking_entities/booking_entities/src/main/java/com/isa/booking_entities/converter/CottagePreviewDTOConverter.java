package com.isa.booking_entities.converter;

import java.util.ArrayList;
import java.util.List;

import com.isa.booking_entities.dtos.CottagePreviewDTO;
import com.isa.booking_entities.models.entites.Cottage;

public class CottagePreviewDTOConverter {
	public CottagePreviewDTOConverter() {
		// TODO Auto-generated constructor stub
	}
	
	public List<CottagePreviewDTO> convertListCottageToListCottagePreviewDTO(List<Cottage> cottages) {
		List<CottagePreviewDTO> cottagePreviewDTOs = new ArrayList<CottagePreviewDTO>();
		for (Cottage cottageIt : cottages) {
			cottagePreviewDTOs.add(new CottagePreviewDTO(cottageIt .getId(),cottageIt.getTitle(), cottageIt.getAddress(), cottageIt.getPromotionalDescription(), cottageIt.getAverageGrade()));
		}
		return cottagePreviewDTOs;
	}
}
