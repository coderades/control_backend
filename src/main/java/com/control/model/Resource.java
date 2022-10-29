package com.control.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Entity
@Table(name = "[resource]")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "resourceId")
public class Resource implements Serializable {

	private static final long serialVersionUID = -8130065148349958506L;

	@Id
	@Column(name = "resource_id", columnDefinition = "uuid", nullable = false, unique = true)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String resourceId;

	@Column(name = "application_id", columnDefinition = "uuid", nullable = false)
	private String applicationId;

	@Column(name = "resource_is_enabled", nullable = false)
	private Boolean resourceIsEnabled;

	@Column(name = "resource_name", length = 50, nullable = false, unique = true)
	@Size(min = 2, max = 50)
	@NotBlank(message = "It cannot be empty")
	private String resourceName;

	@Column(name = "resource_url", length = 200, nullable = false, unique = true)
	@Size(min = 2, max = 200)
	@NotBlank(message = "It cannot be empty")
	private String resourceUrl;

	@Column(name = "resource_mod_is_create", nullable = false)
	private Boolean resourceModIsCreate;

	@Column(name = "resource_mod_is_read", nullable = false)
	private Boolean resourceModIsRead;

	@Column(name = "resource_mod_is_update", nullable = false)
	private Boolean resourceModIsUpdate;

	@Column(name = "resource_mod_is_delete", nullable = false)
	private Boolean resourceModIsDelete;

	@Column(name = "resource_created", nullable = false, insertable = true, updatable = false)
	@DateTimeFormat
	@CreationTimestamp
	private LocalDateTime resourceCreated;

	@Column(name = "resource_updated", nullable = true, insertable = false, updatable = true)
	@DateTimeFormat
	@UpdateTimestamp
	private LocalDateTime resourceUpdated;

}
