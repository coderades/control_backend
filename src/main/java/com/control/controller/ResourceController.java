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

import com.control.model.dto.ResourceIdDTO;
import com.control.model.dto.ResourceInsertDTO;
import com.control.model.dto.ResourceUpdadeDTO;
import com.control.service.ResourceService;

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

	@GetMapping("/{resourceId}")
	public ResponseEntity<?> findById(@PathVariable("resourceId") String resourceId) {
		return ResponseEntity.ok(resourceService.findById(resourceId));
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody ResourceInsertDTO resourceInsertDTO) {
		return ResponseEntity.ok(resourceService.save(resourceInsertDTO));
	}

	@PutMapping
	public ResponseEntity<?> save(@Valid @RequestBody ResourceUpdadeDTO resourceUpdateDTO) {
		resourceService.save(resourceUpdateDTO);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping
	public ResponseEntity<?> delete(@Valid @RequestBody ResourceIdDTO resourceDTO) {
		resourceService.delete(resourceDTO);
		return ResponseEntity.noContent().build();
	}

}
