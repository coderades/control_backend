package com.control.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
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
@Table(name = "[application_tenant]")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "applicationTenantId")
public class ApplicationTenant implements Serializable {

	private static final long serialVersionUID = 921948580827701635L;

	@Id
	@Column(name = "application_tenant_id", columnDefinition = "uuid", nullable = false, unique = true)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String applicationTenantId;

	@Column(name = "application_id", columnDefinition = "uuid", nullable = false)
	private String applicationId;

	@Column(name = "application_tenant_is_enabled", nullable = false)
	private Boolean applicationTenantIsEnabled;

	@Column(name = "application_tenant_name", length = 30, nullable = false, unique = true)
	private String applicationTenantName;
	
	@Column(name = "application_tenant_url", length = 500, nullable = false, unique = false)
	private String applicationTenantUrl;

	@Column(name = "application_tenant_user_name", length = 50, nullable = false, unique = false)
	private String applicationTenantUserName;

	@Column(name = "application_tenant_password", length = 70, nullable = false, unique = false)
	private String applicationTenantPassword;

	@Column(name = "application_tenant_driver_class_name", length = 50, nullable = false, unique = false)
	private String applicationTenantDriverClassName;	
	
	@Column(name = "application_tenant_initialize", nullable = false)
	private Boolean applicationTenantInitialize;

	@Column(name = "application_tenant_created", nullable = false, insertable = true, updatable = false)
	@DateTimeFormat
	@CreationTimestamp
	private LocalDateTime applicationTenantCreated;

	@Column(name = "application_tenant_updated", nullable = true, insertable = false, updatable = true)
	@DateTimeFormat
	@UpdateTimestamp
	private LocalDateTime applicationTenantUpdated;

}
