package com.isa.booking_entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.booking_entities.models.users.SystemAdmin;

public interface ISystemAdminRepository extends JpaRepository<SystemAdmin, Long> {

}
