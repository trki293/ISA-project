package com.isa.booking_entities.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.isa.booking_entities.dtos.BoatDisplayDTO;
import com.isa.booking_entities.dtos.BoatPreviewDTO;
import com.isa.booking_entities.models.entites.Boat;
import com.isa.booking_entities.models.users.Client;

public class BoatPreviewDTOConverter {
	public BoatPreviewDTOConverter() {
		// TODO Auto-generated constructor stub
	}
	
	public List<BoatPreviewDTO> convertListBoatToListBoatPreviewDTO(List<Boat> boats) {
		List<BoatPreviewDTO> boatPreviewDTOs = new ArrayList<BoatPreviewDTO>();
		for (Boat boatIt : boats) {
			boatPreviewDTOs.add(new BoatPreviewDTO(boatIt .getId(),boatIt.getTitle(), boatIt.getAddress(), boatIt.getPromotionalDescription(), boatIt.getAverageGrade(), boatIt.getAdditionalServices().stream().collect(Collectors.toList()), boatIt.getPricePerHour()));
		}
		return boatPreviewDTOs;
	}

	public List<BoatDisplayDTO> convertListBoatToListBoatDisplayDTO(List<Boat> boats) {
		List<BoatDisplayDTO> boatDisplayDTOs = new ArrayList<BoatDisplayDTO>();
		for (Boat boatIt : boats) {
			boatDisplayDTOs.add(new BoatDisplayDTO(boatIt .getId(),boatIt.getTitle(),boatIt.getAverageGrade(),boatIt.getPricePerHour(),boatIt.getAddress(),boatIt.getCapacity()));
		}
		return boatDisplayDTOs;
	}
}
