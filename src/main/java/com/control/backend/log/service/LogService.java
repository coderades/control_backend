package com.control.backend.log.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.backend.log.model.Log;
import com.control.backend.log.model.dto.LogDTO;
import com.control.backend.log.repository.LogRepository;

@Service
public class LogService {

	@Autowired
	private LogRepository logRepository;

	@Transactional(rollbackFor = Exception.class)
	public void save(LogDTO logDTO) {
		final var log = new Log();
		BeanUtils.copyProperties(logDTO, log);
		logRepository.save(log);
	}

}
