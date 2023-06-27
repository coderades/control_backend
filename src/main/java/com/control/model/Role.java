package com.control.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "[role]")
@ToString(exclude = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "roleId")
public class Role implements Serializable {

	private static final long serialVersionUID = -5796366343084004995L;

	@Id
	@Column(name = "role_id", columnDefinition = "uuid", nullable = false, unique = true)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String roleId;

	@Column(name = "role_is_enabled", nullable = false)
	private Boolean roleIsEnabled;

	@Column(name = "role_name", length = 50, nullable = false, unique = true)
	private String roleName;

	@Column(name = "role_created", nullable = false, insertable = true, updatable = false)
	@DateTimeFormat
	@CreationTimestamp
	private LocalDateTime roleCreated;

	@Column(name = "role_updated", nullable = true, insertable = false, updatable = true)
	@DateTimeFormat
	@UpdateTimestamp
	private LocalDateTime roleUpdated;

	@ManyToMany(mappedBy = "roles")
	private List<User> users;

}
