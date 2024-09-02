package com.control.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.backend.model.dto.PermissionInsertDTO;
import com.control.backend.model.dto.PermissionUpdateDTO;
import com.control.backend.service.PermissionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;

	@GetMapping
	public ResponseEntity<?> findAll(@PageableDefault(size = 100) final Pageable pageable) {
		return ResponseEntity.ok(permissionService.findAll(pageable));
	}

	@GetMapping("/findbyid/{permissionId}")
	public ResponseEntity<?> findById(@PathVariable String permissionId) {
		return ResponseEntity.ok(permissionService.findById(permissionId));
	}

	@GetMapping("/findbyispublic/{permissionIsPublic}")
	public ResponseEntity<?> findByIsPublic(@PathVariable Boolean permissionIsPublic) {
		return ResponseEntity.ok(permissionService.findByIsPublic(permissionIsPublic));
	}

	@GetMapping("/findbymethod/{permissionMethod}")
	public ResponseEntity<?> findByNameContaining(@PathVariable String permissionMethod) {
		return ResponseEntity.ok(permissionService.findByMethod(permissionMethod));
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody PermissionInsertDTO permissionInsertDTO) {
		return ResponseEntity.created(null).body(permissionService.save(permissionInsertDTO));
	}

	@PutMapping
	public ResponseEntity<?> save(@Valid @RequestBody PermissionUpdateDTO permissionUpdateDTO) {
		permissionService.save(permissionUpdateDTO);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{permissionId}")
	public ResponseEntity<?> delete(@PathVariable String permissionId) {
		permissionService.delete(permissionId);
		return ResponseEntity.noContent().build();
	}

}
