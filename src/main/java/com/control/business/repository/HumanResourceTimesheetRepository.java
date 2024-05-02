package com.control.business.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.control.business.model.HumanResourceTimesheet;

public interface HumanResourceTimesheetRepository extends JpaRepository<HumanResourceTimesheet, String> {

	HumanResourceTimesheet findByUserIdAndHumanResourceTimesheetDate(String userId,
			LocalDate humanResourceTimesheetPeriodDate);

//	@Query("SELECT humanResourceTimesheet FROM HumanResourceTimesheet humanResourceTimesheet WHERE (userId = ?1) AND (humanResourceTimesheetPeriod = ?2) AND (CAST(humanResourceTimesheetIn AS DATE) = CAST(?3 AS DATE))")
//	HumanResourceTimesheet findByPeriodIn(String userId, Integer humanResourceTimesheetPeriod,
//			LocalDateTime humanResourceTimesheetIn);
//
//	@Query("SELECT humanResourceTimesheet FROM HumanResourceTimesheet humanResourceTimesheet WHERE (userId = ?1) AND (humanResourceTimesheetPeriod = ?2) AND (CAST(humanResourceTimesheetOut AS DATE) = CAST(?3 AS DATE))")
//	HumanResourceTimesheet findByPeriodOut(String userId, Integer humanResourceTimesheetPeriod,
//			LocalDateTime humanResourceTimesheetIn);

}

