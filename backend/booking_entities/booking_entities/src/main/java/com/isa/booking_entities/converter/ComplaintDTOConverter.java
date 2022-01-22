package com.isa.booking_entities.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.isa.booking_entities.dtos.BoatComplaintDTO;
import com.isa.booking_entities.dtos.BoatComplaintDisplayDTO;
import com.isa.booking_entities.dtos.CottageComplaintDTO;
import com.isa.booking_entities.dtos.CottageComplaintDisplayDTO;
import com.isa.booking_entities.dtos.InstructorComplaintDTO;
import com.isa.booking_entities.dtos.InstructorComplaintDisplayDTO;
import com.isa.booking_entities.models.complaints.BoatComplaint;
import com.isa.booking_entities.models.complaints.CottageComplaint;
import com.isa.booking_entities.models.complaints.InstructionsComplaint;
import com.isa.booking_entities.models.reservations.BoatReservation;
import com.isa.booking_entities.models.reservations.CottageReservation;
import com.isa.booking_entities.models.reservations.InstructionsReservation;

public class ComplaintDTOConverter {
	public ComplaintDTOConverter() {
		// TODO Auto-generated constructor stub
	}

	public List<InstructorComplaintDTO> convertListInstructionsReservationToListInstructorComplaintDTO(
			List<InstructionsReservation> instructionsReservations) {
		List<InstructorComplaintDTO> complaintDTOs = new ArrayList<InstructorComplaintDTO>();
		List<InstructionsReservation> instructionsReservationNew = new ArrayList<InstructionsReservation>();
		instructionsReservations.forEach(instructionsReservationsIt->addIfDidntExistInList(instructionsReservationNew, instructionsReservationsIt));
		instructionsReservationNew.forEach(instructionsReservationsIt -> complaintDTOs.add(new InstructorComplaintDTO(
				instructionsReservationsIt.getInstructionsForReservation().getInstructor().getId(),
				instructionsReservationsIt.getInstructionsForReservation().getInstructor().getFirstName(),
				instructionsReservationsIt.getInstructionsForReservation().getInstructor().getLastName())));
		return complaintDTOs.stream().distinct().collect(Collectors.toList());
	}

	public List<CottageComplaintDTO> convertListCottageReservationToListCottageComplaintDTO(
			List<CottageReservation> cottageReservations) {
		List<CottageComplaintDTO> complaintDTOs = new ArrayList<CottageComplaintDTO>();
		List<CottageReservation> cottageReservationNew = new ArrayList<CottageReservation>();
		cottageReservations.forEach(cottageReservationsIt->addIfDidntExistInList(cottageReservationNew, cottageReservationsIt));
		cottageReservationNew.forEach(cottageReservationsIt -> complaintDTOs
				.add(new CottageComplaintDTO(cottageReservationsIt.getCottageForReservation().getId(),
						cottageReservationsIt.getCottageForReservation().getTitle(),
						cottageReservationsIt.getCottageForReservation().getOwnerOfCottage().getFirstName(),
						cottageReservationsIt.getCottageForReservation().getOwnerOfCottage().getLastName())));
		return complaintDTOs.stream().distinct().collect(Collectors.toList());
	}

	private void addIfDidntExistInList(List<CottageReservation> cottageReservationNew,
			CottageReservation cottageReservationsIt) {
		if (cottageReservationNew.size() == 0) {
			cottageReservationNew.add(cottageReservationsIt);
		} else {
			for (CottageReservation cottageReservation : cottageReservationNew) {
				if (cottageReservation.getCottageForReservation().getId() == cottageReservationsIt
						.getCottageForReservation().getId())
					return;
			}
			cottageReservationNew.add(cottageReservationsIt);
		}
	}
	
	private void addIfDidntExistInList(List<BoatReservation> boatReservationNew,
			BoatReservation boatReservationsIt) {
		if (boatReservationNew.size() == 0) {
			boatReservationNew.add(boatReservationsIt);
		} else {
			for (BoatReservation boatReservation : boatReservationNew) {
				if (boatReservation.getBoatForReservation().getId() == boatReservationsIt
						.getBoatForReservation().getId())
					return;
			}
			boatReservationNew.add(boatReservationsIt);
		}
	}
	
	private void addIfDidntExistInList(List<InstructionsReservation> instructionsReservationNew,
			InstructionsReservation instructionsReservationsIt) {
		if (instructionsReservationNew.size() == 0) {
			instructionsReservationNew.add(instructionsReservationsIt);
		} else {
			for (InstructionsReservation instructionsReservation : instructionsReservationNew) {
				if (instructionsReservation.getInstructionsForReservation().getId() == instructionsReservationsIt
						.getInstructionsForReservation().getId())
					return;
			}
			instructionsReservationNew.add(instructionsReservationsIt);
		}
	}

	public List<BoatComplaintDTO> convertListBoatReservationToListBoatComplaintDTO(
			List<BoatReservation> boatReservations) {
		List<BoatComplaintDTO> complaintDTOs = new ArrayList<BoatComplaintDTO>();
		List<BoatReservation> boatReservationNew = new ArrayList<BoatReservation>();
		boatReservations.forEach(boatReservationsIt->addIfDidntExistInList(boatReservationNew, boatReservationsIt));
		boatReservationNew.forEach(boatReservationsIt -> complaintDTOs
				.add(new BoatComplaintDTO(boatReservationsIt.getBoatForReservation().getId(),
						boatReservationsIt.getBoatForReservation().getTitle(),
						boatReservationsIt.getBoatForReservation().getOwnerOfBoat().getFirstName(),
						boatReservationsIt.getBoatForReservation().getOwnerOfBoat().getLastName())));
		return complaintDTOs.stream().distinct().collect(Collectors.toList());
	}

	public List<InstructorComplaintDisplayDTO> convertListInstructionsReservationToListInstructorComplaintDisplayDTO(
			List<InstructionsComplaint> instructionsComplaints) {
		List<InstructorComplaintDisplayDTO> complaintDTOs = new ArrayList<InstructorComplaintDisplayDTO>();
		instructionsComplaints.forEach(instructionsComplaintsIt -> complaintDTOs.add(new InstructorComplaintDisplayDTO(
				instructionsComplaintsIt.getId(), instructionsComplaintsIt.getInstructorForComplaint(),
				instructionsComplaintsIt.getClientWhoCreateComplaint())));
		return complaintDTOs;
	}

	public List<CottageComplaintDisplayDTO> convertListCottageReservationToListCottageComplaintDisplayDTO(
			List<CottageComplaint> instructionsComplaints) {
		List<CottageComplaintDisplayDTO> complaintDTOs = new ArrayList<CottageComplaintDisplayDTO>();
		instructionsComplaints.forEach(instructionsComplaintsIt -> complaintDTOs.add(new CottageComplaintDisplayDTO(
				instructionsComplaintsIt.getId(), instructionsComplaintsIt.getClientWhoCreateComplaint(),
				instructionsComplaintsIt.getCottageForComplaint())));
		return complaintDTOs;
	}

	public List<BoatComplaintDisplayDTO> convertListBoatReservationToListBoatComplaintDisplayDTO(
			List<BoatComplaint> instructionsComplaints) {
		List<BoatComplaintDisplayDTO> complaintDTOs = new ArrayList<BoatComplaintDisplayDTO>();
		instructionsComplaints.forEach(instructionsComplaintsIt -> complaintDTOs.add(new BoatComplaintDisplayDTO(
				instructionsComplaintsIt.getId(), instructionsComplaintsIt.getClientWhoCreateComplaint(),
				instructionsComplaintsIt.getBoatForComplaint())));
		return complaintDTOs;
	}
}
