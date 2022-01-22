package com.isa.booking_entities.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.dtos.CottageReviewNewDTO;
import com.isa.booking_entities.models.entites.Cottage;
import com.isa.booking_entities.models.rating.CottageReview;
import com.isa.booking_entities.models.rating.StatusOfReview;
import com.isa.booking_entities.models.rating.TypeOfReview;
import com.isa.booking_entities.models.reservations.CottageReservation;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.models.users.CottageOwner;
import com.isa.booking_entities.repositories.ICottageReviewRepository;
import com.isa.booking_entities.services.interfaces.ICottageReviewService;

@Service
public class CottageReviewService implements ICottageReviewService {
	
	private ICottageReviewRepository iCottageReviewRepository;
	
	@Autowired
	public CottageReviewService(ICottageReviewRepository iCottageReviewRepository) {
		this.iCottageReviewRepository = iCottageReviewRepository;
	}

	@Override
	public CottageReview save(CottageReview cottageReview) {
		return iCottageReviewRepository.save(cottageReview);
	}

	@Override
	public CottageReview createCottageReview(CottageReviewNewDTO cottageReviewNewDTO,
			CottageReservation cottageReservation, Client client) {
		CottageReview cottageReview = new CottageReview();
		cottageReview.setClientWhoEvaluating(client);
		cottageReview.setContent(cottageReviewNewDTO.getContent());
		cottageReview.setCottageForReview(cottageReservation.getCottageForReservation());
		cottageReview.setPublished(false);
		cottageReview.setRating(cottageReviewNewDTO.getRating());
		cottageReview.setReservationBeingEvaluated(cottageReservation);
		cottageReview.setTypeOfReview(TypeOfReview.COTTAGE_REVIEW);
		cottageReview.setStatusOfReview(StatusOfReview.CREATED);
		return cottageReview;
	}

	@Override
	public List<CottageReview> getAllForCottage(Cottage cottage) {
		return iCottageReviewRepository.findAll().stream().filter(cottageReviewIt -> cottageReviewIt.getCottageForReview().getId()==cottage.getId()).collect(Collectors.toList());
	}

	@Override
	public CottageReview getById(long id) {
		return iCottageReviewRepository.findById(id).orElse(null);
	}
	
	@Override
	public List<CottageReview> getOnlyPublishedForCottage(Cottage cottage) {
		return iCottageReviewRepository.findAll().stream()
				.filter(cottageReviewIt -> cottageReviewIt.getStatusOfReview() == StatusOfReview.PUBLISHED
						&& cottageReviewIt.getCottageForReview().getId() == cottage.getId())
				.collect(Collectors.toList());
	}
	
	@Override
	public List<CottageReview> getOnlyPublishedForCottageOwner(CottageOwner cottageOwner) {
		return iCottageReviewRepository.findAll().stream()
				.filter(cottageReviewIt -> cottageReviewIt.getStatusOfReview() == StatusOfReview.PUBLISHED
						&& cottageReviewIt.getCottageForReview().getOwnerOfCottage().getId() == cottageOwner.getId())
				.collect(Collectors.toList());
	}

	@Override
	public List<CottageReview> getOnlyCottageReviewsForSystemAdmin() {
		return iCottageReviewRepository.findAll().stream().filter(cottageReviewIt -> cottageReviewIt.getStatusOfReview()==StatusOfReview.CREATED).collect(Collectors.toList());
	}

}
