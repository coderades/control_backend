package com.control.backend.model;

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
	
	@Column(name = "application_public_key", columnDefinition = "uuid", nullable = false)
	private String applicationPublicKey;
		
	@Column(name = "application_secret_key", columnDefinition = "uuid", nullable = false)
	private String applicationSecretKey;
	
	@Column(name = "application_token_pass_phrase", length = 50, nullable = false, unique = false)
	private String applicationTokenPassPhrase;
	
	@Column(name = "application_token_expiration", nullable = false)
	private Integer applicationTokenExpiration;

	@Column(name = "application_created_at", nullable = false, insertable = true, updatable = false)
	@DateTimeFormat
	@CreationTimestamp
	private LocalDateTime applicationCreatedAt;

	@Column(name = "application_updated_at", nullable = true, insertable = false, updatable = true)
	@DateTimeFormat
	@UpdateTimestamp
	private LocalDateTime applicationUpdatedAt;

}
