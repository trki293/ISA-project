package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.dtos.CottageReviewNewDTO;
import com.isa.booking_entities.models.entites.Cottage;
import com.isa.booking_entities.models.rating.CottageReview;
import com.isa.booking_entities.models.reservations.CottageReservation;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.models.users.CottageOwner;

public interface ICottageReviewService {
	CottageReview save(CottageReview cottageReview);
	CottageReview createCottageReview(CottageReviewNewDTO cottageReviewNewDTO, CottageReservation cottageReservation, Client client);
	List<CottageReview> getAllForCottage(Cottage cottage);
	CottageReview getById(long id);
	List<CottageReview> getOnlyPublishedForCottage(Cottage cottage);
	List<CottageReview> getOnlyPublishedForCottageOwner(CottageOwner cottageOwner);
	List<CottageReview> getOnlyCottageReviewsForSystemAdmin();
}
