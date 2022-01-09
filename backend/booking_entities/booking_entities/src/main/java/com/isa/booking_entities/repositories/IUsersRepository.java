package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.users.Users;

public interface IUsersRepository extends JpaRepository<Users, Long> {

}
