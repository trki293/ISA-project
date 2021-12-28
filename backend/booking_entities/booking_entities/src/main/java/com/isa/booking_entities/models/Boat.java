package com.isa.booking_entities.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	private String picturesPaths; //trenutno zamisljeno kao string sa pathovima razdvojenim nekim karakterom, posle mozda bude promjenjeno kao i sa Address
	
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
	
	//termini slobodni
	//akcije
	
	public Boat() {
		// TODO Auto-generated constructor stub
	}
	
	public Boat(long id, String title, TypeOfBoat typeOfBoat, double length, int engineNumber, double enginePower,
			double maxSpeed, Address address, String promotionalDescription, String picturesPaths, int capacity,
			String rulesOfConduct, double pricePerHour, double percentageOfEarningsWhenCanceling) {
		super();
		this.id = id;
		this.title = title;
		this.typeOfBoat = typeOfBoat;
		this.length = length;
		this.engineNumber = engineNumber;
		this.enginePower = enginePower;
		this.maxSpeed = maxSpeed;
		this.address = address;
		this.promotionalDescription = promotionalDescription;
		this.picturesPaths = picturesPaths;
		this.capacity = capacity;
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
	
}
