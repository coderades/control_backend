package com.control.corporate.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.control.corporate.model.HumanResourceTimeClock;
import com.control.corporate.model.dto.HumanResourceTimeClockSaveDTO;

public interface HumanResourceTimeClockRepository extends JpaRepository<HumanResourceTimeClock, String> {

	void save(HumanResourceTimeClockSaveDTO humanResourceTimeClockSaveDTO);

	// @Query("SELECT humanResourceTimeClock FROM HumanResourceTimeClock
	// humanResourceTimeClock")
//	@Query(value = "SELECT MIN(CASE WHEN human_resource_time_clock_period = 1 AND human_resource_time_clock_action = 'in' THEN human_resource_time_clock_record END)  human_resource_time_time_1_in_record FROM human_resource_time_clock WHERE [employee_id] = 'f9a1da70-68a4-eb11-a3d3-6245b4ea43a3' AND human_resource_time_clock_date BETWEEN '2024-08-23' AND '2024-08-23'", nativeQuery = true)
//	String findByUser();

	List<HumanResourceTimeClock> findByEmployeeIdAndHumanResourceTimeClockRecordBetweenOrderByHumanResourceTimeClockRecord(String EmployeeId, LocalDateTime HumanResourceTimeClockRecordStar,
			LocalDateTime HumanResourceTimeClockRecordEnd);

}
