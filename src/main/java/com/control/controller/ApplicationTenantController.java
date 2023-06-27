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

import com.control.model.dto.ApplicationTenantDTO;
import com.control.model.dto.ApplicationTenantInsertDTO;
import com.control.model.dto.ApplicationTenantUpdateDTO;
import com.control.service.ApplicationTenantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/applicationTenant")
public class ApplicationTenantController {

	@Autowired
	private ApplicationTenantService applicationTenantService;

	@GetMapping
	public ResponseEntity<?> findAll(
			@SortDefault(sort = "applicationTenantName") @PageableDefault(size = 100) final Pageable pageable) {
		return ResponseEntity.ok(applicationTenantService.findAll(pageable));
	}

	@GetMapping("/{applicationTenantId}")
	public ResponseEntity<?> findById(@PathVariable("applicationTenantId") String applicationTenantId) {
		return ResponseEntity.ok(applicationTenantId);
	}

	@GetMapping("/findDataSource")
	public ResponseEntity<?> findDataSource(Pageable pageable) {
		return ResponseEntity.ok(applicationTenantService.findByDataSource());
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody ApplicationTenantInsertDTO applicationTenantInsertDTO) {
		return ResponseEntity.ok(applicationTenantService.save(applicationTenantInsertDTO));
	}

	@PutMapping
	public ResponseEntity<?> save(@Valid @RequestBody ApplicationTenantUpdateDTO applicationTenantUpdateDTO) {
		applicationTenantService.save(applicationTenantUpdateDTO);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping
	public ResponseEntity<?> delete(@Valid @RequestBody ApplicationTenantDTO applicationTenantDTO) {
		applicationTenantService.delete(applicationTenantDTO);
		return ResponseEntity.noContent().build();
	}

}
