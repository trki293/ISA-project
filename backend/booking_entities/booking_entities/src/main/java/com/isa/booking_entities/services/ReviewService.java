package com.isa.booking_entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.models.rating.Review;
import com.isa.booking_entities.repositories.IReviewRepository;
import com.isa.booking_entities.services.interfaces.IReviewService;

@Service
public class ReviewService implements IReviewService {
	
	private IReviewRepository iReviewRepository;
	
	@Autowired
	public ReviewService(IReviewRepository iReviewRepository) {
		this.iReviewRepository = iReviewRepository;
	}

	@Override
	public Review save(Review review) {
		return iReviewRepository.save(review);
	}

}
