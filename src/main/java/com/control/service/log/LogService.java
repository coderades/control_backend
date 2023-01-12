package com.control.service.log;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.control.model.log.Log;
import com.control.model.log.dto.LogSelectDTO;
import com.control.repository.log.LogRepository;

@Service
public class LogService {

	private final LogRepository logRepository;

	public LogService(LogRepository logRepository) {
		this.logRepository = logRepository;
	}

	public Page<Log> findAll(Pageable pageable) {
		final var entity = logRepository.findAll(pageable);
		return entity;
	}

	public List<Log> findByLog(LogSelectDTO logSelectDTO) {
		final var entity = logRepository.findByLog(logSelectDTO.getLogId(), logSelectDTO.getLogCreatedStart(),
				logSelectDTO.getLogCreatedEnd(), logSelectDTO.getLogLevel(), logSelectDTO.getLogClass(),
				logSelectDTO.getLogMethod(), logSelectDTO.getLogMessage());
		return entity;
	}

}