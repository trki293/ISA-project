package com.isa.booking_entities.services.interfaces;

import com.isa.booking_entities.models.users.SystemAdmin;

public interface ISystemAdminService {
	SystemAdmin getById(long id);
	SystemAdmin save(SystemAdmin systemAdmin);
	SystemAdmin getByEmail(String email);
}
