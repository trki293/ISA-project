package com.isa.booking_entities.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isa.booking_entities.dtos.InstructionsReviewNewDTO;
import com.isa.booking_entities.models.entites.Instructions;
import com.isa.booking_entities.models.rating.InstructionsReview;
import com.isa.booking_entities.models.rating.StatusOfReview;
import com.isa.booking_entities.models.reservations.InstructionsReservation;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.services.EmailService;
import com.isa.booking_entities.services.interfaces.IClientService;
import com.isa.booking_entities.services.interfaces.IInstructionsReservationService;
import com.isa.booking_entities.services.interfaces.IInstructionsReviewService;
import com.isa.booking_entities.services.interfaces.IInstructionsService;
import com.isa.booking_entities.services.interfaces.IInstructorService;
import com.isa.booking_entities.services.interfaces.IReviewService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/instructions_reviews", produces = MediaType.APPLICATION_JSON_VALUE)
public class InstructionsReviewController {

	private IInstructionsReviewService iInstructionsReviewService;

	private IReviewService iReviewService;

	private IClientService iClientService;

	private IInstructionsService iInstructionsService;
	
	private IInstructorService iInstructorService;
	
	private IInstructionsReservationService iInstructionsReservationService;

	private EmailService emailService;

	@Autowired
	public InstructionsReviewController(IInstructorService iInstructorService, IInstructionsReviewService iInstructionsReviewService, IReviewService iReviewService,
			IClientService iClientService, IInstructionsService iInstructionsService, IInstructionsReservationService iInstructionsReservationService,
			EmailService emailService) {
		this.iInstructionsReviewService = iInstructionsReviewService;
		this.iReviewService = iReviewService;
		this.iClientService = iClientService;
		this.iInstructionsService = iInstructionsService;
		this.iInstructionsReservationService = iInstructionsReservationService;
		this.emailService = emailService;
		this.iInstructorService = iInstructorService;
	}

	@PostMapping("/create")
	public ResponseEntity<Boolean> createInstructionsReview(@RequestBody InstructionsReviewNewDTO instructionsReviewNewDTO) {
		Client client = iClientService.getByEmail(instructionsReviewNewDTO.getClientEmail());
		InstructionsReservation instructionsReservation = iInstructionsReservationService.getById(instructionsReviewNewDTO.getInstructionsReservationId());
		InstructionsReview instructionsReview = iInstructionsReviewService.createInstructionsReview(instructionsReviewNewDTO, instructionsReservation, client);
		Instructions instructions = iInstructionsService.getById(instructionsReservation.getInstructionsForReservation().getId());
		instructionsReview.setInstructionsForReview(instructions);
		iReviewService.save(iInstructionsReviewService.save(instructionsReview));
		instructions.setAverageGrade(getAverageGradeForInstructions(instructions));
		iInstructionsService.save(instructions);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private double getAverageGradeForInstructions(Instructions instructions) {
		List<InstructionsReview> instructionsReviews = iInstructionsReviewService.getAllForInstructions(instructions);
		int sumGrade = 0;
		for (InstructionsReview instructionsReview : instructionsReviews) {
			sumGrade += instructionsReview.getRating();
		}
		return sumGrade / instructionsReviews.size();

	}

	@PutMapping(value = "/publish/{reviewId}/{publishType}", consumes = "application/json")
	public ResponseEntity<Boolean> publish(@PathVariable long reviewId, @PathVariable String publishType) {
		InstructionsReview instructionsReview = iInstructionsReviewService.getById(reviewId);
		instructionsReview.setPublished(publishType.toLowerCase().equals("true") ? true : false);
		instructionsReview.setStatusOfReview(
				publishType.toLowerCase().equals("true") ? StatusOfReview.PUBLISHED : StatusOfReview.NOT_PUBLISHED);
		iReviewService.save(iInstructionsReviewService.save(instructionsReview));

		if (instructionsReview.getStatusOfReview() == StatusOfReview.PUBLISHED) {
			try {
				emailService.sendMailAboutPublishReview(instructionsReview.getClientWhoEvaluating().getEmail());
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (MailException | InterruptedException e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/getOnlyPublishedInstructionsReviewsForInstructions/{instructionsId}")
	public ResponseEntity<List<InstructionsReview>> getOnlyPublishedInstructionsReviewsForInstructions(@PathVariable long instructionsId){
		return new ResponseEntity<List<InstructionsReview>>(iInstructionsReviewService.getOnlyPublishedForInstructions(iInstructionsService.getById(instructionsId)), HttpStatus.OK) ;
	}
	
	@GetMapping(value = "/getOnlyPublishedInstructionsReviewsForInstructor/{email}")
	public ResponseEntity<List<InstructionsReview>> getOnlyPublishedInstructionsReviewsForInstructor(@PathVariable String email){
		return new ResponseEntity<List<InstructionsReview>>(iInstructionsReviewService.getOnlyPublishedForInstructor(iInstructorService.getByEmail(email)), HttpStatus.OK) ;
	}
}
