package com.control.business.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.DynamicUpdate;
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
import lombok.ToString;

@Entity
@Table(name = "[human_resource_timesheet]")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DynamicUpdate
public class HumanResourceTimesheet implements Serializable {

	private static final long serialVersionUID = 5564696185958520215L;

	@Id
	@Column(name = "human_resource_timesheet_id", columnDefinition = "uuid", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.UUID)
	private String humanResourceTimesheetId;

	@Column(name = "user_id", columnDefinition = "uuid", nullable = false, updatable = false)
	private String userId;

	@Column(name = "human_resource_timesheet_date", nullable = false)
	@DateTimeFormat
	private LocalDate humanResourceTimesheetDate;

	@Column(name = "human_resource_timesheet_period_1_in", nullable = true)
	@DateTimeFormat
	private LocalTime humanResourceTimesheetPeriod1In;

	@Column(name = "human_resource_timesheet_period_1_out", nullable = true)
	@DateTimeFormat
	private LocalTime humanResourceTimesheetPeriod1Out;

	@Column(name = "human_resource_timesheet_period_2_in", nullable = true)
	@DateTimeFormat
	private LocalTime humanResourceTimesheetPeriod2In;

	@Column(name = "human_resource_timesheet_period_2_out", nullable = true)
	@DateTimeFormat
	private LocalTime humanResourceTimesheetPeriod2Out;

	@Column(name = "human_resource_timesheet_period_3_in", nullable = true)
	@DateTimeFormat
	private LocalTime humanResourceTimesheetPeriod3In;

	@Column(name = "human_resource_timesheet_period_3_out", nullable = true)
	@DateTimeFormat
	private LocalTime humanResourceTimesheetPeriod3Out;

}
