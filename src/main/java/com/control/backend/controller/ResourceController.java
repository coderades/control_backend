package com.control.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.backend.model.dto.ResourceInsertDTO;
import com.control.backend.model.dto.ResourceUpdateDTO;
import com.control.backend.service.ResourceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {

	@Autowired
	private ResourceService resourceService;

	@GetMapping
	public ResponseEntity<?> findAll(
			@SortDefault(sort = "resourceName") @PageableDefault(size = 100) final Pageable pageable) {
		return ResponseEntity.ok(resourceService.findAll(pageable));
	}

	@GetMapping("/find/{find}")
	public ResponseEntity<?> find(@PathVariable String find) {
		return ResponseEntity.ok(resourceService.find(find));
	}

	@GetMapping("/findbyid/{resourceId}")
	public ResponseEntity<?> findById(@PathVariable String resourceId) {
		return ResponseEntity.ok(resourceService.findById(resourceId));
	}

	@GetMapping("/findbyname/{resourceName}")
	public ResponseEntity<?> findByName(@PathVariable String resourceName) {
		return ResponseEntity.ok(resourceService.findByName(resourceName));
	}

	@GetMapping("/findbyresourcepath/{applicationKeyAccess}/{httpMethod}/{permissionIsPublic}")
	public ResponseEntity<?> findByResourcePath(@PathVariable String applicationKeyAccess,
			@PathVariable HttpMethod httpMethod, @PathVariable Boolean permissionIsPublic) {
		return ResponseEntity
				.ok(resourceService.findByResourcePath(applicationKeyAccess, httpMethod, permissionIsPublic));
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody ResourceInsertDTO resourceInsertDTO) {
		return ResponseEntity.created(null).body(resourceService.save(resourceInsertDTO));
	}

	@PutMapping
	public ResponseEntity<?> save(@Valid @RequestBody ResourceUpdateDTO resourceUpdateDTO) {
		resourceService.save(resourceUpdateDTO);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{resourceId}")
	public ResponseEntity<?> delete(@PathVariable String resourceId) {
		resourceService.delete(resourceId);
		return ResponseEntity.noContent().build();
	}

}
