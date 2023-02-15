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

import com.control.model.dto.RoleIdDTO;
import com.control.model.dto.RoleInsertDTO;
import com.control.model.dto.RoleUpdateDTO;
import com.control.service.RoleService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/role")
@Slf4j
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping
	public ResponseEntity<?> findAll(Pageable pageable) {
		log.info("Pagination: {}", pageable);

		final var entity = roleService.findAll(pageable);
		return entity.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(entity);
	}

	@GetMapping("/{roleId}")
	public ResponseEntity<?> findById(@PathVariable("roleId") String roleId) {
		log.info("Parameter: roleId={}", roleId);

		final var entity = roleService.findById(roleId);
		return entity.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(entity);
	}

	@GetMapping("/findByName/{roleName}")
	public ResponseEntity<?> findByName(@PathVariable("role_name") String roleName) {
		log.info("Parameter: roleName={}", roleName);

		final var entity = roleService.findByName(roleName);
		return entity == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(entity);
	}

	@GetMapping("/findByNameContaining/{roleName}")
	public ResponseEntity<?> findByNameContaining(@PathVariable("roleName") String roleName) {
		log.info("Parameter: roleName={}", roleName);

		final var entity = roleService.findByNameContaining(roleName);
		return entity.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(entity);
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody RoleInsertDTO roleInsertDTO) {
		log.info("Parameter: object={}", roleInsertDTO.toString());

		return ResponseEntity.ok(roleService.save(roleInsertDTO));
	}

	@PutMapping
	public ResponseEntity<?> save(@Valid @RequestBody RoleUpdateDTO roleUpdateDTO) {
		log.info("Parameter: object={}", roleUpdateDTO);

		roleService.save(roleUpdateDTO);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping()
	public ResponseEntity<?> delete(@Valid @RequestBody RoleIdDTO roleIdDTO) {
		log.info("Parameter: roleId={}", roleIdDTO.getRoleId());

		roleService.delete(roleIdDTO.getRoleId());

		return ResponseEntity.noContent().build();
	}

}
