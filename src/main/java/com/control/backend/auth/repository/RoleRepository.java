package com.control.backend.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.control.backend.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}