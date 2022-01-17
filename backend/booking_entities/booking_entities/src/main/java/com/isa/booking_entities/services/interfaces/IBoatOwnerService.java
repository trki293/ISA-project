package com.isa.booking_entities.services.interfaces;

import com.isa.booking_entities.models.users.BoatOwner;

public interface IBoatOwnerService {
	BoatOwner save(BoatOwner boatOwner);
	BoatOwner getById(long id);
	BoatOwner getByEmail(String email);
}
