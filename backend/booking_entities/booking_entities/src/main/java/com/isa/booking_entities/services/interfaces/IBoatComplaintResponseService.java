package com.isa.booking_entities.services.interfaces;

import com.isa.booking_entities.dtos.BoatComplaintResponseNewDTO;
import com.isa.booking_entities.models.complaints.BoatComplaint;
import com.isa.booking_entities.models.complaints.BoatComplaintResponse;
import com.isa.booking_entities.models.users.SystemAdmin;

public interface IBoatComplaintResponseService {
	BoatComplaintResponse save(BoatComplaintResponse boatComplaintResponse);
	BoatComplaintResponse getById(long id);
	BoatComplaintResponse createBoatComplain(BoatComplaintResponseNewDTO boatComplaintResponseNewDTO, SystemAdmin systemAdmin, BoatComplaint boatComplaint);
}
