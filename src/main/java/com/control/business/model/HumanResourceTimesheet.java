package com.control.business.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "[human_resource_timesheet]")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HumanResourceTimesheet implements Serializable {

	private static final long serialVersionUID = 5564696185958520215L;

	@Id
	@Column(name = "human_resource_timesheet_id", columnDefinition = "uuid", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.UUID)
	private String humanResourceTimesheetId;

	@Column(name = "user_id", columnDefinition = "uuid", nullable = false)
	private String userId;

	@Column(name = "human_resource_timesheet_period_1_start", nullable = true)
	@DateTimeFormat
	private LocalDateTime humanResourceTimesheetPeriod1Start;

	@Column(name = "human_resource_timesheet_period_1_end", nullable = true)
	@DateTimeFormat
	private LocalDateTime humanResourceTimesheetPeriod1End;

	@Column(name = "human_resource_timesheet_period_2_start", nullable = true)
	@DateTimeFormat
	private LocalDateTime humanResourceTimesheetPeriod2Start;

	@Column(name = "human_resource_timesheet_period_2_end", nullable = true)
	@DateTimeFormat
	private LocalDateTime humanResourceTimesheetPeriod2End;

	@Column(name = "human_resource_timesheet_period_3_start", nullable = true)
	@DateTimeFormat
	private LocalDateTime humanResourceTimesheetPeriod3Start;

	@Column(name = "human_resource_timesheet_period_3_end", nullable = true)
	@DateTimeFormat
	private LocalDateTime humanResourceTimesheetPeriod3End;

}
