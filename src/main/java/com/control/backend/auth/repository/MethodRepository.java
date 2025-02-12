package com.control.backend.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.control.backend.auth.model.Method;

public interface MethodRepository extends JpaRepository<Method, Long> {

}
