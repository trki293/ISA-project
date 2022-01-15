package com.isa.booking_entities.converter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import com.isa.booking_entities.dtos.BoatQuickBookingDisplayDTO;
import com.isa.booking_entities.dtos.CottageQuickBookingDisplayDTO;
import com.isa.booking_entities.dtos.InstructionsQuickBookingDisplayDTO;
import com.isa.booking_entities.models.reservations.BoatQuickBooking;
import com.isa.booking_entities.models.reservations.CottageQuickBooking;
import com.isa.booking_entities.models.reservations.InstructionsQuickBooking;

public class QuickBookingDTOConverter {
	public QuickBookingDTOConverter() {
		// TODO Auto-generated constructor stub
	}

	public List<InstructionsQuickBookingDisplayDTO> convertListInstructionsQuickBookingToListInstructionsQuickBookingDisplayDTO(
			List<InstructionsQuickBooking> instructionsQuickBookings,double coefficent) {
		List<InstructionsQuickBookingDisplayDTO> bookingDisplayDTOs = new ArrayList<InstructionsQuickBookingDisplayDTO>();
		instructionsQuickBookings.forEach(instructionsQuickBookingIt -> bookingDisplayDTOs.add(new InstructionsQuickBookingDisplayDTO(instructionsQuickBookingIt.getId(),instructionsQuickBookingIt.getInstructionsForQuickReservation(),instructionsQuickBookingIt.getTimeOfBeginingReservation() , instructionsQuickBookingIt.getTimeOfEndingReservation(), getOriginalPrice(coefficent,instructionsQuickBookingIt) , instructionsQuickBookingIt.getTotalPrice()*coefficent)));
		return bookingDisplayDTOs;
	}
	
	public List<CottageQuickBookingDisplayDTO> convertListCottageQuickBookingToListCottageQuickBookingDisplayDTO(
			List<CottageQuickBooking> cottageQuickBookings,double coefficent) {
		List<CottageQuickBookingDisplayDTO> bookingDisplayDTOs = new ArrayList<CottageQuickBookingDisplayDTO>();
		cottageQuickBookings.forEach(cottageQuickBookingIt -> bookingDisplayDTOs.add(new CottageQuickBookingDisplayDTO(cottageQuickBookingIt.getId(),cottageQuickBookingIt.getCottageForQuickReservation(),cottageQuickBookingIt.getTimeOfBeginingReservation() , cottageQuickBookingIt.getTimeOfEndingReservation(), getOriginalPrice(coefficent,cottageQuickBookingIt) , cottageQuickBookingIt.getTotalPrice()*coefficent)));
		return bookingDisplayDTOs;
	}
	
	public List<BoatQuickBookingDisplayDTO> convertListBoatQuickBookingToListBoatQuickBookingDisplayDTO(
			List<BoatQuickBooking> boatQuickBookings,double coefficent) {
		List<BoatQuickBookingDisplayDTO> bookingDisplayDTOs = new ArrayList<BoatQuickBookingDisplayDTO>();
		boatQuickBookings.forEach(boatQuickBookingIt -> bookingDisplayDTOs.add(new BoatQuickBookingDisplayDTO(boatQuickBookingIt.getId(),boatQuickBookingIt.getBoatForQuickReservation(),boatQuickBookingIt.getTimeOfBeginingReservation() , boatQuickBookingIt.getTimeOfEndingReservation(), getOriginalPrice(coefficent,boatQuickBookingIt) , boatQuickBookingIt.getTotalPrice()*coefficent)));
		return bookingDisplayDTOs;
	}

	private double getOriginalPrice(double coefficent, InstructionsQuickBooking instructionsQuickBooking) {
		return (Duration.between(instructionsQuickBooking.getTimeOfBeginingReservation(), instructionsQuickBooking.getTimeOfEndingReservation()).getSeconds()/3600)*instructionsQuickBooking.getInstructionsForQuickReservation().getPricePerHour()*coefficent;
	}
	
	private double getOriginalPrice(double coefficent, CottageQuickBooking cottageQuickBooking) {
		return (Duration.between(cottageQuickBooking.getTimeOfBeginingReservation(), cottageQuickBooking.getTimeOfEndingReservation()).getSeconds()/86400)*cottageQuickBooking.getCottageForQuickReservation().getPricePerNight()*coefficent;
	}
	
	private double getOriginalPrice(double coefficent, BoatQuickBooking boatQuickBooking) {
		return (Duration.between(boatQuickBooking.getTimeOfBeginingReservation(), boatQuickBooking.getTimeOfEndingReservation()).getSeconds()/3600)*boatQuickBooking.getBoatForQuickReservation().getPricePerHour()*coefficent;
	}
}
