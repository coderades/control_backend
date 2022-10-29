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

import com.control.model.dto.RoleInsertDTO;
import com.control.model.dto.RoleUpdateDTO;
import com.control.service.RoleService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/role")
@Slf4j
public class RoleController {

	private final RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@GetMapping
	public ResponseEntity<?> findAll(Pageable pageable) {
		log.info("Pagination: {}", pageable);

		final var role = roleService.findAll(pageable);
		return role.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				: ResponseEntity.status(HttpStatus.FOUND).body(role);
	}

	@GetMapping("/{role_id}")
	public ResponseEntity<?> findById(@PathVariable("role_id") String role_id) {
		log.info("Parameter: roleId={}", role_id);

		final var role = roleService.findById(role_id);
		return role == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				: ResponseEntity.status(HttpStatus.FOUND).body(role);
	}

	@GetMapping("/findByName/{role_name}")
	public ResponseEntity<?> findByName(@PathVariable("role_name") String role_name) {
		log.info("Parameter: roleName={}", role_name);

		final var role = roleService.findByName(role_name);
		return role == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				: ResponseEntity.status(HttpStatus.FOUND).body(role);
	}

	@GetMapping("/findByNameContaining/{role_name}")
	public ResponseEntity<?> findByNameIgnoreCaseContaining(@PathVariable("role_name") String role_name) {
		log.info("Parameter: roleName={}", role_name);

		final var role = roleService.findByNameContaining(role_name);
		return role.size() == 0 ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				: ResponseEntity.status(HttpStatus.FOUND).body(role);
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody RoleInsertDTO roleInsertDTO) {
		log.info("Parameter: object {}", roleInsertDTO.toString());

		return ResponseEntity.status(HttpStatus.CREATED).body(roleService.save(roleInsertDTO));
	}

	@PutMapping
	public ResponseEntity<?> save(@Valid @RequestBody RoleUpdateDTO roleUpdateDTO) {
		log.info("Parameter: object {}", roleUpdateDTO);

		roleService.save(roleUpdateDTO);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/{role_id}")
	public ResponseEntity<?> delete(@PathVariable("role_id") String role_id) {
		log.info("Parameter: roleId={}", role_id);

		roleService.delete(role_id);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
