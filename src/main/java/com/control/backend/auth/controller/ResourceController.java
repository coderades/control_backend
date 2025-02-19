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

import com.control.backend.auth.model.dto.ResourceDTO;
import com.control.backend.auth.service.ResourceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/resource", produces = MediaType.APPLICATION_JSON_VALUE)
public class ResourceController {

	@Autowired
	private ResourceService resourceService;

	@GetMapping
	public ResponseEntity<?> findAll(
			@SortDefault(sort = "resourceId") @PageableDefault(size = 20) final Pageable pageable) {
		return ResponseEntity.ok(resourceService.findAll(pageable));
	}

	@GetMapping("/findbyid/{resourceId}")
	public ResponseEntity<?> findById(@PathVariable Long resourceId) {
		return ResponseEntity.ok(resourceService.findById(resourceId));
	}

	@GetMapping("/findbyname/{resourceName}")
	public ResponseEntity<?> findByName(@PathVariable String resourceName) {
		return ResponseEntity.ok(resourceService.findByName(resourceName));
	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody ResourceDTO resourceDTO) {
		final var hashMap = new HashMap<String, Long>();
		hashMap.put("resourceId", resourceService.save(resourceDTO));
		return ResponseEntity.created(null).body(hashMap);
	}

	@PutMapping("/{roleId}")
	public ResponseEntity<?> save(@PathVariable Long resourceId, @Valid @RequestBody ResourceDTO resourceDTO) {
		resourceService.save(resourceId, resourceDTO);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{resourceId}")
	public ResponseEntity<?> delete(@PathVariable Long resourceId) {
		resourceService.delete(resourceId);
		return ResponseEntity.noContent().build();
	}

}
