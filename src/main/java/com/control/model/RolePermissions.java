package com.control.model;

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
@Table(name = "[role_permissions]")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "rolePermissionId")
public class RolePermissions implements Serializable  {

	private static final long serialVersionUID = 1875769542890469882L;

	@Id
	@Column(name = "role_permissions_id", columnDefinition = "uuid", nullable = false, unique = true)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String rolePermissionsId;

	@Column(name = "role_id", columnDefinition = "uuid", nullable = false)
	private String roleId;
	
	@Column(name = "resource_id", columnDefinition = "uuid", nullable = false)
	private String resourceId;
	
	@Column(name = "role_permissions_mod_is_create", nullable = false)
	private Boolean rolePermissionsModIsCreate;

	@Column(name = "role_permissions_mod_is_read", nullable = false)
	private Boolean rolePermissionsModIsRead;

	@Column(name = "role_permissions_mod_is_update", nullable = false)
	private Boolean rolePermissionsModIsUpdate;

	@Column(name = "role_permissions_mod_is_delete", nullable = false)
	private Boolean rolePermissionsModIsDelete;	

	@Column(name = "role_permissions_created", nullable = false, insertable = true, updatable = false)
	@DateTimeFormat
	@CreationTimestamp
	private LocalDateTime rolePermissionsCreated;

}
