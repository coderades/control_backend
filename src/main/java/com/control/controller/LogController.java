package com.control.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.model.dto.LogSelectDTO;
import com.control.service.LogService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/log")
@Slf4j
public class LogController {

	private final LogService logService;

	public LogController(LogService logService) {
		this.logService = logService;
	}

	@GetMapping
	public ResponseEntity<?> findAll(Pageable pageable) {
		log.info("Pagination: {}", pageable);

		final var entity = logService.findAll(pageable);
		return !entity.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body(entity)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@GetMapping("/findByLog")
	public ResponseEntity<?> findByLevel(@RequestBody LogSelectDTO logSelectDTO) {
		log.info("Parameter: log={}", logSelectDTO);

		final var entity = logService.findByLog(logSelectDTO);
		return !entity.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body(entity)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
