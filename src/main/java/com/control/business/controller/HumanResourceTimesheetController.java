package com.control.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.business.model.dto.HumanResourceTimesheetPuchItDTO;
import com.control.business.service.HumanResourceTimesheetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/business/humanresource/timesheet")
public class HumanResourceTimesheetController {

	@Autowired
	private HumanResourceTimesheetService humanResourceTimesheetService;

	@GetMapping
	public ResponseEntity<?> findAll(@PageableDefault(size = 100) final Pageable pageable) {
		return ResponseEntity.ok(humanResourceTimesheetService.findAll(pageable));
	}

	@PostMapping("/puch_it")
	public ResponseEntity<?> save(@Valid @RequestBody HumanResourceTimesheetPuchItDTO humanResourceTimesheetSaveDTO) {
		humanResourceTimesheetService.save(humanResourceTimesheetSaveDTO);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{humanResourceTimesheetId}")
	public ResponseEntity<?> delete(@PathVariable("humanResourceTimesheetId") String humanResourceTimesheetId) {
		humanResourceTimesheetService.delete(humanResourceTimesheetId);
		return ResponseEntity.noContent().build();
	}

}
