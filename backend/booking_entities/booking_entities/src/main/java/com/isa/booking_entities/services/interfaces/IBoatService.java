package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.dtos.BoatPreviewDTO;
import com.isa.booking_entities.dtos.BoatSearchDTO;
import com.isa.booking_entities.models.entites.Boat;

public interface IBoatService {
	Boat getById(long id);
	Boat save(Boat boat);
	List<BoatPreviewDTO> getAllBoats(BoatSearchDTO boatSearchDTO);
}