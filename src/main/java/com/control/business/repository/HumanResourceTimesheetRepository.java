package com.control.business.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.control.business.model.HumanResourceTimesheet;

public interface HumanResourceTimesheetRepository extends JpaRepository<HumanResourceTimesheet, String> {

	@Query("UPDATE human_resource_timesheet SET human_resource_timesheet_period_1_start = ?2 WHERE (human_resource_timesheet_id = ?1)")
	Integer findByApplicationTokenExpiresTime(String humanResourceTimesheetId,
			LocalDateTime humanResourceTimesheetPeriod1Start);

}
