package com.isa.booking_entities.converter;

import java.util.ArrayList;
import java.util.List;

import com.isa.booking_entities.dtos.CottageReservationHistoryDTO;
import com.isa.booking_entities.models.reservations.CottageReservation;

public class CottageReservationDTOConverter {
	public CottageReservationDTOConverter() {
		// TODO Auto-generated constructor stub
	}

	public List<CottageReservationHistoryDTO> convertListCottageReservationToListCottageReservationHistoryDTO(
			List<CottageReservation> cottageReservations) {
		List<CottageReservationHistoryDTO> cottageReservationHistoryDTOs = new ArrayList<CottageReservationHistoryDTO>();
		for (CottageReservation cottageReservationIt : cottageReservations) {
			cottageReservationHistoryDTOs
					.add(new CottageReservationHistoryDTO(cottageReservationIt.getId(),cottageReservationIt.getTimeOfBeginingReservation(),
							cottageReservationIt.getTimeOfEndingReservation(), cottageReservationIt.getCottageForReservation(),
							cottageReservationIt.getTotalPrice(), cottageReservationIt.getStatusOfReservation()));
		}
		return cottageReservationHistoryDTOs;
	}
}
