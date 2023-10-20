package com.control.model;

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
@Table(name = "[role]")
@Data
public class Role implements Serializable {

	private static final long serialVersionUID = -5796366343084004995L;

	@Id
	@Column(name = "role_id", columnDefinition = "uuid", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.UUID)
	private String roleId;

	@Column(name = "role_is_enabled", nullable = false)
	private Boolean roleIsEnabled;

	@Column(name = "role_name", length = 50, nullable = false, unique = true)
	private String roleName;

	@Column(name = "role_created_on", nullable = false, insertable = true, updatable = false)
	@DateTimeFormat
	@CreationTimestamp
	private LocalDateTime roleCreatedOn;
	
	@Column(name = "role_created_by", nullable = false, insertable = true, updatable = false)
	private String roleCreatedBy;

	@Column(name = "role_updated_on", nullable = true, insertable = false, updatable = true)
	@DateTimeFormat
	@UpdateTimestamp
	private LocalDateTime roleUpdatedOn;
	
	@Column(name = "role_updated_by", nullable = false, insertable = true, updatable = false)
	private String roleUpdatedBy;

}
