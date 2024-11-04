package com.control.corporate.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import com.control.corporate.model.enumerator.EmployeeTimeClockEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "[employee_time_clock]")
@Data
@DynamicUpdate
public class EmployeeTimeClock implements Serializable {

	private static final long serialVersionUID = 5564696185958520215L;

	@Id
	@Column(name = "employee_time_clock_id", columnDefinition = "uuid", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.UUID)
	private String employeeTimeClockId;

	@Column(name = "employee_id", columnDefinition = "uuid", nullable = false)
	private String employeeId;

	@Column(name = "employee_time_clock_punch_at", nullable = false)
	@DateTimeFormat
	private LocalDateTime employeeTimeClockPunchAt;

	@Column(name = "employee_time_clock_period", nullable = false)
	private Integer employeeTimeClockPeriod;

	@Column(name = "employee_time_clock_action", length = 3, nullable = false)
	private EmployeeTimeClockEnum employeeTimeClockAction;

	@Column(name = "employee_time_clock_ip", length = 40, nullable = false)
	private String employeeTimeClockIp;

	@Column(name = "employee_time_clock_latitude", precision = 8, scale = 6, nullable = true)
	private BigDecimal employeeTimeClockLatitude;

	@Column(name = "employee_time_clock_longitude", precision = 8, scale = 6, nullable = true)
	private BigDecimal employeeTimeClockLongitude;

}
