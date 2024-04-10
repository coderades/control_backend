package com.control.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.business.repository.HumanResourceTimesheetRepository;

@RestController
@RequestMapping("/api/business/humanresource/timesheet")
public class HumanResourceTimesheetController {

	@Autowired
	private HumanResourceTimesheetRepository humanResourceTimesheetRepository;

	@GetMapping
	public ResponseEntity<?> findAll(@PageableDefault(size = 100) final Pageable pageable) {
		return ResponseEntity.ok(humanResourceTimesheetRepository.findAll(pageable));
	}

}
