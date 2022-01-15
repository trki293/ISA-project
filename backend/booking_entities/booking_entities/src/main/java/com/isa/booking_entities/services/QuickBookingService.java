package com.isa.booking_entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.models.reservations.QuickBooking;
import com.isa.booking_entities.repositories.IQuickBookingRepository;
import com.isa.booking_entities.services.interfaces.IQuickBookingService;

@Service
public class QuickBookingService implements IQuickBookingService {
	
	private IQuickBookingRepository iQuickBookingRepository;
	
	@Autowired
	public QuickBookingService(IQuickBookingRepository iQuickBookingRepository) {
		this.iQuickBookingRepository = iQuickBookingRepository;
	}

	@Override
	public QuickBooking save(QuickBooking quickBooking) {
		return iQuickBookingRepository.save(quickBooking);
	}

}
