package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.entites.Cottage;

public interface ICottageRepository extends JpaRepository<Cottage, Long> {

}
