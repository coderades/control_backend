package com.control.model.log;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "[log]")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "logId")
public class Log implements Serializable {

	private static final long serialVersionUID = 402068483477303936L;

	@Id
	@Column(name = "log_id", columnDefinition = "uuid", nullable = false, unique = true)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String logId;

	@Column(name = "log_session_id", columnDefinition = "uuid", nullable = false)
	private String logSessionId;

	@Column(name = "log_created", nullable = false, insertable = true, updatable = false)
	@DateTimeFormat
	@CreationTimestamp
	private LocalDateTime logCreated;

	@Column(name = "log_level", length = 5, nullable = false, unique = false)
	private String logLevel;

	@Column(name = "log_pid", nullable = false, unique = false)
	private Integer logPid;

	@Column(name = "log_thread", length = 15, nullable = false, unique = false)
	private String logThread;

	@Column(name = "log_class", length = 200, nullable = false, unique = false)
	private String logClass;

	@Column(name = "log_method", length = 15, nullable = false, unique = false)
	private String logMethod;

	@Column(name = "log_message", nullable = false, unique = false)
	private String logMessage;

}
