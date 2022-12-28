package com.control.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.model.dto.ApplicationTenantDTO;
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
		return !entity.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body(entity)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping()
	public ResponseEntity<?> delete(@Valid @RequestBody ApplicationTenantDTO  applicationTenantDTO) {
		log.info("Parameter: applicationId={}", applicationTenantDTO.getApplicationTenantId());
		applicationTenantService.delete(applicationTenantDTO);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
