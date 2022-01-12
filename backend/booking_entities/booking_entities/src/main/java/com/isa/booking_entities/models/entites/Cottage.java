package com.isa.booking_entities.models.entites;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isa.booking_entities.models.Address;
import com.isa.booking_entities.models.complaints.CottageComplaint;
import com.isa.booking_entities.models.rating.CottageReview;
import com.isa.booking_entities.models.reservations.BoatReservation;
import com.isa.booking_entities.models.reservations.CottageAvailabilityPeriod;
import com.isa.booking_entities.models.reservations.CottageQuickBooking;
import com.isa.booking_entities.models.reservations.CottageReservation;
import com.isa.booking_entities.models.users.CottageOwner;

@Entity
@Table(name = "cottages")
public class Cottage {
	@Id
	@SequenceGenerator(name = "mySeqGenCottage", sequenceName = "mySeqCottage", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenCottage")
	private long id;

	@Column(name = "title", unique = false, nullable = false)
	private String title;

	private Address address;

	@Column(name = "promotionalDescription", unique = false, nullable = true)
	private String promotionalDescription;

	@Column(name = "picturesPaths", unique = false, nullable = true)
	private String picturesPaths; // trenutno zamisljeno kao string sa pathovima razdvojenim nekim karakterom,
									// posle mozda bude promjenjeno kao i sa Address

	@Column(name = "numberOfRooms", unique = false, nullable = false)
	private int numberOfRooms;

	@Column(name = "numberOfBeds", unique = false, nullable = false)
	private int numberOfBeds;

	@Column(name = "rulesOfConduct", unique = false, nullable = true)
	private String rulesOfConduct;

	@Column(name = "pricePerNight", unique = false, nullable = false)
	private double pricePerNight;
	
	@Column(name = "averageGrade", unique = false, nullable = false)
	private double averageGrade;
	
	@Column(name = "deleted", unique = false, nullable = false)
	private Boolean deleted;

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<AdditionalServices> additionalServices = new HashSet<AdditionalServices>();

	@JsonManagedReference
	@OneToMany(mappedBy = "cottageForQuickReservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CottageQuickBooking> cottageQuickBookings = new HashSet<CottageQuickBooking>();

	@JsonManagedReference
	@OneToMany(mappedBy = "cottageForReservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CottageReservation> cottageReservations = new HashSet<CottageReservation>();

	@JsonManagedReference
	@OneToMany(mappedBy = "cottageForReview", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CottageReview> cottageReviews = new HashSet<CottageReview>();

	@JsonManagedReference
	@OneToMany(mappedBy = "cottageForAvailabilityPeriod", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CottageAvailabilityPeriod> cottageAvailabilityPeriods = new HashSet<CottageAvailabilityPeriod>();

	@JsonManagedReference
	@OneToMany(mappedBy = "cottageForComplaint", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CottageComplaint> cottageComplaints = new HashSet<CottageComplaint>();

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private CottageOwner ownerOfCottage;

	public Cottage() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPromotionalDescription() {
		return promotionalDescription;
	}

	public void setPromotionalDescription(String promotionalDescription) {
		this.promotionalDescription = promotionalDescription;
	}

	public String getPicturesPaths() {
		return picturesPaths;
	}

	public void setPicturesPaths(String picturesPaths) {
		this.picturesPaths = picturesPaths;
	}

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public String getRulesOfConduct() {
		return rulesOfConduct;
	}

	public void setRulesOfConduct(String rulesOfConduct) {
		this.rulesOfConduct = rulesOfConduct;
	}

	public double getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	public Set<AdditionalServices> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(Set<AdditionalServices> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public Set<CottageQuickBooking> getCottageQuickBookings() {
		return cottageQuickBookings;
	}

	public void setCottageQuickBookings(Set<CottageQuickBooking> cottageQuickBookings) {
		this.cottageQuickBookings = cottageQuickBookings;
	}

	public Set<CottageReservation> getCottageReservations() {
		return cottageReservations;
	}

	public void setCottageReservations(Set<CottageReservation> cottageReservations) {
		this.cottageReservations = cottageReservations;
	}

	public Set<CottageReview> getCottageReviews() {
		return cottageReviews;
	}

	public void setCottageReviews(Set<CottageReview> cottageReviews) {
		this.cottageReviews = cottageReviews;
	}

	public Set<CottageAvailabilityPeriod> getCottageAvailabilityPeriods() {
		return cottageAvailabilityPeriods;
	}

	public void setCottageAvailabilityPeriods(Set<CottageAvailabilityPeriod> cottageAvailabilityPeriods) {
		this.cottageAvailabilityPeriods = cottageAvailabilityPeriods;
	}

	public Set<CottageComplaint> getCottageComplaints() {
		return cottageComplaints;
	}

	public void setCottageComplaints(Set<CottageComplaint> cottageComplaints) {
		this.cottageComplaints = cottageComplaints;
	}

	public CottageOwner getOwnerOfCottage() {
		return ownerOfCottage;
	}

	public void setOwnerOfCottage(CottageOwner ownerOfCottage) {
		this.ownerOfCottage = ownerOfCottage;
	}

	public double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}
