package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.dtos.BoatDisplayDTO;
import com.isa.booking_entities.dtos.BoatPreviewDTO;
import com.isa.booking_entities.dtos.BoatSearchDTO;
import com.isa.booking_entities.dtos.EntitySearchReservationDTO;
import com.isa.booking_entities.models.entites.Boat;
import com.isa.booking_entities.models.users.Client;

public interface IBoatService {
	Boat getById(long id);
	Boat save(Boat boat);
	List<BoatPreviewDTO> getAllBoats(BoatSearchDTO boatSearchDTO);
	List<BoatDisplayDTO> getAllBoatsForClient(EntitySearchReservationDTO boatSearchReservationDTO, Client client);
	List<String> getAdditionalServicesForBoat(long id) throws Exception;
	List<BoatPreviewDTO> getAllNonDeletedBoats();
}
