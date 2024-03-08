package com.control.backend.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "[role_permissions]")
@Data
public class RolePermissions implements Serializable {

	private static final long serialVersionUID = -8621485068128163556L;

	@Id
	@Column(name = "role_permissions_id", columnDefinition = "uuid", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.UUID)
	private String rolePermissionsId;

	@Column(name = "role_id", columnDefinition = "uuid", nullable = false)
	private String roleId;

	@Column(name = "permission_id", columnDefinition = "uuid", nullable = false)
	private String permissionId;

	@Column(name = "role_permissions_created_on", nullable = false, insertable = true, updatable = false)
	@DateTimeFormat
	@CreationTimestamp
	private LocalDateTime rolePermissionsCreatedOn;

}
