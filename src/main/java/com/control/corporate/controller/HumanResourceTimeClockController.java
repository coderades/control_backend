package com.control.corporate.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.control.corporate.model.dto.HumanResourceTimeClockSaveDTO;
import com.control.corporate.service.HumanResourceTimeClockService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/corporate/humanresource/timeclock")
public class HumanResourceTimeClockController {

	@Autowired
	private HumanResourceTimeClockService humanResourceTimeClockService;

	@GetMapping()
	public ResponseEntity<?> findByEmployeeIdAndHumanResourceTimeClockRecordBetweenOrderByHumanResourceTimeClockRecord(
			@RequestParam String emploeeId, @RequestParam LocalDate humanResourceTimeClockRecordStar,
			@RequestParam LocalDate humanResourceTimeClockRecordEnd) {
		return ResponseEntity.ok(humanResourceTimeClockService
				.findByEmployeeIdAndHumanResourceTimeClockRecordBetweenOrderByHumanResourceTimeClockRecord(emploeeId,
						humanResourceTimeClockRecordStar, humanResourceTimeClockRecordEnd));
	}

	@PostMapping("/puch")
	public ResponseEntity<?> save(@Valid @RequestBody HumanResourceTimeClockSaveDTO humanResourceTimeClockSaveDTO) {
		humanResourceTimeClockService.save(humanResourceTimeClockSaveDTO);
		return ResponseEntity.ok().build();
	}

}
