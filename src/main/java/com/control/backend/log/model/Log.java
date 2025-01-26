package com.control.backend.log.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.control.backend.log.model.enumerator.LogActionEnum;
import com.control.backend.log.model.enumerator.LogLevelEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "[log]")
public class Log implements Serializable {

	private static final long serialVersionUID = 2716126055640581877L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "log_id", nullable = false, unique = true, updatable = false)
	private String logId;

	@Column(name = "log_level", length = 7, nullable = false, unique = false)
	private LogLevelEnum logLevel;

	@Column(name = "log_session", length = 32, nullable = false, unique = false)
	private String logSession;

	@Column(name = "log_action", length = 10, nullable = false, unique = false)
	private LogActionEnum logAction;

	@Column(name = "log_logger", nullable = false, unique = false)
	private String logLogger;

	@DateTimeFormat
	@CreationTimestamp
	@Column(name = "log_created_at", nullable = false, insertable = true, updatable = false)
	private LocalDateTime logCreatedAt;

}
