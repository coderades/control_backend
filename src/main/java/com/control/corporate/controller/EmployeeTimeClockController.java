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

import com.control.corporate.model.dto.EmployeeTimeClockSaveDTO;
import com.control.corporate.service.EmployeeTimeClockService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/corporate/employee/timeclock")
public class EmployeeTimeClockController {

	@Autowired
	private EmployeeTimeClockService employeeTimeClockService;

	@GetMapping("/findByPunsh")
	public ResponseEntity<?> findByPunsh(@RequestParam String employeeId,
			@RequestParam LocalDate employeeTimeClockPunchStart, @RequestParam LocalDate employeeTimeClockPunchEnd) {
		return ResponseEntity.ok(employeeTimeClockService.findByPunsh(employeeId, employeeTimeClockPunchStart,
				employeeTimeClockPunchEnd));
	}

	@PostMapping("/puch")
	public ResponseEntity<?> save(@Valid @RequestBody EmployeeTimeClockSaveDTO employeeTimeClockSaveDTO) {
		employeeTimeClockService.save(employeeTimeClockSaveDTO);
		return ResponseEntity.ok().build();
	}

}
