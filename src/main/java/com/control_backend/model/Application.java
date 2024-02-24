package com.control_backend.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "[application]")
@Data
public class Application implements Serializable {

	private static final long serialVersionUID = -5911557548691753968L;

	@Id
	@Column(name = "application_id", columnDefinition = "uuid", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.UUID)
	private String applicationId;

	@Column(name = "application_is_enabled", nullable = false)
	private Boolean applicationIsEnabled;

	@Column(name = "application_Name", length = 50, nullable = false, unique = false)
	private String applicationName;

	@Column(name = "application_email", length = 50, nullable = false, unique = false)
	private String applicationEmail;
	
	@Column(name = "application_access_token", columnDefinition = "uuid", nullable = false)
	private String applicationAccessToken;
	
	@Column(name = "application_access_token_expires_time", nullable = false)
	private Integer applicationAccessTokenExpiresTime;

	@Column(name = "application_created_on", nullable = false, insertable = true, updatable = false)
	@DateTimeFormat
	@CreationTimestamp
	private LocalDateTime applicationCreatedOn;

	@Column(name = "application_updated_on", nullable = true, insertable = false, updatable = true)
	@DateTimeFormat
	@UpdateTimestamp
	private LocalDateTime applicationUpdatedOn;

}
