package com.control.business.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.business.model.HumanResourceTimesheet;
import com.control.business.model.dto.HumanResourceTimesheetPeriod1StartDTO;
import com.control.business.repository.HumanResourceTimesheetRepository;

@Service
public class HumanResourceTimesheetService {

	@Autowired
	private HumanResourceTimesheetRepository humanResourceTimesheetRepository;

	@Transactional(rollbackFor = Exception.class)
	public void save(HumanResourceTimesheetPeriod1StartDTO humanResourceTimesheetPeriod1StartDTO) {
		final var humanResourceTimesheet = new HumanResourceTimesheet();
		BeanUtils.copyProperties(humanResourceTimesheetPeriod1StartDTO, humanResourceTimesheet);
		humanResourceTimesheetRepository.save(humanResourceTimesheet);
	}

}
