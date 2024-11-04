package com.control.corporate.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.corporate.model.EmployeeTimeClock;
import com.control.corporate.model.dto.EmployeeTimeClockSaveDTO;
import com.control.corporate.repository.EmployeeTimeClockRepository;

@Service
public class EmployeeTimeClockService {

	@Autowired
	private EmployeeTimeClockRepository employeeTimeClockRepository;

	public List<EmployeeTimeClock> findByPunsh(String employeeId, LocalDate employeeTimeClockPunchStart,
			LocalDate employeeTimeClockPunchEnd) {
		final var employeeTimeClock = employeeTimeClockRepository.findByPunsh(employeeId, employeeTimeClockPunchStart,
				employeeTimeClockPunchEnd);
		return employeeTimeClock;
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(EmployeeTimeClockSaveDTO employeeTimeClockSaveDTO) {
		final var employeeTimeClock = new EmployeeTimeClock();
		BeanUtils.copyProperties(employeeTimeClockSaveDTO, employeeTimeClock);
		employeeTimeClock.setEmployeeTimeClockPunchAt(LocalDateTime.now());
		employeeTimeClockRepository.save(employeeTimeClock);
	}

}
