package com.control.backend.auth.model;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@Data
@Table(name = "[resource]")
public class Resource implements Serializable {

	private static final long serialVersionUID = -2726330731839179790L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "resource_id", nullable = false, unique = true)
	private Long resourceId;

	@Column(name = "resource_is_enabled", nullable = false)
	private Boolean resourceIsEnabled;

	@Column(name = "resource_name", length = 50, nullable = false, unique = true)
	private String resourceName;

	@Column(name = "resource_path", length = 800, nullable = false, unique = true)
	private String resourcePath;

	@DateTimeFormat
	@UpdateTimestamp
	@Column(name = "resource_updated_at", nullable = true, insertable = false, updatable = true)
	private LocalDateTime resourceUpdatedAt;

	@DateTimeFormat
	@UpdateTimestamp
	@Column(name = "resource_Logged_at", nullable = true, insertable = false, updatable = true)
	private LocalDateTime resourceLoggedAt;

}
