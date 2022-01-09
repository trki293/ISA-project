package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.SystemParameters;

public interface ISystemParametersRepository extends JpaRepository<SystemParameters, Long> {

}
