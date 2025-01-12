package com.control.backend.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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

import com.control.backend.auth.model.dto.UserRolesDTO;
import com.control.backend.auth.service.UserRolesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/userroles", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRolesController {

	@Autowired
	private UserRolesService userRolesService;

	@GetMapping("/findbyid/{userRolesId}")
	public ResponseEntity<?> findById(@PathVariable Long userRolesId) {
		return ResponseEntity.ok(userRolesService.findById(userRolesId));
	}

	@GetMapping("/findbyUserid/{userId}")
	public ResponseEntity<?> findByUserId(@PathVariable Long userId) {
		return ResponseEntity.ok(userRolesService.findByUserId(userId));
	}

	@GetMapping("/findbyRoleid/{roleId}")
	public ResponseEntity<?> findByRoleId(@PathVariable Long roleId) {
		return ResponseEntity.ok(userRolesService.findByRoleId(roleId));
	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody UserRolesDTO userRolesDTO) {
		final var jsonObject = new JSONObject();
		try {
			jsonObject.put("roleId", userRolesService.save(userRolesDTO));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ResponseEntity.created(null).body(jsonObject.toString());
	}

	@PutMapping("/{userRolesId}")
	public ResponseEntity<?> save(@PathVariable Long userRolesId, @Valid @RequestBody UserRolesDTO userRolesDTO) {
		userRolesService.save(userRolesId, userRolesDTO);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{userRolesId}")
	public ResponseEntity<?> delete(@PathVariable Long userRolesId) {
		userRolesService.delete(userRolesId);
		return ResponseEntity.noContent().build();
	}

}
