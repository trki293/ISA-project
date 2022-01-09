package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.requests.DeleteAccountRequest;

public interface IDeleteAccountRequestRepository extends JpaRepository<DeleteAccountRequest, Long> {

}
