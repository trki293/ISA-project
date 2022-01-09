package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.users.BoatOwner;

public interface IBoatOwnerRepository extends JpaRepository<BoatOwner, Long>{

}
