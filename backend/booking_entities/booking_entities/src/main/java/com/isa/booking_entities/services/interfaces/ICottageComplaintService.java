package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.dtos.CottageComplaintNewDTO;
import com.isa.booking_entities.models.complaints.CottageComplaint;
import com.isa.booking_entities.models.entites.Cottage;
import com.isa.booking_entities.models.users.Client;

public interface ICottageComplaintService {
	CottageComplaint save(CottageComplaint cottageComplaint);
	CottageComplaint getById(long id);
	CottageComplaint createCottageComplaintByCottageComplaintNewDTO(CottageComplaintNewDTO cottageComplaintNewDTO,
			Client client, Cottage cottage);
	List<CottageComplaint> getCottageComplaints();
}
