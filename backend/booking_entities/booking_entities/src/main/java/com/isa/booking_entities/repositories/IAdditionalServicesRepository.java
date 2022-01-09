package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.entites.AdditionalServices;

public interface IAdditionalServicesRepository extends JpaRepository<AdditionalServices, Long> {

}
