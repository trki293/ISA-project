package com.isa.booking_entities.services.interfaces;

import com.isa.booking_entities.models.SystemParameters;

public interface ISystemParametersService {
	SystemParameters get();
	SystemParameters save(SystemParameters systemParameters);
}
