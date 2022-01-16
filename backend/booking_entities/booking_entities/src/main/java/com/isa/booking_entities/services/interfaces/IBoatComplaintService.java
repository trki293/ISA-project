package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.dtos.BoatComplaintNewDTO;
import com.isa.booking_entities.models.complaints.BoatComplaint;
import com.isa.booking_entities.models.entites.Boat;
import com.isa.booking_entities.models.users.Client;

public interface IBoatComplaintService {
	BoatComplaint save(BoatComplaint boatComplaint);
	BoatComplaint getById(long id);
	BoatComplaint createBoatComplaintByBoatComplaintNewDTO(BoatComplaintNewDTO boatComplaintNewDTO, Client client, Boat boat);
	List<BoatComplaint> getBoatComplaints();
}
