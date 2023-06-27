package com.control.controller.log;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.model.log.dto.LogSelectDTO;
import com.control.service.log.LogService;

@RestController
@RequestMapping("/api/log")
public class LogController {

	private final LogService logService;

	public LogController(LogService logService) {
		this.logService = logService;
	}

	@GetMapping
	public ResponseEntity<?> findAll(
			@SortDefault(sort = "LocalDateTime") @PageableDefault(size = 100) final Pageable pageable) {
		return ResponseEntity.ok(logService.findAll(pageable));
	}

	@GetMapping("/findByLog")
	public ResponseEntity<?> findByLevel(@RequestBody LogSelectDTO logSelectDTO) {
		return ResponseEntity.ok(logService.findByLog(logSelectDTO));
	}

}
