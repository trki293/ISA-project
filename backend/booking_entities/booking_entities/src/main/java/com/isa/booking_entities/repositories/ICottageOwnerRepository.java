package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.users.CottageOwner;

public interface ICottageOwnerRepository extends JpaRepository<CottageOwner, Long> {

}
