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

import com.control.model.dto.RoleInsertDTO;
import com.control.model.dto.RoleUpdateDTO;
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
	public ResponseEntity<?> findByName(@PathVariable("roleName") String roleName) {
		return ResponseEntity.ok(roleService.findByName(roleName));
	}

	@GetMapping("/findByNameContaining/{roleName}")
	public ResponseEntity<?> findByNameContaining(@PathVariable("roleName") String roleName) {
		return ResponseEntity.ok(roleService.findByNameContaining(roleName));
	}

	@GetMapping("/find/{find}")
	public ResponseEntity<?> find(@PathVariable("find") String find) {
		return ResponseEntity.ok(roleService.findByNameContaining(find));
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid RoleInsertDTO roleInsertDTO) {
		return ResponseEntity.ok(roleService.save(roleInsertDTO));
	}

	@PutMapping
	public ResponseEntity<?> save(@RequestBody @Valid RoleUpdateDTO roleUpdateDTO) {
		roleService.save(roleUpdateDTO);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{roleId}")
	public ResponseEntity<?> delete(@Valid @PathVariable("roleId") String roleId) {
		roleService.delete(roleId);
		return ResponseEntity.noContent().build();
	}

}
