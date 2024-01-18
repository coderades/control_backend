package com.control_backend.controller;

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

import com.control_backend.model.dto.PermissionIdDTO;
import com.control_backend.model.dto.PermissionInsertDTO;
import com.control_backend.model.dto.PermissionUpdateDTO;
import com.control_backend.service.PermissionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;

	@GetMapping
	public ResponseEntity<?> findAll(
			@SortDefault(sort = "permissionName") @PageableDefault(size = 100) final Pageable pageable) {
		return ResponseEntity.ok(permissionService.findAll(pageable));
	}

	@GetMapping("/findById/{permissionId}")
	public ResponseEntity<?> findById(@PathVariable("permissionId") String permissionId) {
		return ResponseEntity.ok(permissionService.findById(permissionId));
	}

	@GetMapping("/findByName/{permissionIsPublic}")
	public ResponseEntity<?> findByIsPublic(@PathVariable("permissionIsPublic") Boolean permissionIsPublic) {
		return ResponseEntity.ok(permissionService.findByIsPublic(permissionIsPublic));
	}

	@GetMapping("/findByMethod/{permissionMethod}")
	public ResponseEntity<?> findByNameContaining(@PathVariable("permissionMethod") String permissionMethod) {
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

	@DeleteMapping
	public ResponseEntity<?> delete(@Valid @RequestBody PermissionIdDTO permissionIdDTO) {
		permissionService.delete(permissionIdDTO);
		return ResponseEntity.noContent().build();
	}

}
