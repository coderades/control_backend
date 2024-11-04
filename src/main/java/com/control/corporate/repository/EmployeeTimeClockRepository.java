package com.control.corporate.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.control.corporate.model.EmployeeTimeClock;

public interface EmployeeTimeClockRepository extends JpaRepository<EmployeeTimeClock, String> {

	@Query("SELECT employeeTimeClock FROM EmployeeTimeClock employeeTimeClock WHERE employeeId = ?1 AND CAST(employeeTimeClockPunchAt AS LocalDate) BETWEEN ?2 and ?3")
	List<EmployeeTimeClock> findByPunsh(String employeeId, LocalDate employeeTimeClockPunchStart,
			LocalDate employeeTimeClockPunchEnd);

}
