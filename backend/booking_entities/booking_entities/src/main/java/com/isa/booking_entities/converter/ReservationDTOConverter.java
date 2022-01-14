package com.isa.booking_entities.converter;

import java.util.ArrayList;
import java.util.List;

import com.isa.booking_entities.dtos.ReservationFutureDisplayDTO;
import com.isa.booking_entities.models.reservations.Reservation;

public class ReservationDTOConverter {
	public ReservationDTOConverter() {
		// TODO Auto-generated constructor stub
	}

	public List<ReservationFutureDisplayDTO> convertListReservationToListReservationFutureDisplayDTO(
			List<Reservation> reservations) {
		List<ReservationFutureDisplayDTO> futureDisplayDTOs = new ArrayList<ReservationFutureDisplayDTO>();
		for (Reservation reservationIt : reservations) {
			futureDisplayDTOs.add(
					new ReservationFutureDisplayDTO(reservationIt.getId(), reservationIt.getTimeOfBeginingReservation(),
							reservationIt.getTimeOfEndingReservation(), reservationIt.getTotalPrice(),
							reservationIt.getStatusOfReservation(), reservationIt.getTypeOfReservation()));
		}
		return futureDisplayDTOs;
	}
}
