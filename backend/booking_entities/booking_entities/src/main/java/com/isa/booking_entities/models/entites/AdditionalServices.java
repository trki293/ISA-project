package com.isa.booking_entities.models.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "additional_services")
public class AdditionalServices {
	@Id
	@SequenceGenerator(name = "mySeqGenAdditionalServices", sequenceName = "mySeqAdditionalServices", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenAdditionalServices")
	private long id;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	public AdditionalServices() {
		// TODO Auto-generated constructor stub
	}

	public AdditionalServices(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
