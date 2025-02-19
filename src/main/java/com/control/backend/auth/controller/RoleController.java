package com.control.backend.auth.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.backend.auth.model.dto.RoleDTO;
import com.control.backend.auth.service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/role", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping
	public ResponseEntity<?> findAll(
			@SortDefault(sort = "roleId") @PageableDefault(size = 20) final Pageable pageable) {
		return ResponseEntity.ok(roleService.findAll(pageable));
	}

	@GetMapping("/findbyid/{roleId}")
	public ResponseEntity<?> findById(@PathVariable Long roleId) {
		return ResponseEntity.ok(roleService.findById(roleId));
	}

	@GetMapping("/findbyname/{roleName}")
	public ResponseEntity<?> findByName(@PathVariable String roleName) {
		return ResponseEntity.ok(roleService.findByName(roleName));
	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody RoleDTO roleDTO) {
		final var hashMap = new HashMap<String, Long>();
		hashMap.put("roleId", roleService.save(roleDTO));
		return ResponseEntity.created(null).body(hashMap);
	}

	@PutMapping("/{roleId}")
	public ResponseEntity<?> save(@PathVariable Long roleId, @Valid @RequestBody RoleDTO roleDTO) {
		roleService.save(roleId, roleDTO);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{roleId}")
	public ResponseEntity<?> delete(@PathVariable Long roleId) {
		roleService.delete(roleId);
		return ResponseEntity.noContent().build();
	}

}
