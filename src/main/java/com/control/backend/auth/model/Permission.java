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
@Table(name = "[permission]")
public class Permission implements Serializable {

	private static final long serialVersionUID = 263569007028366972L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "permission_id", nullable = false, unique = true)
	private Long permissionId;

	@Column(name = "permission_is_wildcard", nullable = false)
	private Boolean permissionIsWildcard;

	@Column(name = "role_id", nullable = true, unique = true)
	private Long roleId;

	@Column(name = "method_id", nullable = false, unique = true)
	private Long methodId;

	@Column(name = "resource_id", nullable = false, unique = true)
	private Long resourceId;

	@DateTimeFormat
	@UpdateTimestamp
	@Column(name = "permission_updated_at", nullable = true, insertable = false, updatable = true)
	private LocalDateTime permissionUpdatedAt;

	@DateTimeFormat
	@UpdateTimestamp
	@Column(name = "permission_Logged_at", nullable = true, insertable = false, updatable = true)
	private LocalDateTime permissionLoggedAt;

}
