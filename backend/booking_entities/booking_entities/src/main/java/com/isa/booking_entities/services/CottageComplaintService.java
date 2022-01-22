package com.isa.booking_entities.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.dtos.CottageComplaintNewDTO;
import com.isa.booking_entities.models.complaints.CottageComplaint;
import com.isa.booking_entities.models.complaints.StatusOfComplaint;
import com.isa.booking_entities.models.complaints.TypeOfComplaint;
import com.isa.booking_entities.models.entites.Cottage;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.repositories.ICottageComplaintRepository;
import com.isa.booking_entities.services.interfaces.ICottageComplaintService;

@Service
public class CottageComplaintService implements ICottageComplaintService {
	
	private ICottageComplaintRepository iCottageComplaintRepository;
	
	@Autowired
	public CottageComplaintService(ICottageComplaintRepository iCottageComplaintRepository) {
		this.iCottageComplaintRepository = iCottageComplaintRepository;
	}

	@Override
	public CottageComplaint save(CottageComplaint cottageComplaint) {
		return iCottageComplaintRepository.save(cottageComplaint);
	}

	@Override
	public CottageComplaint getById(long id) {
		return iCottageComplaintRepository.findById(id).orElse(null); 
	}
	
	@Override
	public CottageComplaint createCottageComplaintByCottageComplaintNewDTO(CottageComplaintNewDTO cottageComplaintNewDTO,
			Client client, Cottage cottage) {
		CottageComplaint cottageComplaint = new CottageComplaint();
		cottageComplaint.setCottageForComplaint(cottage);
		cottageComplaint.setClientWhoCreateComplaint(client);
		cottageComplaint.setStatusOfComplaint(StatusOfComplaint.WAITING_FOR_RESPONSE);
		cottageComplaint.setText(cottageComplaintNewDTO.getText());
		cottageComplaint.setTypeOfComplaint(TypeOfComplaint.COTTAGE_COMPLAINT);
		return cottageComplaint;
	}
	
	@Override
	public List<CottageComplaint> getCottageComplaints() {
		return iCottageComplaintRepository.findAll().stream().filter(cottageComplaintIt -> cottageComplaintIt.getStatusOfComplaint()==StatusOfComplaint.WAITING_FOR_RESPONSE).collect(Collectors.toList());
	}


}
