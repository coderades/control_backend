package com.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.control.model.Resource;

public interface ResourceRepository extends JpaRepository<Resource, String> {

}
