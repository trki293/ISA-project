package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.users.Instructor;

public interface IInstructorRepository extends JpaRepository<Instructor, Long> {

}
