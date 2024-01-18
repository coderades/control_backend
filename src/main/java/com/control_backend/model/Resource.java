package com.control_backend.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "[resource]")
@Data
public class Resource implements Serializable {

	private static final long serialVersionUID = -5796366343084004995L;

	@Id
	@Column(name = "resource_id", columnDefinition = "uuid", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.UUID)
	private String resourceId;

	@Column(name = "application_id", columnDefinition = "uuid", nullable = false)
	private String applicationId;

	@Column(name = "resource_is_enabled", nullable = false)
	private Boolean resourceIsEnabled;

	@Column(name = "resource_name", length = 50, nullable = false, unique = true)
	private String resourceName;

	@Column(name = "resource_path", length = 500, nullable = false, unique = false)
	private String resourcePath;

	@Column(name = "resource_created_on", nullable = false, insertable = true, updatable = false)
	@DateTimeFormat
	@CreationTimestamp
	private LocalDateTime resourceCreatedOn;

	@Column(name = "resource_updated_on", nullable = true, insertable = false, updatable = true)
	@DateTimeFormat
	@UpdateTimestamp
	private LocalDateTime resourceUpdatedOn;
	
    @OneToMany
    @JoinColumn(name="permission_id")
    private List<Permission> permissions;

}
