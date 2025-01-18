package com.control.backend.auth.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.uuid.Generators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "[role]")
public class Role implements Serializable {

	private static final long serialVersionUID = -5796366343084004995L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id", nullable = false, unique = true)
	private Long roleId;

	@Column(name = "role_public_id", columnDefinition = "uuid", nullable = false, unique = true, updatable = false)
	private String rolePublicId = Generators.timeBasedEpochGenerator().generate().toString();

	@Column(name = "role_is_enabled", nullable = false)
	private Boolean roleIsEnabled;

	@Column(name = "role_name", length = 50, nullable = false, unique = true)
	private String roleName;

	@Column(name = "role_description", length = 400, nullable = false, unique = false)
	private String roleDescription;

	@DateTimeFormat
	@CreationTimestamp
	@Column(name = "role_created_at", nullable = false, insertable = true, updatable = false)
	private LocalDateTime roleCreatedAt;

	@DateTimeFormat
	@UpdateTimestamp
	@Column(name = "role_updated_at", nullable = true, insertable = false, updatable = true)
	private LocalDateTime roleUpdatedAt;

}
