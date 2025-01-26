package com.control.backend.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.control.backend.auth.model.RoleResources;

public interface RoleResourcesRepository extends JpaRepository<RoleResources, Long> {

}
