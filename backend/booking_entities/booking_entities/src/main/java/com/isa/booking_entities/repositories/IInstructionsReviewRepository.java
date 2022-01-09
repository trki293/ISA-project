package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.rating.InstructionsReview;

public interface IInstructionsReviewRepository extends JpaRepository<InstructionsReview, Long> {

}
