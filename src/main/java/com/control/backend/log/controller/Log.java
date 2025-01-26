package com.control.backend.log.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.backend.log.model.dto.LogDTO;
import com.control.backend.log.service.LogService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/log", produces = MediaType.APPLICATION_JSON_VALUE)
public class Log {

	@Autowired
	private LogService logService;

	@Async
	@PostMapping()
	public void save(@Valid @RequestBody LogDTO logDTO) {
		logService.save(logDTO);
	}

}
