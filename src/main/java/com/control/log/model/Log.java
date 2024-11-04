package com.control.log.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "log")
@Data
@DynamicUpdate
public class Log implements Serializable {

	private static final long serialVersionUID = 5815192118423456003L;

	@Id
	@Column(name = "log_id", columnDefinition = "uuid", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.UUID)
	private String logId;

	@Column(name = "log_user_id",  columnDefinition = "uuid", nullable = false, unique = false)
	private String logUserId;
	
	@Column(name = "log_session", length = 32, nullable = true)
	private String logSession;

	@Column(name = "log_level", length = 10, nullable = false)
	private String logLevel;

	@Column(name = "log_Logger", length = 800, nullable = false)
	private String logLogger;

	@Column(name = "log_message", length = 800, nullable = false)
	private String logMessage;

	@Column(name = "log_exception", length = 800, nullable = false)
	private String logException;
	
	@Column(name = "log_created_at", nullable = false, insertable = true, updatable = false)
	@DateTimeFormat
	@CreationTimestamp
	private LocalDateTime logCreatedAt;

}
