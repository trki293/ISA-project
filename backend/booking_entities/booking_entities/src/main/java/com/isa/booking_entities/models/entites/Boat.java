package com.isa.booking_entities.models.entites;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import com.isa.booking_entities.models.complaints.BoatComplaint;
import com.isa.booking_entities.models.rating.BoatReview;
import com.isa.booking_entities.models.reservations.BoatAvailabilityPeriod;
import com.isa.booking_entities.models.reservations.BoatQuickBooking;
import com.isa.booking_entities.models.reservations.BoatReservation;
import com.isa.booking_entities.models.users.BoatOwner;

@Entity
@Table(name = "boats")
public class Boat {
	@Id
	@SequenceGenerator(name = "mySeqGenBoat", sequenceName = "mySeqBoat", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenBoat")
	private long id;

	@Column(name = "title", unique = false, nullable = false)
	private String title;

	@Enumerated(EnumType.ORDINAL)
	private TypeOfBoat typeOfBoat;

	@Column(name = "length", unique = false, nullable = false)
	private double length;

	@Column(name = "averageGrade", unique = false, nullable = false)
	private double averageGrade;
	
	@Column(name = "deleted", unique = false, nullable = false)
	private Boolean deleted;
	
	@Column(name = "engineNumber", unique = false, nullable = false)
	private int engineNumber;

	@Column(name = "enginePower", unique = false, nullable = false)
	private double enginePower;

	@Column(name = "maxSpeed", unique = false, nullable = false)
	private double maxSpeed;

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<NavigationEquipment> navigationEquipment = new HashSet<NavigationEquipment>();

	private Address address;

	@Column(name = "promotionalDescription", unique = false, nullable = true)
	private String promotionalDescription;

	@Column(name = "picturesPaths", unique = false, nullable = true)
	private String picturesPaths; // trenutno zamisljeno kao string sa pathovima razdvojenim nekim karakterom,
									// posle mozda bude promjenjeno kao i sa Address

	@Column(name = "capacity", unique = false, nullable = false)
	private int capacity;

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<FishingEquipment> fishingEquipment = new HashSet<FishingEquipment>();

	@Column(name = "rulesOfConduct", unique = false, nullable = true)
	private String rulesOfConduct;

	@Column(name = "pricePerHour", unique = false, nullable = false)
	private double pricePerHour;

	@Column(name = "percentageOfEarningsWhenCanceling", unique = false, nullable = false)
	private double percentageOfEarningsWhenCanceling;

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<AdditionalServices> additionalServices = new HashSet<AdditionalServices>();

	@JsonManagedReference(value="boat-quick_booking")
	@OneToMany(mappedBy = "boatForQuickReservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<BoatQuickBooking> boatQuickBookings = new HashSet<BoatQuickBooking>();

	@JsonManagedReference(value="boat-reservation")
	@OneToMany(mappedBy = "boatForReservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<BoatReservation> boatReservations = new HashSet<BoatReservation>();

	@JsonManagedReference(value="boat-review")
	@OneToMany(mappedBy = "boatForReview", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<BoatReview> boatReviews = new HashSet<BoatReview>();

	@JsonManagedReference(value="boat-availability_periods")
	@OneToMany(mappedBy = "boatForAvailabilityPeriod", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<BoatAvailabilityPeriod> boatAvailabilityPeriods = new HashSet<BoatAvailabilityPeriod>();

	@JsonManagedReference(value="boat-complaint")
	@OneToMany(mappedBy = "boatForComplaint", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<BoatComplaint> boatComplaints = new HashSet<BoatComplaint>();

	@JsonBackReference(value="boat-owner")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private BoatOwner ownerOfBoat;

	public Boat() {
		// TODO Auto-generated constructor stub
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

	public TypeOfBoat getTypeOfBoat() {
		return typeOfBoat;
	}

	public void setTypeOfBoat(TypeOfBoat typeOfBoat) {
		this.typeOfBoat = typeOfBoat;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public int getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(int engineNumber) {
		this.engineNumber = engineNumber;
	}

	public double getEnginePower() {
		return enginePower;
	}

	public void setEnginePower(double enginePower) {
		this.enginePower = enginePower;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public Set<NavigationEquipment> getNavigationEquipment() {
		return navigationEquipment;
	}

	public void setNavigationEquipment(Set<NavigationEquipment> navigationEquipment) {
		this.navigationEquipment = navigationEquipment;
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

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Set<FishingEquipment> getFishingEquipment() {
		return fishingEquipment;
	}

	public void setFishingEquipment(Set<FishingEquipment> fishingEquipment) {
		this.fishingEquipment = fishingEquipment;
	}

	public String getRulesOfConduct() {
		return rulesOfConduct;
	}

	public void setRulesOfConduct(String rulesOfConduct) {
		this.rulesOfConduct = rulesOfConduct;
	}

	public double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public double getPercentageOfEarningsWhenCanceling() {
		return percentageOfEarningsWhenCanceling;
	}

	public void setPercentageOfEarningsWhenCanceling(double percentageOfEarningsWhenCanceling) {
		this.percentageOfEarningsWhenCanceling = percentageOfEarningsWhenCanceling;
	}

	public Set<AdditionalServices> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(Set<AdditionalServices> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public Set<BoatQuickBooking> getBoatQuickBookings() {
		return boatQuickBookings;
	}

	public void setBoatQuickBookings(Set<BoatQuickBooking> boatQuickBookings) {
		this.boatQuickBookings = boatQuickBookings;
	}

	public Set<BoatReservation> getBoatReservations() {
		return boatReservations;
	}

	public void setBoatReservations(Set<BoatReservation> boatReservations) {
		this.boatReservations = boatReservations;
	}

	public Set<BoatReview> getBoatReviews() {
		return boatReviews;
	}

	public void setBoatReviews(Set<BoatReview> boatReviews) {
		this.boatReviews = boatReviews;
	}

	public Set<BoatAvailabilityPeriod> getBoatAvailabilityPeriods() {
		return boatAvailabilityPeriods;
	}

	public void setBoatAvailabilityPeriods(Set<BoatAvailabilityPeriod> boatAvailabilityPeriods) {
		this.boatAvailabilityPeriods = boatAvailabilityPeriods;
	}

	public Set<BoatComplaint> getBoatComplaints() {
		return boatComplaints;
	}

	public void setBoatComplaints(Set<BoatComplaint> boatComplaints) {
		this.boatComplaints = boatComplaints;
	}

	public BoatOwner getOwnerOfBoat() {
		return ownerOfBoat;
	}

	public void setOwnerOfBoat(BoatOwner ownerOfBoat) {
		this.ownerOfBoat = ownerOfBoat;
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
