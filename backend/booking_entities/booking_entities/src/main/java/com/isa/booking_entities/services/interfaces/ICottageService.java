package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.dtos.CottageDisplayDTO;
import com.isa.booking_entities.dtos.CottagePreviewDTO;
import com.isa.booking_entities.dtos.CottagePreviewDTO;
import com.isa.booking_entities.dtos.CottageSearchDTO;
import com.isa.booking_entities.dtos.EntitySearchReservationDTO;
import com.isa.booking_entities.models.entites.Cottage;
import com.isa.booking_entities.models.users.Client;

public interface ICottageService {
	Cottage getById(long id);
	Cottage save(Cottage cottage);
	List<CottagePreviewDTO> getAllCottages(CottageSearchDTO cottageSearchDTO);
	List<CottageDisplayDTO> getAllCottagesForClient(EntitySearchReservationDTO cottageSearchReservationDTO, Client client);
	List<String> getAdditionalServicesForCottage(long id) throws Exception;
	List<CottagePreviewDTO> getAllNonDeletedCottages();
}
