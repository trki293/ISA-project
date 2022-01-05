package com.isa.booking_entities.models.rating;

import static javax.persistence.InheritanceType.JOINED;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.reservations.Reservation;
import com.isa.booking_entities.models.users.Client;

@Entity
@Table(name = "reviews")
@Inheritance(strategy = JOINED)
public class Review {
	
	@Id
	@SequenceGenerator(name = "mySeqGenReview", sequenceName = "mySeqReview", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenReview")
	private long id;
	
	@Column(name = "rating", unique = false, nullable = false)
	private int rating;
	
	@Column(name = "content", unique = false, nullable = false)
	private String content;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Client clientWhoEvaluating;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Reservation reservationBeingEvaluated;
	
	@Column(name = "published", unique = false, nullable = false)
	private boolean published;
	
	@Enumerated(EnumType.ORDINAL)
	private TypeOfReview typeOfReview;
	
	public Review() {
		// TODO Auto-generated constructor stub
	}
	
	public Review(long id, int rating, String content, Client clientWhoEvaluating,
			Reservation reservationBeingEvaluated, boolean published) {
		this.id = id;
		this.rating = rating;
		this.content = content;
		this.clientWhoEvaluating = clientWhoEvaluating;
		this.reservationBeingEvaluated = reservationBeingEvaluated;
		this.published = published;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Client getClientWhoEvaluating() {
		return clientWhoEvaluating;
	}
	public void setClientWhoEvaluating(Client clientWhoEvaluating) {
		this.clientWhoEvaluating = clientWhoEvaluating;
	}
	public Reservation getReservationBeingEvaluated() {
		return reservationBeingEvaluated;
	}
	public void setReservationBeingEvaluated(Reservation reservationBeingEvaluated) {
		this.reservationBeingEvaluated = reservationBeingEvaluated;
	}
	public boolean isPublished() {
		return published;
	}
	public void setPublished(boolean published) {
		this.published = published;
	}

}
