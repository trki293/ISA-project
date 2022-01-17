package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.dtos.BoatReviewNewDTO;
import com.isa.booking_entities.models.entites.Boat;
import com.isa.booking_entities.models.rating.BoatReview;
import com.isa.booking_entities.models.reservations.BoatReservation;
import com.isa.booking_entities.models.users.BoatOwner;
import com.isa.booking_entities.models.users.Client;

public interface IBoatReviewService {
	BoatReview save(BoatReview boatReview);
	BoatReview createBoatReview(BoatReviewNewDTO boatReviewNewDTO, BoatReservation boatReservation, Client client);
	List<BoatReview> getAllForBoat(Boat boat);
	BoatReview getById(long id);
	List<BoatReview> getOnlyPublishedForBoat(Boat boat);
	List<BoatReview> getOnlyPublishedForBoatOwner(BoatOwner boatOwner);
}
