package com.control.model.dto;

import java.io.Serializable;

import org.hibernate.annotations.DynamicUpdate;

import com.control.model.dto.validation.ExistsUserEmailForAnotherUserId;
import com.control.model.dto.validation.ExistsUserId;
import com.control.model.dto.validation.ExistsUserNameForAnotherUserId;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@DynamicUpdate
@ExistsUserNameForAnotherUserId( fieldId = "userId", fieldName = "userName")
@ExistsUserEmailForAnotherUserId( fieldId = "userId", fieldEmail = "userEmail")
public class UserPutDTO implements Serializable {

	private static final long serialVersionUID = 3445600659154104881L;

	@Id
	@NotBlank(message = "It cannot be blank")
	@ExistsUserId
	private String userId;

	@NotNull(message = "It cannot be null")
	private Boolean userIsEnabled;

	@NotNull(message = "It cannot be null")
	private Boolean userIsAccountNonExpired;

	@NotNull(message = "It cannot be null")
	private Boolean userIsAccountNonLocked;

	@NotNull(message = "It cannot be null")
	private Boolean userIsCredentialsNonExpired;

	@Size(min = 2, max = 50, message = "Enter between 2 and 50 characters")
	private String userName;

	@Email(message = "Incorrect format")
	@Size(min = 8, max = 50, message = "Enter between 8 and 50 characters")
	private String userEmail;

	private String userPasswordToken;

	private String userRememberToken;

	private Long userPinCode;

}
