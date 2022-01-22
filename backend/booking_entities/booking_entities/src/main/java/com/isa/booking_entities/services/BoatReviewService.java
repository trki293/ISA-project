package com.isa.booking_entities.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.dtos.BoatReviewNewDTO;
import com.isa.booking_entities.models.entites.Boat;
import com.isa.booking_entities.models.rating.BoatReview;
import com.isa.booking_entities.models.rating.StatusOfReview;
import com.isa.booking_entities.models.rating.TypeOfReview;
import com.isa.booking_entities.models.reservations.BoatReservation;
import com.isa.booking_entities.models.users.BoatOwner;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.repositories.IBoatReviewRepository;
import com.isa.booking_entities.services.interfaces.IBoatReviewService;

@Service
public class BoatReviewService implements IBoatReviewService {

	private IBoatReviewRepository iBoatReviewRepository;

	@Autowired
	public BoatReviewService(IBoatReviewRepository iBoatReviewRepository) {
		this.iBoatReviewRepository = iBoatReviewRepository;
	}

	@Override
	public BoatReview save(BoatReview boatReview) {
		return iBoatReviewRepository.save(boatReview);
	}

	@Override
	public BoatReview createBoatReview(BoatReviewNewDTO boatReviewNewDTO, BoatReservation boatReservation,
			Client client) {
		BoatReview boatReview = new BoatReview();
		boatReview.setClientWhoEvaluating(client);
		boatReview.setContent(boatReviewNewDTO.getContent());
		boatReview.setBoatForReview(boatReservation.getBoatForReservation());
		boatReview.setPublished(false);
		boatReview.setRating(boatReviewNewDTO.getRating());
		boatReview.setReservationBeingEvaluated(boatReservation);
		boatReview.setTypeOfReview(TypeOfReview.BOAT_REVIEW);
		boatReview.setStatusOfReview(StatusOfReview.CREATED);
		return boatReview;
	}

	@Override
	public List<BoatReview> getAllForBoat(Boat boat) {
		return iBoatReviewRepository.findAll().stream()
				.filter(boatReviewIt -> boatReviewIt.getBoatForReview().getId() == boat.getId())
				.collect(Collectors.toList());
	}

	@Override
	public BoatReview getById(long id) {
		return iBoatReviewRepository.findById(id).orElse(null);
	}

	@Override
	public List<BoatReview> getOnlyPublishedForBoat(Boat boat) {
		return iBoatReviewRepository.findAll().stream()
				.filter(boatReviewIt -> boatReviewIt.getStatusOfReview() == StatusOfReview.PUBLISHED
						&& boatReviewIt.getBoatForReview().getId() == boat.getId())
				.collect(Collectors.toList());
	}
	
	@Override
	public List<BoatReview> getOnlyPublishedForBoatOwner(BoatOwner boatOwner) {
		return iBoatReviewRepository.findAll().stream()
				.filter(boatReviewIt -> boatReviewIt.getStatusOfReview() == StatusOfReview.PUBLISHED
						&& boatReviewIt.getBoatForReview().getOwnerOfBoat().getId() == boatOwner.getId())
				.collect(Collectors.toList());
	}

	@Override
	public List<BoatReview> getOnlyBoatReviewsForSystemAdmin() {
		return iBoatReviewRepository.findAll().stream().filter(boatReviewIt-> boatReviewIt.getStatusOfReview() == StatusOfReview.CREATED).collect(Collectors.toList());
	}
}
