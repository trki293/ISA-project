package com.isa.booking_entities.models.complaints;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.entites.Cottage;
import com.isa.booking_entities.models.users.CottageOwner;

@Entity
@Table(name = "cottage_complaints")
public class CottageComplaint extends Complaint {
	@JsonBackReference(value="cottage-complaint")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Cottage cottageForComplaint;

	public CottageComplaint() {
		// TODO Auto-generated constructor stub
	}

	public Cottage getCottageForComplaint() {
		return cottageForComplaint;
	}

	public void setCottageForComplaint(Cottage cottageForComplaint) {
		this.cottageForComplaint = cottageForComplaint;
	}

}
