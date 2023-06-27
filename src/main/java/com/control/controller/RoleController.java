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

import com.control.model.dto.RoleIdDTO;
import com.control.model.dto.RolePostDTO;
import com.control.model.dto.RolePutDTO;
import com.control.service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping
	public ResponseEntity<?> findAll(
			@SortDefault(sort = "roleName") @PageableDefault(size = 100) final Pageable pageable) {
		return ResponseEntity.ok(roleService.findAll(pageable));
	}

	@GetMapping("/{roleId}")
	public ResponseEntity<?> findById(@PathVariable("roleId") String roleId) {
		return ResponseEntity.ok(roleService.findById(roleId));
	}

	@GetMapping("/findByName/{roleName}")
	public ResponseEntity<?> findByName(@PathVariable("role_name") String roleName) {
		return ResponseEntity.ok(roleService.findByName(roleName));
	}

	@GetMapping("/findByNameContaining/{roleName}")
	public ResponseEntity<?> findByNameContaining(@PathVariable("roleName") String roleName) {
		return ResponseEntity.ok(roleService.findByNameContaining(roleName));
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody RolePostDTO roleInsertDTO) {
		return ResponseEntity.ok(roleService.save(roleInsertDTO));
	}

	@PutMapping
	public ResponseEntity<?> save(@Valid @RequestBody RolePutDTO roleUpdateDTO) {
		roleService.save(roleUpdateDTO);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping
	public ResponseEntity<?> delete(@Valid @RequestBody RoleIdDTO roleIdDTO) {
		roleService.delete(roleIdDTO);
		return ResponseEntity.noContent().build();
	}

}
