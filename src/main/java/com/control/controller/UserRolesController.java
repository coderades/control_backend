package com.control.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

	private final UserRolesService userRolesService;

	public UserRolesController(UserRolesService userRolesService) {
		this.userRolesService = userRolesService;
	}

	@GetMapping
	public ResponseEntity<?> findAll(Pageable pageable) {
		log.info("Pagination: {}", pageable);

		return ResponseEntity.status(HttpStatus.OK).body(userRolesService.findAll(pageable));
	}

	@GetMapping("/{userRolesId}")
	public ResponseEntity<?> findById(@PathVariable("userRolesId") String userRolesId) {
		log.info("Parameter: userRolesId={}", userRolesId);

		final var entity = userRolesService.findById(userRolesId);
		return ResponseEntity.status(HttpStatus.FOUND).body(entity);
	}

}
