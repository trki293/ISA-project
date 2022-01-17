package com.isa.booking_entities.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.dtos.InstructionsReviewNewDTO;
import com.isa.booking_entities.models.entites.Instructions;
import com.isa.booking_entities.models.entites.Instructions;
import com.isa.booking_entities.models.rating.InstructionsReview;
import com.isa.booking_entities.models.rating.InstructionsReview;
import com.isa.booking_entities.models.rating.StatusOfReview;
import com.isa.booking_entities.models.rating.TypeOfReview;
import com.isa.booking_entities.models.reservations.InstructionsReservation;
import com.isa.booking_entities.models.users.Instructor;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.models.users.Instructor;
import com.isa.booking_entities.repositories.IInstructionsReviewRepository;
import com.isa.booking_entities.services.interfaces.IInstructionsReviewService;

@Service
public class InstructionsReviewService implements IInstructionsReviewService {


	private IInstructionsReviewRepository iInstructionsReviewRepository;
	
	@Autowired
	public InstructionsReviewService(IInstructionsReviewRepository iInstructionsReviewRepository) {
		this.iInstructionsReviewRepository = iInstructionsReviewRepository;
	}

	@Override
	public InstructionsReview save(InstructionsReview instructionsReview) {
		return iInstructionsReviewRepository.save(instructionsReview);
	}

	@Override
	public InstructionsReview createInstructionsReview(InstructionsReviewNewDTO instructionsReviewNewDTO,
			InstructionsReservation instructionsReservation, Client client) {
		InstructionsReview instructionsReview = new InstructionsReview();
		instructionsReview.setClientWhoEvaluating(client);
		instructionsReview.setContent(instructionsReviewNewDTO.getContent());
		instructionsReview.setInstructorForReview(instructionsReservation.getInstructionsForReservation().getInstructor());
		instructionsReview.setPublished(false);
		instructionsReview.setRating(instructionsReviewNewDTO.getRating());
		instructionsReview.setReservationBeingEvaluated(instructionsReservation);
		instructionsReview.setTypeOfReview(TypeOfReview.INSTRUCTIONS_REVIEW);
		instructionsReview.setStatusOfReview(StatusOfReview.CREATED);
		return instructionsReview;
	}

	@Override
	public List<InstructionsReview> getAllForInstructions(Instructions instructions) {
		return iInstructionsReviewRepository.findAll().stream().filter(instructionsReviewIt -> instructionsReviewIt.getInstructionsForReview().getId()==instructions.getId()).collect(Collectors.toList());
	}

	@Override
	public InstructionsReview getById(long id) {
		return iInstructionsReviewRepository.findById(id).orElse(null);
	}

	@Override
	public List<InstructionsReview> getOnlyPublishedForInstructions(Instructions instructions) {
		return iInstructionsReviewRepository.findAll().stream()
				.filter(instructionsReviewIt -> instructionsReviewIt.getStatusOfReview() == StatusOfReview.PUBLISHED
						&& instructionsReviewIt.getInstructionsForReview().getId() == instructions.getId())
				.collect(Collectors.toList());
	}
	
	@Override
	public List<InstructionsReview> getOnlyPublishedForInstructor(Instructor instructor) {
		return iInstructionsReviewRepository.findAll().stream()
				.filter(instructionsReviewIt -> instructionsReviewIt.getStatusOfReview() == StatusOfReview.PUBLISHED
						&& instructionsReviewIt.getInstructionsForReview().getInstructor().getId() == instructor.getId())
				.collect(Collectors.toList());
	}
}
