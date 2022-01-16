package com.isa.booking_entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.dtos.CottageComplaintResponseNewDTO;
import com.isa.booking_entities.models.complaints.CottageComplaint;
import com.isa.booking_entities.models.complaints.CottageComplaintResponse;
import com.isa.booking_entities.models.complaints.TypeOfComplaintResponse;
import com.isa.booking_entities.models.users.SystemAdmin;
import com.isa.booking_entities.repositories.ICottageComplaintResponseRepository;
import com.isa.booking_entities.services.interfaces.ICottageComplaintResponseService;

@Service
public class CottageComplaintResponseService implements ICottageComplaintResponseService {
	
	private ICottageComplaintResponseRepository iCottageComplaintResponseRepository;
	
	@Autowired
	public CottageComplaintResponseService(ICottageComplaintResponseRepository iCottageComplaintResponseRepository) {
		this.iCottageComplaintResponseRepository = iCottageComplaintResponseRepository;
	}

	@Override
	public CottageComplaintResponse save(CottageComplaintResponse cottageComplaintResponse) {
		return iCottageComplaintResponseRepository.save(cottageComplaintResponse);
	}

	@Override
	public CottageComplaintResponse getById(long id) {
		return iCottageComplaintResponseRepository.findById(id).orElse(null);
	}

	@Override
	public CottageComplaintResponse createCottageComplain(CottageComplaintResponseNewDTO cottageComplaintResponseNewDTO,
			SystemAdmin systemAdmin, CottageComplaint cottageComplaint) {
		CottageComplaintResponse cottageComplaintResponse = new CottageComplaintResponse();
		cottageComplaintResponse.setCottageComplaintForComplaintResponse(cottageComplaint);
		cottageComplaintResponse.setSystemAdminWhoCreateResponse(systemAdmin);
		cottageComplaintResponse.setText(cottageComplaintResponseNewDTO.getText());
		cottageComplaintResponse.setTypeOfComplaintResponse(TypeOfComplaintResponse.COTTAGE_COMPLAINT_RESPONSE);
		return cottageComplaintResponse;
	}

}
