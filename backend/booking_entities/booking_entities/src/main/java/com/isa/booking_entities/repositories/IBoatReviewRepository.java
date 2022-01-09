package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.rating.BoatReview;

public interface IBoatReviewRepository extends JpaRepository<BoatReview, Long> {

}
