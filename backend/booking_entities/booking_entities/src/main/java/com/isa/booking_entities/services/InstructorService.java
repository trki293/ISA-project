package com.isa.booking_entities.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.models.users.Instructor;
import com.isa.booking_entities.repositories.IInstructorRepository;
import com.isa.booking_entities.services.interfaces.IInstructorService;

@Service
public class InstructorService implements IInstructorService {
	
	private IInstructorRepository iInstructorRepository;
	
	@Autowired
	public InstructorService(IInstructorRepository iInstructorRepository) {
		this.iInstructorRepository = iInstructorRepository;
	}

	@Override
	public Instructor getById(long id) {
		return iInstructorRepository.findById(id).orElse(null);
	}

	@Override
	public Instructor getByEmail(String email) {
		return iInstructorRepository.findAll().stream().filter(instructorIt -> instructorIt.getEmail().equals(email)).findFirst().orElse(null);
	}

	@Override
	public Instructor save(Instructor instructor) {
		return iInstructorRepository.save(instructor);
	}

	@Override
	public List<Instructor> getAllNonDeletedInstructors() {
		return iInstructorRepository.findAll().stream().filter(instructorIt -> !instructorIt.getDeleted()).collect(Collectors.toList());
	}
	
}
