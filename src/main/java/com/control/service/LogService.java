package com.control.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.control.repository.LogRepository;

@Service
public class LogService {

	private final LogRepository logRepository;

	public LogService(LogRepository logRepository) {
		this.logRepository = logRepository;
	}

	public Page<?> findAll(Pageable pageable) {
		final var entity = logRepository.findAll(pageable);
		return entity;
	}

	public List<?> findByLevel(String logLevel) {
		final var entity = logRepository.findByLogLevel(logLevel);
		return entity == null ? null : entity;
	}

}
