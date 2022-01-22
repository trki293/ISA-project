package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.dtos.InstructionsReviewNewDTO;
import com.isa.booking_entities.models.entites.Instructions;
import com.isa.booking_entities.models.rating.InstructionsReview;
import com.isa.booking_entities.models.reservations.InstructionsReservation;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.models.users.Instructor;

public interface IInstructionsReviewService {
	InstructionsReview save(InstructionsReview instructionsReview);
	InstructionsReview createInstructionsReview(InstructionsReviewNewDTO instructionsReviewNewDTO, InstructionsReservation instructionsReservation, Client client);
	List<InstructionsReview> getAllForInstructions(Instructions instructions);
	InstructionsReview getById(long id);
	List<InstructionsReview> getOnlyPublishedForInstructions(Instructions instructions);
	List<InstructionsReview> getOnlyPublishedForInstructor(Instructor instructor);
	List<InstructionsReview> getInstructionsReviewsForSystemAdmin();
}
