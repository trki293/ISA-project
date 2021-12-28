package com.isa.booking_entities.models.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="clients")
public class Client extends Users {
	
	@Column(name = "penaltyPoints", unique = false, nullable = false)
	private long penaltyPoints;
	
	@Column(name = "loyaltyPoints", unique = false, nullable = false)
	private long loyaltyPoints;
	
	//It is necessary to add an enum for the client's rank
}
