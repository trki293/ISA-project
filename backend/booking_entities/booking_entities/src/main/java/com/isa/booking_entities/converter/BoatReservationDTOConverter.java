package com.isa.booking_entities.converter;

import java.util.ArrayList;
import java.util.List;

import com.isa.booking_entities.dtos.BoatReservationHistoryDTO;
import com.isa.booking_entities.models.reservations.BoatReservation;

public class BoatReservationDTOConverter {
	public BoatReservationDTOConverter() {
		// TODO Auto-generated constructor stub
	}

	public List<BoatReservationHistoryDTO> convertListBoatReservationToListBoatReservationHistoryDTO(
			List<BoatReservation> boatReservations) {
		List<BoatReservationHistoryDTO> boatReservationHistoryDTOs = new ArrayList<BoatReservationHistoryDTO>();
		for (BoatReservation boatReservationIt : boatReservations) {
			boatReservationHistoryDTOs
					.add(new BoatReservationHistoryDTO(boatReservationIt.getId(),boatReservationIt.getTimeOfBeginingReservation(),
							boatReservationIt.getTimeOfEndingReservation(), boatReservationIt.getBoatForReservation(),
							boatReservationIt.getTotalPrice(), boatReservationIt.getStatusOfReservation()));
		}
		return boatReservationHistoryDTOs;
	}
}
