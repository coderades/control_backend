package com.control.backend.auth.model;

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
@Data
@Table(name = "[role_resources]")
public class RoleResources  implements Serializable{

	private static final long serialVersionUID = -5773636847545751880L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_resources_id", nullable = false, unique = true)
	private Long roleResourcesId;
	
	@Column(name = "role_id", nullable = false, unique = true)
	private Long roleId;

	@Column(name = "resources_id", nullable = false, unique = true)
	private Long resourcesId;
	
	@Column(name = "role_resource_is_create", nullable = false)
	private Boolean roleResourceIsCreate;

	@Column(name = "role_resource_is_read", nullable = false)
	private Boolean roleResourceIsRead;

	@Column(name = "role_resource_is_update", nullable = false)
	private Boolean roleResourceIsUpdate;

	@Column(name = "role_resource_is_delete", nullable = false)
	private Boolean roleResourceIsDelete;

	@DateTimeFormat
	@CreationTimestamp
	@Column(name = "role_resource_created_at", nullable = false, insertable = true, updatable = false)
	private LocalDateTime roleResourceCreatedAt;

	@DateTimeFormat
	@UpdateTimestamp
	@Column(name = "role_resource_updated_at", nullable = true, insertable = false, updatable = true)
	private LocalDateTime roleResourceUpdatedAt;
	
}
