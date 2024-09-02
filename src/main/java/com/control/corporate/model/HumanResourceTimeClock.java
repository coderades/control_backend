package com.control.corporate.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.control.corporate.model.enumerator.HumanResourceTimeClockActionEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "[human_resource_time_clock]")
@Data
@DynamicUpdate
public class HumanResourceTimeClock implements Serializable {

	private static final long serialVersionUID = 5564696185958520215L;

	@Id
	@Column(name = "human_resource_time_clock_id", columnDefinition = "uuid", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.UUID)
	private String humanResourceTimeClockId;

	@Column(name = "employee_id", columnDefinition = "uuid", nullable = false)
	private String employeeId;

	@Column(name = "human_resource_time_clock_period", nullable = false)
	private Integer humanResourceTimeClockPeriod;

	@Column(name = "human_resource_time_clock_action", length = 3, nullable = false)
	private HumanResourceTimeClockActionEnum humanResourceTimeClockAction;

	@Column(name = "human_resource_time_clock_record", nullable = false)
	@DateTimeFormat
	private LocalDateTime humanResourceTimeClockRecord;

	@Column(name = "human_resource_time_clock_ip", length = 40, nullable = false)
	private String humanResourceTimeClockIp;

	@Column(name = "human_resource_time_clock_latitude", precision = 8, scale = 6, nullable = true)
	private BigDecimal humanResourceTimeClockLatitude;

	@Column(name = "human_resource_time_clock_longitude", precision = 8, scale = 6, nullable = true)
	private BigDecimal humanResourceTimeClockLongitude;

	@Column(name = "human_resource_time_clock_updated_on", nullable = true, insertable = false, updatable = true)
	@DateTimeFormat
	@UpdateTimestamp
	private LocalDateTime humanResourceTimeClockUpdatedOn;
}
