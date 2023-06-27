package com.control.model.dto;

import java.io.Serializable;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserRolesPostDTO implements Serializable {

	private static final long serialVersionUID = 3445600659154104881L;
	
	@Id
	@Column(name = "user_roles_id", columnDefinition = "uuid", nullable = false, unique = true)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String userRolesId;

	@Column(name = "user_id", columnDefinition = "uuid", nullable = false)
	private String userId;

	@Column(name = "role_id", columnDefinition = "uuid", nullable = false)
	private String roleId;

}
