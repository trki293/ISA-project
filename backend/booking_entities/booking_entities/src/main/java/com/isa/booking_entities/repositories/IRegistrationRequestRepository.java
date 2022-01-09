package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.requests.RegistrationRequest;

public interface IRegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long>{

}
