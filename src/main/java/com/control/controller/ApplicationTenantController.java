package com.control.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.service.ApplicationTenantService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/applicationTenant")
@Slf4j
public class ApplicationTenantController {

	private final ApplicationTenantService applicationTenantService;

	public ApplicationTenantController(ApplicationTenantService applicationTenantService) {
		this.applicationTenantService = applicationTenantService;
	}

	@GetMapping("/findDataSource")
	public ResponseEntity<?> findDataSource(Pageable pageable) {
		log.info("Pagination: {}", pageable);

		final var entity = applicationTenantService.findByDataSource();
		return entity.size() == 0 ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				: ResponseEntity.status(HttpStatus.FOUND).body(entity);
	}

}
