package com.isa.booking_entities.services.interfaces;

import com.isa.booking_entities.models.users.CottageOwner;

public interface ICottageOwnerService {
	CottageOwner getById(long id);
	CottageOwner getByEmail(String email);
	CottageOwner save(CottageOwner cottageOwner);
}
