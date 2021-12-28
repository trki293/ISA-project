package com.isa.booking_entities.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "fishing_equipments")
public class FishingEquipment {
	@Id
	@SequenceGenerator(name = "mySeqGenFishingEquipment", sequenceName = "mySeqFishingEquipment", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenFishingEquipment")
	private long id;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	public FishingEquipment() {
		// TODO Auto-generated constructor stub
	}

	public FishingEquipment(long id, String name) {
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
