package com.control.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
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
import com.control.model.dto.ApplicationPostDTO;
import com.control.model.dto.ApplicationPutDTO;
import com.control.service.ApplicationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/application")
public class ApplicationController {

	@Autowired
	private ApplicationService applicationService;

	@GetMapping
	public ResponseEntity<?> findAll(
			@SortDefault(sort = "applicationName") @PageableDefault(size = 100) final Pageable pageable) {
		return ResponseEntity.ok(applicationService.findAll(pageable));
	}

	@GetMapping("/{applicationId}")
	public ResponseEntity<?> findById(@PathVariable("applicationId") String applicationId) {
		return ResponseEntity.ok(applicationService.findById(applicationId));
	}

	@GetMapping("/findByNameContaining/{applicationName}")
	public ResponseEntity<?> findByNameContaining(@PathVariable("applicationName") String applicationName) {
		return ResponseEntity.ok(applicationService.findByNameContaining(applicationName));
	}

	@GetMapping("/findByEmailContaining/{applicationEmail}")
	public ResponseEntity<?> findByEmailContaining(@PathVariable("applicationEmail") String applicationEmail) {
		return ResponseEntity.ok(applicationEmail);
	}

	@GetMapping("/find/{find}")
	public ResponseEntity<?> find(@PathVariable("find") String find) {
		return ResponseEntity.ok(applicationService.find(find));
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody ApplicationPostDTO applicationInsertDTO) {
		return ResponseEntity.ok(applicationService.save(applicationInsertDTO));
	}

	@PutMapping
	public ResponseEntity<?> save(@Valid @RequestBody ApplicationPutDTO applicationUpdateDTO) {
		applicationService.save(applicationUpdateDTO);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping
	public ResponseEntity<?> delete(@Valid @RequestBody ApplicationIdDTO applicationIdDTO) {
		applicationService.delete(applicationIdDTO);
		return ResponseEntity.noContent().build();
	}

}
