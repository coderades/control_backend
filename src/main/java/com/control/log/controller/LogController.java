package com.control.log.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.log.model.dto.LogInsertDTO;
import com.control.log.service.LogService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/log")
public class LogController {

	@Autowired
	private LogService logService;

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody LogInsertDTO logInsertDTO) {
		logService.save(logInsertDTO);
		return ResponseEntity.created(null).build();
	}

}
