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

	@Column(name = "resource_is_public", nullable = false)
	private Boolean resourceIsPublic;

	@Column(name = "resource_name", length = 50, nullable = false, unique = true)
	private String resourceName;

	@Column(name = "resource_path", length = 800, nullable = false, unique = true)
	private String resourcePath;

	@Column(name = "resource_is_create", nullable = false)
	private Boolean resourceIsCreate;

	@Column(name = "resource_is_read", nullable = false)
	private Boolean resourceIsRead;

	@Column(name = "resource_is_update", nullable = false)
	private Boolean resourceIsUpdate;

	@Column(name = "resource_is_delete", nullable = false)
	private Boolean resourceIsDelete;

	@DateTimeFormat
	@UpdateTimestamp
	@Column(name = "resource_updated_at", nullable = true, insertable = false, updatable = true)
	private LocalDateTime resourceUpdatedAt;

	@DateTimeFormat
	@UpdateTimestamp
	@Column(name = "resource_Logged_at", nullable = true, insertable = false, updatable = true)
	private LocalDateTime resourceLoggedAt;

}
