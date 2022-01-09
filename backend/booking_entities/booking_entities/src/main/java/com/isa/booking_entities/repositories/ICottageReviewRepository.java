package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.rating.CottageReview;

public interface ICottageReviewRepository extends JpaRepository<CottageReview, Long> {

}
