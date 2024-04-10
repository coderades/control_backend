package com.control.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.backend.model.dto.RolePermissionsInsertDTO;
import com.control.backend.service.RolePermissionsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rolePermissions")
public class RolePermissionsController {

	@Autowired
	private RolePermissionsService rolePermissionsService;

	@GetMapping
	public ResponseEntity<?> findAll(@PageableDefault(size = 100) final Pageable pageable) {
		return ResponseEntity.ok(rolePermissionsService.findAll(pageable));
	}

	@GetMapping("/findById/{rolePermissionsServiceId}")
	public ResponseEntity<?> findById(@PathVariable("rolePermissionsServiceId") String rolePermissionsServiceId) {
		return ResponseEntity.ok(rolePermissionsService.findById(rolePermissionsServiceId));
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody RolePermissionsInsertDTO rolePermissionsInsertDTO) {
		return ResponseEntity.created(null).body(rolePermissionsService.save(rolePermissionsInsertDTO));
	}

	@DeleteMapping("/{rolePermissionsServiceId}")
	public ResponseEntity<?> delete(@PathVariable("rolePermissionsServiceId") String rolePermissionsServiceId) {
		rolePermissionsService.delete(rolePermissionsServiceId);
		return ResponseEntity.noContent().build();
	}

}