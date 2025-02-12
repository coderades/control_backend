package com.control.backend.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.control.backend.auth.model.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

	Resource findByResourceName(String resourceName);

}
