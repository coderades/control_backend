package com.control.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Entity
@Table(name = "[application]")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "applicationId")
public class Application implements Serializable {

	private static final long serialVersionUID = -527506195465983324L;

	@Id
	@Column(name = "application_id", columnDefinition = "uuid", nullable = false, unique = true)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String applicationId;

	@Column(name = "application_is_enabled", nullable = false)
	private Boolean applicationIsEnabled;

	@Column(name = "application_name", length = 50, nullable = false, unique = true)
	private String applicationName;

	@Column(name = "application_email", length = 50, nullable = true)
	private String applicationEmail;

	@Column(name = "application_secret", columnDefinition = "uuid", nullable = false, unique = true)
	private String applicationSecret;

	@Column(name = "application_created", nullable = false, insertable = true, updatable = false)
	@DateTimeFormat
	@CreationTimestamp
	private LocalDateTime applicationCreated;

	@Column(name = "application_updated", nullable = true, insertable = false, updatable = true)
	@DateTimeFormat
	@UpdateTimestamp
	private LocalDateTime applicationUpdated;

}
