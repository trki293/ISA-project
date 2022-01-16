package com.isa.booking_entities.services.interfaces;

import com.isa.booking_entities.dtos.CottageComplaintResponseNewDTO;
import com.isa.booking_entities.models.complaints.CottageComplaint;
import com.isa.booking_entities.models.complaints.CottageComplaintResponse;
import com.isa.booking_entities.models.users.SystemAdmin;

public interface ICottageComplaintResponseService {
	CottageComplaintResponse save(CottageComplaintResponse cottageComplaintResponse);
	CottageComplaintResponse getById(long id);
	CottageComplaintResponse createCottageComplain(CottageComplaintResponseNewDTO cottageComplaintResponseNewDTO, SystemAdmin systemAdmin, CottageComplaint cottageComplaint);
}
