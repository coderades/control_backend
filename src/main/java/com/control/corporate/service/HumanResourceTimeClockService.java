package com.control.corporate.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.corporate.model.HumanResourceTimeClock;
import com.control.corporate.model.dto.HumanResourceTimeClockSaveDTO;
import com.control.corporate.repository.HumanResourceTimeClockRepository;

@Service
public class HumanResourceTimeClockService {

	@Autowired
	private HumanResourceTimeClockRepository humanResourceTimeClockRepository;

	public List<HumanResourceTimeClock> findByEmployeeIdAndHumanResourceTimeClockRecordBetweenOrderByHumanResourceTimeClockRecord(
			String emploeedId, LocalDate humanResourceTimeClockRecordStar, LocalDate humanResourceTimeClockRecordEnd) {
		final var humanResourceTimeClock = humanResourceTimeClockRepository
				.findByEmployeeIdAndHumanResourceTimeClockRecordBetweenOrderByHumanResourceTimeClockRecord(emploeedId,
						LocalDateTime.parse(humanResourceTimeClockRecordStar + "T00:00"),
						LocalDateTime.parse(humanResourceTimeClockRecordEnd + "T23:59"));
		return humanResourceTimeClock;
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(HumanResourceTimeClockSaveDTO humanResourceTimeClockSaveDTO) {
		final var humanResourceTimeCard = new HumanResourceTimeClock();
		BeanUtils.copyProperties(humanResourceTimeClockSaveDTO, humanResourceTimeCard);
		humanResourceTimeCard.setHumanResourceTimeClockRecord(LocalDateTime.now());
		humanResourceTimeClockRepository.save(humanResourceTimeCard);
	}

}
