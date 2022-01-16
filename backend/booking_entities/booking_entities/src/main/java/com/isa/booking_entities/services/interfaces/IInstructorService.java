package com.isa.booking_entities.services.interfaces;

import com.isa.booking_entities.models.users.Instructor;

public interface IInstructorService {
	Instructor getById(long id);
	Instructor getByEmail(String email);
	Instructor save(Instructor instructor);
}
