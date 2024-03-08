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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "[permission]")
@Data
public class Permission implements Serializable {

	private static final long serialVersionUID = -8621485068128163556L;

	@Id
	@Column(name = "permission_id", columnDefinition = "uuid", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.UUID)
	private String permissionId;

	@Column(name = "resource_id", columnDefinition = "uuid", nullable = false)
	private String resourceId;

	@Column(name = "permission_is_public", nullable = false)
	private Boolean permissionIsPublic;

	@Column(name = "permission_method", nullable = false, unique = false)
	// @Enumerated(EnumType.STRING)
	private String permissionMethod;

	@Column(name = "permission_created_on", nullable = false, insertable = true, updatable = false)
	@DateTimeFormat
	@CreationTimestamp
	private LocalDateTime permissionCreatedOn;

	@Column(name = "permission_updated_on", nullable = true, insertable = false, updatable = true)
	@DateTimeFormat
	@UpdateTimestamp
	private LocalDateTime permissionUpdatedOn;

	@ManyToOne
	private Resource resource;

}
