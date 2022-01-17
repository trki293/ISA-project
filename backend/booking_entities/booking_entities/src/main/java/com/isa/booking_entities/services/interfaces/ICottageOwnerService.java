package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.models.users.CottageOwner;

public interface ICottageOwnerService {
	CottageOwner getById(long id);
	CottageOwner getByEmail(String email);
	CottageOwner save(CottageOwner cottageOwner);
	List<CottageOwner> getAllNonDeletedCottageOwners();
}
