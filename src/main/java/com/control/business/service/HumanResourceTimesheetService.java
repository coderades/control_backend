package com.control.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.business.model.HumanResourceTimesheet;
import com.control.business.model.dto.HumanResourceTimesheetPuchItDTO;
import com.control.business.model.enumerator.HumanResourceTimesheetMoviment;
import com.control.business.repository.HumanResourceTimesheetRepository;

@Service
public class HumanResourceTimesheetService {

	@Autowired
	private HumanResourceTimesheetRepository humanResourceTimesheetRepository;

	public Page<HumanResourceTimesheet> findAll(Pageable pageable) {
		final var humanResourceTimesheet = humanResourceTimesheetRepository.findAll(pageable);
		return humanResourceTimesheet;
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(HumanResourceTimesheetPuchItDTO humanResourceTimesheetPuchItDTO) {
		var humanResourceTimesheet = new HumanResourceTimesheet();

		humanResourceTimesheet = humanResourceTimesheetRepository.findByUserIdAndHumanResourceTimesheetDate(
				humanResourceTimesheetPuchItDTO.userId(), humanResourceTimesheetPuchItDTO.humanResourceTimesheetDate());

		if (humanResourceTimesheet == null) {
			humanResourceTimesheet = new HumanResourceTimesheet();
			humanResourceTimesheet.setUserId(humanResourceTimesheetPuchItDTO.userId());
			humanResourceTimesheet
					.setHumanResourceTimesheetDate(humanResourceTimesheetPuchItDTO.humanResourceTimesheetDate());
		}

		if (humanResourceTimesheetPuchItDTO.humanResourceTimesheetMovement() == HumanResourceTimesheetMoviment.in) {
			switch (humanResourceTimesheetPuchItDTO.humanResourceTimesheetPeriod()) {
			case 1 -> {
				humanResourceTimesheet.setHumanResourceTimesheetPeriod1In(
						humanResourceTimesheetPuchItDTO.humanResourceTimesheetTime());
			}
			case 2 -> {
				humanResourceTimesheet.setHumanResourceTimesheetPeriod2In(
						humanResourceTimesheetPuchItDTO.humanResourceTimesheetTime());
			}
			case 3 -> {
				humanResourceTimesheet.setHumanResourceTimesheetPeriod3In(
						humanResourceTimesheetPuchItDTO.humanResourceTimesheetTime());
			}
			}

		}

		if (humanResourceTimesheetPuchItDTO.humanResourceTimesheetMovement() == HumanResourceTimesheetMoviment.out) {
			switch (humanResourceTimesheetPuchItDTO.humanResourceTimesheetPeriod()) {
			case 1 -> {
				humanResourceTimesheet.setHumanResourceTimesheetPeriod1Out(
						humanResourceTimesheetPuchItDTO.humanResourceTimesheetTime());
			}
			case 2 -> {
				humanResourceTimesheet.setHumanResourceTimesheetPeriod2Out(
						humanResourceTimesheetPuchItDTO.humanResourceTimesheetTime());
			}
			case 3 -> {
				humanResourceTimesheet.setHumanResourceTimesheetPeriod3Out(
						humanResourceTimesheetPuchItDTO.humanResourceTimesheetTime());
			}
			}
		}

		humanResourceTimesheetRepository.save(humanResourceTimesheet);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(String humanResourceTimesheetId) {
		humanResourceTimesheetRepository.deleteById(humanResourceTimesheetId);
	}

}
