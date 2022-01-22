package com.isa.booking_entities.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isa.booking_entities.dtos.CottageReviewNewDTO;
import com.isa.booking_entities.models.entites.Cottage;
import com.isa.booking_entities.models.rating.CottageReview;
import com.isa.booking_entities.models.rating.StatusOfReview;
import com.isa.booking_entities.models.reservations.CottageReservation;
import com.isa.booking_entities.models.users.Client;
import com.isa.booking_entities.services.EmailService;
import com.isa.booking_entities.services.interfaces.IClientService;
import com.isa.booking_entities.services.interfaces.ICottageOwnerService;
import com.isa.booking_entities.services.interfaces.ICottageReservationService;
import com.isa.booking_entities.services.interfaces.ICottageReviewService;
import com.isa.booking_entities.services.interfaces.ICottageService;
import com.isa.booking_entities.services.interfaces.IReviewService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/cottage_reviews", produces = MediaType.APPLICATION_JSON_VALUE)
public class CottageReviewController {
	
	private ICottageReviewService iCottageReviewService;

	private IReviewService iReviewService;

	private IClientService iClientService;

	private ICottageService iCottageService;

	private ICottageOwnerService iCottageOwnerService;
	
	private ICottageReservationService iCottageReservationService;

	private EmailService emailService;

	@Autowired
	public CottageReviewController(ICottageOwnerService iCottageOwnerService,ICottageReviewService iCottageReviewService, IReviewService iReviewService,
			IClientService iClientService, ICottageService iCottageService, ICottageReservationService iCottageReservationService,
			EmailService emailService) {
		this.iCottageReviewService = iCottageReviewService;
		this.iReviewService = iReviewService;
		this.iClientService = iClientService;
		this.iCottageService = iCottageService;
		this.iCottageReservationService = iCottageReservationService;
		this.emailService = emailService;
		this.iCottageOwnerService = iCottageOwnerService;
	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping("/create")
	public ResponseEntity<Boolean> createCottageReview(@RequestBody CottageReviewNewDTO cottageReviewNewDTO) {
		Client client = iClientService.getByEmail(cottageReviewNewDTO.getClientEmail());
		CottageReservation cottageReservation = iCottageReservationService.getById(cottageReviewNewDTO.getCottageReservationId());
		CottageReview cottageReview = iCottageReviewService.createCottageReview(cottageReviewNewDTO, cottageReservation, client);
		iReviewService.save(iCottageReviewService.save(cottageReview));
		Cottage cottage = iCottageService.getById(cottageReservation.getCottageForReservation().getId());
		cottage.setAverageGrade(getAverageGradeForCottage(cottage));
		iCottageService.save(cottage);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private double getAverageGradeForCottage(Cottage cottage) {
		List<CottageReview> cottageReviews = iCottageReviewService.getAllForCottage(cottage);
		double sumGrade = 0;
		for (CottageReview cottageReview : cottageReviews) {
			sumGrade += cottageReview.getRating();
		}
		return sumGrade / cottageReviews.size();

	}

	@PutMapping(value = "/publish/{reviewId}/{publishType}", consumes = "application/json")
	public ResponseEntity<Boolean> publish(@PathVariable long reviewId, @PathVariable String publishType) {
		CottageReview cottageReview = iCottageReviewService.getById(reviewId);
		cottageReview.setPublished(publishType.toLowerCase().equals("true") ? true : false);
		cottageReview.setStatusOfReview(
				publishType.toLowerCase().equals("true") ? StatusOfReview.PUBLISHED : StatusOfReview.NOT_PUBLISHED);
		iReviewService.save(iCottageReviewService.save(cottageReview));

		if (cottageReview.getStatusOfReview() == StatusOfReview.PUBLISHED) {
			try {
				emailService.sendMailAboutPublishReview(cottageReview.getClientWhoEvaluating().getEmail());
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (MailException | InterruptedException e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/getOnlyPublishedCottageReviewsForCottage/{cottageId}")
	public ResponseEntity<List<CottageReview>> getOnlyPublishedCottageReviewsForCottage(@PathVariable long cottageId){
		return new ResponseEntity<List<CottageReview>>(iCottageReviewService.getOnlyPublishedForCottage(iCottageService.getById(cottageId)), HttpStatus.OK) ;
	}
	
	
	@GetMapping(value = "/getOnlyCottageReviewsForSystemAdmin")
	public ResponseEntity<List<CottageReview>> getOnlyCottageReviewsForSystemAdmin(){
		return new ResponseEntity<List<CottageReview>>(iCottageReviewService.getOnlyCottageReviewsForSystemAdmin(), HttpStatus.OK) ;
	}
	
	@GetMapping(value = "/getOnlyPublishedCottageReviewsForCottageOwner/{email}")
	public ResponseEntity<List<CottageReview>> getOnlyPublishedCottageReviewsForCottageOwner(@PathVariable String email){
		return new ResponseEntity<List<CottageReview>>(iCottageReviewService.getOnlyPublishedForCottageOwner(iCottageOwnerService.getByEmail(email)), HttpStatus.OK) ;
	}
}
