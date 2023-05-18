package com.control.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.service.UserRolesService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/userRoles")
@Slf4j
public class UserRolesController {

	@Autowired
	private UserRolesService userRolesService;

	@GetMapping
	public ResponseEntity<?> findAll(Pageable pageable) {
		log.info("Pagination {}", pageable);

		final var entity = userRolesService.findAll(pageable);
		return entity.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(entity);
	}

	@GetMapping("/{userRolesId}")
	public ResponseEntity<?> findById(@PathVariable("userRolesId") String userRolesId) {
		log.info("userRolesId {}", userRolesId);

		final var entity = userRolesService.findById(userRolesId);
		return entity.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(entity);
	}

}
