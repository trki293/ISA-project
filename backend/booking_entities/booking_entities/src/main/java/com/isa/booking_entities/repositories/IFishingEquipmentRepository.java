package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.entites.FishingEquipment;

public interface IFishingEquipmentRepository extends JpaRepository<FishingEquipment, Long> {

}
