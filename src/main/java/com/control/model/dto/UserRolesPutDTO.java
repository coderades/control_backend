package com.control.model.dto;

import java.io.Serializable;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserRolesPutDTO implements Serializable {

	private static final long serialVersionUID = 3445600659154104881L;
	
	@Column(name = "user_id", columnDefinition = "uuid", nullable = false)
	private String userId;

	@Column(name = "role_id", columnDefinition = "uuid", nullable = false)
	private String roleId;

}
