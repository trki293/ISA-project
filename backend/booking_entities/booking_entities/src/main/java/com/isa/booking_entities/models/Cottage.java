package com.isa.booking_entities.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	private String picturesPaths; //trenutno zamisljeno kao string sa pathovima razdvojenim nekim karakterom, posle mozda bude promjenjeno kao i sa Address
	
	@Column(name = "numberOfRooms", unique = false, nullable = false)
	private int numberOfRooms;
	
	@Column(name = "numberOfBeds", unique = false, nullable = false)
	private int numberOfBeds;
	
	@Column(name = "rulesOfConduct", unique = false, nullable = true)
	private String rulesOfConduct;
	
	@Column(name = "pricePerNight", unique = false, nullable = false)
	private double pricePerNight;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<AdditionalServices> additionalServices = new HashSet<AdditionalServices>();
	
	//termini slobodni
	//akcije
	
	public Cottage() {
	}

	public Cottage(long id, String title, Address address, String promotionalDescription, String picturesPaths,
			int numberOfRooms, int numberOfBeds, String rulesOfConduct, double pricePerNight) {
		super();
		this.id = id;
		this.title = title;
		this.address = address;
		this.promotionalDescription = promotionalDescription;
		this.picturesPaths = picturesPaths;
		this.numberOfRooms = numberOfRooms;
		this.numberOfBeds = numberOfBeds;
		this.rulesOfConduct = rulesOfConduct;
		this.pricePerNight = pricePerNight;
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
	
}
