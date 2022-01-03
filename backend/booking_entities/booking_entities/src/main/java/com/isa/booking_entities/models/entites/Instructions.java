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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isa.booking_entities.models.Address;
import com.isa.booking_entities.models.reservations.InstructionsAvailabilityPeriod;
import com.isa.booking_entities.models.reservations.InstructionsQuickBooking;
import com.isa.booking_entities.models.reservations.InstructionsReservation;

@Entity
@Table(name = "instructions")
public class Instructions {
	@Id
	@SequenceGenerator(name = "mySeqGenInstructions", sequenceName = "mySeqInstructions", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenInstructions")
	private long id;
	
	@Column(name = "length", unique = false, nullable = false)
	private String title;
	
	private Address address;
	
	@Column(name = "promotionalDescription", unique = false, nullable = true)
	private String promotionalDescription;
	
	@Column(name = "instructorBiography", unique = false, nullable = false)
	private String instructorBiography;
	
	@Column(name = "picturesPaths", unique = false, nullable = true)
	private String picturesPaths; //trenutno zamisljeno kao string sa pathovima razdvojenim nekim karakterom, posle mozda bude promjenjeno kao i sa Address
	
	@Column(name = "maxNumberOfPeople", unique = false, nullable = false)
	private int maxNumberOfPeople;
	
	@Column(name = "rulesOfConduct", unique = false, nullable = true)
	private String rulesOfConduct;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<FishingEquipment> fishingEquipment = new HashSet<FishingEquipment>();
	
	@Column(name = "pricePerHour", unique = false, nullable = false)
	private double pricePerHour;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<AdditionalServices> additionalServices = new HashSet<AdditionalServices>();
	
	@Column(name = "percentageOfEarningsWhenCanceling", unique = false, nullable = false)
	private double percentageOfEarningsWhenCanceling;
	
	
	@JsonManagedReference
	@OneToMany(mappedBy = "instructionsForQuickReservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<InstructionsQuickBooking> instructionsQuickBookings = new HashSet<InstructionsQuickBooking>();
	
	@JsonManagedReference
	@OneToMany(mappedBy = "instructionsForReservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<InstructionsReservation> instructionsReservations = new HashSet<InstructionsReservation>();
	
	@JsonManagedReference
	@OneToMany(mappedBy = "instructionsForAvailabilityPeriod", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<InstructionsAvailabilityPeriod> instructionsAvailabilityPeriods = new HashSet<InstructionsAvailabilityPeriod>();
	
	public Instructions() {
		// TODO Auto-generated constructor stub
	}

	public Instructions(long id, String title, Address address, String promotionalDescription,
			String instructorBiography, String picturesPaths, int maxNumberOfPeople, String rulesOfConduct,
			double pricePerHour, double percentageOfEarningsWhenCanceling) {
		super();
		this.id = id;
		this.title = title;
		this.address = address;
		this.promotionalDescription = promotionalDescription;
		this.instructorBiography = instructorBiography;
		this.picturesPaths = picturesPaths;
		this.maxNumberOfPeople = maxNumberOfPeople;
		this.rulesOfConduct = rulesOfConduct;
		this.pricePerHour = pricePerHour;
		this.percentageOfEarningsWhenCanceling = percentageOfEarningsWhenCanceling;
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

	public String getInstructorBiography() {
		return instructorBiography;
	}

	public void setInstructorBiography(String instructorBiography) {
		this.instructorBiography = instructorBiography;
	}

	public String getPicturesPaths() {
		return picturesPaths;
	}

	public void setPicturesPaths(String picturesPaths) {
		this.picturesPaths = picturesPaths;
	}

	public int getMaxNumberOfPeople() {
		return maxNumberOfPeople;
	}

	public void setMaxNumberOfPeople(int maxNumberOfPeople) {
		this.maxNumberOfPeople = maxNumberOfPeople;
	}

	public String getRulesOfConduct() {
		return rulesOfConduct;
	}

	public void setRulesOfConduct(String rulesOfConduct) {
		this.rulesOfConduct = rulesOfConduct;
	}

	public Set<FishingEquipment> getFishingEquipment() {
		return fishingEquipment;
	}

	public void setFishingEquipment(Set<FishingEquipment> fishingEquipment) {
		this.fishingEquipment = fishingEquipment;
	}

	public double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public Set<AdditionalServices> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(Set<AdditionalServices> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public double getPercentageOfEarningsWhenCanceling() {
		return percentageOfEarningsWhenCanceling;
	}

	public void setPercentageOfEarningsWhenCanceling(double percentageOfEarningsWhenCanceling) {
		this.percentageOfEarningsWhenCanceling = percentageOfEarningsWhenCanceling;
	}
	
}
