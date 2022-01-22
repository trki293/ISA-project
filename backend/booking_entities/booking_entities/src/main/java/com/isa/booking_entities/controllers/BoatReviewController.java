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

import com.isa.booking_entities.dtos.BoatReviewNewDTO;
import com.isa.booking_entities.models.entites.Boat;
import com.isa.booking_entities.models.rating.BoatReview;
import com.isa.booking_entities.models.rating.StatusOfReview;
import com.isa.booking_entities.models.reservations.BoatReservation;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.services.EmailService;
import com.isa.booking_entities.services.interfaces.IBoatOwnerService;
import com.isa.booking_entities.services.interfaces.IBoatReservationService;
import com.isa.booking_entities.services.interfaces.IBoatReviewService;
import com.isa.booking_entities.services.interfaces.IBoatService;
import com.isa.booking_entities.services.interfaces.IClientService;
import com.isa.booking_entities.services.interfaces.IReviewService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/boat_reviews", produces = MediaType.APPLICATION_JSON_VALUE)
public class BoatReviewController {

	private IBoatReviewService iBoatReviewService;

	private IReviewService iReviewService;

	private IClientService iClientService;

	private IBoatService iBoatService;

	private IBoatOwnerService iBoatOwnerService;
	
	private IBoatReservationService iBoatReservationService;

	private EmailService emailService;

	@Autowired
	public BoatReviewController(IBoatOwnerService iBoatOwnerService, IBoatReviewService iBoatReviewService, IReviewService iReviewService,
			IClientService iClientService, IBoatService iBoatService, IBoatReservationService iBoatReservationService,
			EmailService emailService) {
		this.iBoatReviewService = iBoatReviewService;
		this.iReviewService = iReviewService;
		this.iClientService = iClientService;
		this.iBoatService = iBoatService;
		this.iBoatReservationService = iBoatReservationService;
		this.emailService = emailService;
		this.iBoatOwnerService = iBoatOwnerService;
	}

	@PostMapping("/create")
	public ResponseEntity<Boolean> createBoatReview(@RequestBody BoatReviewNewDTO boatReviewNewDTO) {
		Client client = iClientService.getByEmail(boatReviewNewDTO.getClientEmail());
		BoatReservation boatReservation = iBoatReservationService.getById(boatReviewNewDTO.getBoatReservationId());
		BoatReview boatReview = iBoatReviewService.createBoatReview(boatReviewNewDTO, boatReservation, client);
		iReviewService.save(iBoatReviewService.save(boatReview));
		Boat boat = iBoatService.getById(boatReservation.getBoatForReservation().getId());
		boat.setAverageGrade(getAverageGradeForBoat(boat));
		iBoatService.save(boat);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private double getAverageGradeForBoat(Boat boat) {
		List<BoatReview> boatReviews = iBoatReviewService.getAllForBoat(boat);
		double sumGrade = 0;
		for (BoatReview boatReview : boatReviews) {
			sumGrade += boatReview.getRating();
		}
		return sumGrade / boatReviews.size();

	}

	@PutMapping(value = "/publish/{reviewId}/{publishType}", consumes = "application/json")
	public ResponseEntity<Boolean> publish(@PathVariable long reviewId, @PathVariable String publishType) {
		BoatReview boatReview = iBoatReviewService.getById(reviewId);
		boatReview.setPublished(publishType.toLowerCase().equals("true") ? true : false);
		boatReview.setStatusOfReview(
				publishType.toLowerCase().equals("true") ? StatusOfReview.PUBLISHED : StatusOfReview.NOT_PUBLISHED);
		iReviewService.save(iBoatReviewService.save(boatReview));

		if (boatReview.getStatusOfReview() == StatusOfReview.PUBLISHED) {
			try {
				emailService.sendMailAboutPublishReview(boatReview.getClientWhoEvaluating().getEmail());
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (MailException | InterruptedException e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/getOnlyPublishedBoatReviewsForBoat/{boatId}")
	public ResponseEntity<List<BoatReview>> getOnlyPublishedBoatReviewsForBoat(@PathVariable long boatId){
		return new ResponseEntity<List<BoatReview>>(iBoatReviewService.getOnlyPublishedForBoat(iBoatService.getById(boatId)), HttpStatus.OK) ;
	}
	
	@GetMapping(value = "/getOnlyPublishedBoatReviewsForBoatOwner/{email}")
	public ResponseEntity<List<BoatReview>> getOnlyPublishedBoatReviewsForBoatOwner(@PathVariable String email){
		return new ResponseEntity<List<BoatReview>>(iBoatReviewService.getOnlyPublishedForBoatOwner(iBoatOwnerService.getByEmail(email)), HttpStatus.OK) ;
	}
}
