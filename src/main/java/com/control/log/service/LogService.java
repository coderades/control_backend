package com.control.log.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.log.model.Log;
import com.control.log.model.dto.LogInsertDTO;
import com.control.log.repository.LogRepository;

@Service
public class LogService {

	@Autowired
	private LogRepository logRepository;

	@Async
	@Transactional(rollbackFor = Exception.class)
	public void save(LogInsertDTO logInsertDTO) {
		final var log = new Log();
		BeanUtils.copyProperties(logInsertDTO, log);
		logRepository.save(log);
	}

}
