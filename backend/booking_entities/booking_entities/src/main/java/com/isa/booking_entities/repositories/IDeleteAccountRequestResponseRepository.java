package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.requests.DeleteAccountRequestResponse;

public interface IDeleteAccountRequestResponseRepository extends JpaRepository<DeleteAccountRequestResponse, Long> {

}
