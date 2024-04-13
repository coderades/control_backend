package com.control.business.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.control.business.model.HumanResourceTimesheet;

public interface HumanResourceTimesheetRepository extends JpaRepository<HumanResourceTimesheet, String> {

	@Query("UPDATE HumanResourceTimesheet SET humanResourceTimesheetPeriod1Start = ?2 WHERE (humanResourceTimesheetId = ?1)")
	Integer findByApplicationTokenExpiresTime(String humanResourceTimesheetId,
			LocalDateTime humanResourceTimesheetPeriod1Start);

}
