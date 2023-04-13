package com.control.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.model.dto.ApplicationIdDTO;
import com.control.model.dto.ApplicationInsertDTO;
import com.control.model.dto.ApplicationUpdateDTO;
import com.control.service.ApplicationService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/application")
@Slf4j
public class ApplicationController {

	@Autowired
	private ApplicationService applicationService;

	@GetMapping
	public ResponseEntity<?> findAll(Pageable pageable) {
		log.info("Pagination: {}", pageable);
		
		final var entity = applicationService.findAll(pageable);
		return entity.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(entity);
	}

	@GetMapping("/{applicationId}")
	public ResponseEntity<?> findById(@PathVariable("applicationId") String applicationId) {
		log.info("Parameter: applicationId={}", applicationId);

		final var entity = applicationService.findById(applicationId);
		return entity.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(entity);
	}

	@GetMapping("/findByNameContaining/{applicationName}")
	public ResponseEntity<?> findByNameContaining(@PathVariable("applicationName") String applicationName) {
		log.info("Parameter: applicationName={}", applicationName);

		final var entity = applicationService.findByNameContaining(applicationName);
		return entity.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(entity);
	}

	@GetMapping("/findByEmailContaining/{applicationEmail}")
	public ResponseEntity<?> findByEmailContaining(@PathVariable("applicationEmail") String applicationEmail) {
		log.info("Parameter: applicationEmail={}", applicationEmail);

		final var entity = applicationService.findByEmailContaining(applicationEmail);
		return entity.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(entity);
	}

	@GetMapping("/find/{find}")
	public ResponseEntity<?> find(@PathVariable("find") String find) {
		log.info("Parameter: find={}", find);

		final var entity = applicationService.find(find);
		return entity.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(entity);
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody ApplicationInsertDTO applicationInsertDTO) {
		log.info("Parameter: object={}", applicationInsertDTO.toString());

		return ResponseEntity.ok(applicationService.save(applicationInsertDTO));
	}

	@PutMapping
	public ResponseEntity<?> save(@Valid @RequestBody ApplicationUpdateDTO applicationUpdateDTO) {
		log.info("Parameter: object={}", applicationUpdateDTO);

		applicationService.save(applicationUpdateDTO);
		
		return ResponseEntity.ok().build();
	}

	@DeleteMapping()
	public ResponseEntity<?> delete(@Valid @RequestBody ApplicationIdDTO applicationIdDTO) {
		log.info("Parameter: object={}", applicationIdDTO);

		applicationService.delete(applicationIdDTO);
		
		return ResponseEntity.noContent().build();
	}

}
