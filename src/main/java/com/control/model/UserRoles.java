package com.control.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "[user_roles]")
@Data
@DynamicUpdate
public class UserRoles implements Serializable  {

	private static final long serialVersionUID = -7304073788175251674L;

	@Id
	@Column(name = "user_roles_id", columnDefinition = "uuid", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.UUID)
	private String userRolesId;

	@Column(name = "user_id", columnDefinition = "uuid", nullable = false)
	private String userId;

	@Column(name = "role_id", columnDefinition = "uuid", nullable = false)
	private String roleId;

	@Column(name = "user_roles_created", nullable = false, insertable = true, updatable = false)
	@DateTimeFormat
	@CreationTimestamp
	private LocalDateTime userRolesCreated;

}
