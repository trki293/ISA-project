package com.isa.booking_entities.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.dtos.BoatComplaintNewDTO;
import com.isa.booking_entities.models.complaints.BoatComplaint;
import com.isa.booking_entities.models.complaints.StatusOfComplaint;
import com.isa.booking_entities.models.complaints.TypeOfComplaint;
import com.isa.booking_entities.models.entites.Boat;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.repositories.IBoatComplaintRepository;
import com.isa.booking_entities.services.interfaces.IBoatComplaintService;

@Service
public class BoatComplaintService implements IBoatComplaintService {

	@Autowired
	private IBoatComplaintRepository iBoatComplaintRepository;
	
	@Override
	public BoatComplaint save(BoatComplaint boatComplaint) {
		return iBoatComplaintRepository.save(boatComplaint);
	}

	@Override
	public BoatComplaint getById(long id) {
		return iBoatComplaintRepository.findById(id).orElse(null);
	}

	@Override
	public BoatComplaint createBoatComplaintByBoatComplaintNewDTO(BoatComplaintNewDTO boatComplaintNewDTO,
			Client client, Boat boat) {
		BoatComplaint boatComplaint = new BoatComplaint();
		boatComplaint.setBoatForComplaint(boat);
		boatComplaint.setClientWhoCreateComplaint(client);
		boatComplaint.setStatusOfComplaint(StatusOfComplaint.WAITING_FOR_RESPONSE);
		boatComplaint.setText(boatComplaintNewDTO.getText());
		boatComplaint.setTypeOfComplaint(TypeOfComplaint.BOAT_COMPLAINT);
		return boatComplaint;
	}

	@Override
	public List<BoatComplaint> getBoatComplaints() {
		return iBoatComplaintRepository.findAll().stream().filter(boatComplaintIt -> boatComplaintIt.getStatusOfComplaint()==StatusOfComplaint.WAITING_FOR_RESPONSE).collect(Collectors.toList());
	}

}
