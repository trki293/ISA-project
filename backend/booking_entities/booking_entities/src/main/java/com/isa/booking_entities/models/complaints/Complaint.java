package com.isa.booking_entities.models.complaints;

import static javax.persistence.InheritanceType.JOINED;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isa.booking_entities.models.users.Client;

@Entity
@Table(name = "complaints")
@Inheritance(strategy = JOINED)
public class Complaint {
	
	@Id
	@SequenceGenerator(name = "mySeqGenComplaint", sequenceName = "mySeqComplaint", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenComplaint")
	private long id;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Client clientWhoCreateComplaint;
	
	@Column(name = "text", unique = false, nullable = false)
	private String text;
	
	@Enumerated(EnumType.ORDINAL)
	private TypeOfComplaint typeOfComplaint;
	
	@Enumerated(EnumType.ORDINAL)
	private StatusOfComplaint statusOfComplaint;
	
	public Complaint() {
		// TODO Auto-generated constructor stub
	}

	public Complaint(long id, Client clientWhoCreateComplaint, String text, TypeOfComplaint typeOfComplaint,
			StatusOfComplaint statusOfComplaint) {
		this.id = id;
		this.clientWhoCreateComplaint = clientWhoCreateComplaint;
		this.text = text;
		this.typeOfComplaint = typeOfComplaint;
		this.statusOfComplaint = statusOfComplaint;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Client getClientWhoCreateComplaint() {
		return clientWhoCreateComplaint;
	}

	public void setClientWhoCreateComplaint(Client clientWhoCreateComplaint) {
		this.clientWhoCreateComplaint = clientWhoCreateComplaint;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public TypeOfComplaint getTypeOfComplaint() {
		return typeOfComplaint;
	}

	public void setTypeOfComplaint(TypeOfComplaint typeOfComplaint) {
		this.typeOfComplaint = typeOfComplaint;
	}

	public StatusOfComplaint getStatusOfComplaint() {
		return statusOfComplaint;
	}

	public void setStatusOfComplaint(StatusOfComplaint statusOfComplaint) {
		this.statusOfComplaint = statusOfComplaint;
	}
	
}
