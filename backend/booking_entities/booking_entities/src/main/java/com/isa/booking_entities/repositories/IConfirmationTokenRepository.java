package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.users.ConfirmationToken;

public interface IConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long>{

}
