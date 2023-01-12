package com.control.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.model.dto.ApplicationTenantDTO;
import com.control.model.dto.ApplicationTenantInsertDTO;
import com.control.model.dto.ApplicationTenantUpdateDTO;
import com.control.service.ApplicationTenantService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/applicationTenant")
@Slf4j
public class ApplicationTenantController {

	private final ApplicationTenantService applicationTenantService;

	public ApplicationTenantController(ApplicationTenantService applicationTenantService) {
		this.applicationTenantService = applicationTenantService;
	}

	@GetMapping
	public ResponseEntity<?> findAll(Pageable pageable) {
		log.info("Pagination: {}", pageable);

		final var entity = applicationTenantService.findAll(pageable);
		return !entity.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body(entity)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@GetMapping("/{applicationTenantId}")
	public ResponseEntity<?> findById(@PathVariable("applicationTenantId") String applicationTenantId) {
		log.info("Parameter: applicationTenantId={}", applicationTenantId);

		final var entity = applicationTenantService.findById(applicationTenantId);
		return entity.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(entity)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@GetMapping("/findDataSource")
	public ResponseEntity<?> findDataSource(Pageable pageable) {
		log.info("Pagination: {}", pageable);

		final var entity = applicationTenantService.findByDataSource();
		return !entity.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body(entity)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody ApplicationTenantInsertDTO applicationTenantInsertDTO) {
		log.info("Parameter: object={}", applicationTenantInsertDTO.toString());

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(applicationTenantService.save(applicationTenantInsertDTO));
	}

	@PutMapping
	public ResponseEntity<?> save(@Valid @RequestBody ApplicationTenantUpdateDTO applicationTenantUpdateDTO) {
		log.info("Parameter: object={}", applicationTenantUpdateDTO);
		applicationTenantService.save(applicationTenantUpdateDTO);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping()
	public ResponseEntity<?> delete(@Valid @RequestBody ApplicationTenantDTO applicationTenantDTO) {
		log.info("Parameter: applicationId={}", applicationTenantDTO.getApplicationTenantId());
		applicationTenantService.delete(applicationTenantDTO);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
