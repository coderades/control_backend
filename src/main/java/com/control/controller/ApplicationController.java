package com.control.controller;

import javax.validation.Valid;

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

import com.control.model.dto.ApplicationInsertDTO;
import com.control.model.dto.ApplicationUpdateDTO;
import com.control.service.ApplicationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/application")
@Slf4j
public class ApplicationController {

	private final ApplicationService applicationService;

	public ApplicationController(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	@GetMapping
	public ResponseEntity<?> findAll(Pageable pageable) {
		log.info("Pagination: {}", pageable);

		final var application = applicationService.findAll(pageable);
		return application.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				: ResponseEntity.status(HttpStatus.FOUND).body(application);
	}

	@GetMapping("/{application_id}")
	public ResponseEntity<?> findById(@PathVariable("application_id") String application_id) {
		log.info("Parameter: applicationId={}", application_id);

		final var application = applicationService.findById(application_id);
		return application == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				: ResponseEntity.status(HttpStatus.FOUND).body(application);
	}

	@GetMapping("/findByName/{application_name}")
	public ResponseEntity<?> findByName(@PathVariable("application_name") String application_name) {
		log.info("Parameter: applicationName={}", application_name);

		final var application = applicationService.findByName(application_name);
		return application == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				: ResponseEntity.status(HttpStatus.FOUND).body(application);
	}

	@GetMapping("/findByNameContaining/{application_name}")
	public ResponseEntity<?> findByNameIgnoreCaseContaining(@PathVariable("application_name") String application_name) {
		log.info("Parameter: applicationName={}", application_name);

		final var application = applicationService.findByNameContaining(application_name);
		return application.size() == 0 ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				: ResponseEntity.status(HttpStatus.FOUND).body(application);
	}

	@GetMapping("/findByEmail/{application_email}")
	public ResponseEntity<?> findByEmail(@PathVariable("application_email") String application_email) {
		log.info("Parameter: applicationEmail={}", application_email);

		final var application = applicationService.findByEmail(application_email);
		return application == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				: ResponseEntity.status(HttpStatus.FOUND).body(application);
	}

	@GetMapping("/find/{find}")
	public ResponseEntity<?> findByApplicationNameIgnoreCaseContainingOrApplicationEmailIgnoreCaseContaining(
			@PathVariable("find") String find) {
		log.info("Parameter: find={}", find);

		final var application = applicationService.find(find);
		return application.size() == 0 ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				: ResponseEntity.status(HttpStatus.FOUND).body(application);
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody ApplicationInsertDTO applicationInsertDTO) {
		log.info("Parameter: object {}", applicationInsertDTO.toString());

		return ResponseEntity.status(HttpStatus.CREATED).body(applicationService.save(applicationInsertDTO));
	}

	@PutMapping
	public ResponseEntity<?> save(@Valid @RequestBody ApplicationUpdateDTO applicationUpdateDTO) {
		log.info("Parameter: object {}", applicationUpdateDTO);
		applicationService.save(applicationUpdateDTO);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/{application_id}")
	public ResponseEntity<?> delete(@PathVariable("application_id") String application_id) {
		log.info("Parameter: applicationId={}", application_id);
		applicationService.delete(application_id);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
