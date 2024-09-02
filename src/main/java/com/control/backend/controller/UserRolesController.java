package com.control.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.backend.service.UserRolesService;

@RestController
@RequestMapping("/api/userroles")
public class UserRolesController {

	@Autowired
	private UserRolesService userRolesService;

	@GetMapping
	public ResponseEntity<?> findAll(
			@SortDefault(sort = "userRolesId") @PageableDefault(size = 100) final Pageable pageable) {
		return ResponseEntity.ok(userRolesService.findAll(pageable));
	}

	@GetMapping("/findbyIid/{userRolesId}")
	public ResponseEntity<?> findById(@PathVariable String userId) {
		return ResponseEntity.ok(userRolesService.findById(userId));
	}
	
	@GetMapping("/findbyroleid/{roleId}")
	public ResponseEntity<?> findByRoleId(@PathVariable String roleId) {
		return ResponseEntity.ok(userRolesService.findByRoleId(roleId));
	}
	
	@GetMapping("/findbyuserid/{userId}")
	public ResponseEntity<?> findByUserId(@PathVariable String userId) {
		return ResponseEntity.ok(userRolesService.findByUserId(userId));
	}

}
