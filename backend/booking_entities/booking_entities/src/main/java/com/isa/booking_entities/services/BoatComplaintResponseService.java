package com.isa.booking_entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.dtos.BoatComplaintResponseNewDTO;
import com.isa.booking_entities.models.complaints.BoatComplaint;
import com.isa.booking_entities.models.complaints.BoatComplaintResponse;
import com.isa.booking_entities.models.complaints.TypeOfComplaintResponse;
import com.isa.booking_entities.models.users.SystemAdmin;
import com.isa.booking_entities.repositories.IBoatComplaintResponseRepository;
import com.isa.booking_entities.services.interfaces.IBoatComplaintResponseService;

@Service
public class BoatComplaintResponseService implements IBoatComplaintResponseService {

	private IBoatComplaintResponseRepository iBoatComplaintResponseRepository;
	
	@Autowired
	public BoatComplaintResponseService(IBoatComplaintResponseRepository iBoatComplaintResponseRepository) {
		this.iBoatComplaintResponseRepository = iBoatComplaintResponseRepository;
	}

	@Override
	public BoatComplaintResponse save(BoatComplaintResponse boatComplaintResponse) {
		return iBoatComplaintResponseRepository.save(boatComplaintResponse);
	}

	@Override
	public BoatComplaintResponse getById(long id) {
		return iBoatComplaintResponseRepository.findById(id).orElse(null);
	}

	@Override
	public BoatComplaintResponse createBoatComplain(BoatComplaintResponseNewDTO boatComplaintResponseNewDTO,
			SystemAdmin systemAdmin, BoatComplaint boatComplaint) {
		BoatComplaintResponse boatComplaintResponse = new BoatComplaintResponse();
		boatComplaintResponse.setBoatComplaintForComplaintResponse(boatComplaint);
		boatComplaintResponse.setSystemAdminWhoCreateResponse(systemAdmin);
		boatComplaintResponse.setText(boatComplaintResponseNewDTO.getText());
		boatComplaintResponse.setTypeOfComplaintResponse(TypeOfComplaintResponse.BOAT_COMPLAINT_RESPONSE);
		return boatComplaintResponse;
	}

}
