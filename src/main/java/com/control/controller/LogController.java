package com.control.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		return entity.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				: ResponseEntity.status(HttpStatus.FOUND).body(entity);
	}
	
	
	@GetMapping("/findByLevel/{log_level}")
	public ResponseEntity<?> findByLevel(@PathVariable("log_level") String log_level) {				
		log.info("Parameter: logLevel={}", log_level);

		final var entity = logService.findByLevel(log_level);
		return entity == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				: ResponseEntity.status(HttpStatus.FOUND).body(entity);
	}

}
