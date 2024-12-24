package com.control.backend.auth.model;

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
@Data
@DynamicUpdate
@Table(name = "[user_roles]")
public class UserRoles implements Serializable {

	private static final long serialVersionUID = -7304073788175251674L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_roles_id", nullable = false, unique = true)
	private Long userRolesId;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "role_id", nullable = false)
	private Long roleId;

	@DateTimeFormat
	@CreationTimestamp
	@Column(name = "user_roles_created_At", nullable = false, insertable = true, updatable = false)
	private LocalDateTime userRolesCreatedAt;

}
