package com.isa.booking_entities.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "navigation_equipments")
public class NavigationEquipment {
	@Id
	@SequenceGenerator(name = "mySeqGenNavigationEquipment", sequenceName = "mySeqNavigationEquipment", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenNavigationEquipment")
	private long id;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	public NavigationEquipment() {
		// TODO Auto-generated constructor stub
	}

	public NavigationEquipment(long id, String name) {
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
