package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.rating.Review;

public interface IReviewRepository extends JpaRepository<Review, Long> {

}
