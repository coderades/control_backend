package com.control.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.control.controller.UserController;
import com.control.model.User;
import com.control.model.dto.UserInsertDTO;

@ExtendWith(MockitoExtension.class)
public class userServiceTest {

	@Mock
	private User user;
	
	@Mock
	private UserService userService;
	
	@Mock
	private UserInsertDTO userInsertDTO;

	@InjectMocks
	private UserController userController;

	@BeforeEach
	void setUp() {
		user.setUserIsEnabled(true);
		user.setUserIsAccountNonExpired(true);
		user.setUserIsAccountNonLocked(true);
		user.setUserIsCredentialsNonExpired(true);
		user.setUserName("Test1");
		user.setUserEmail("test1@test.com");
		user.setUserPassword("123");
		user.setUserPasswordToken(null);
		user.setUserRememberToken(null);
		user.setUserPinCode(null);
				
//		userInsertDTO.setUserIsEnabled(user.getUserIsEnabled());
//		userInsertDTO.setUserIsAccountNonExpired(user.getUserIsAccountNonExpired());
//		userInsertDTO.setUserIsAccountNonLocked(true);
//		userInsertDTO.setUserIsCredentialsNonExpired(true);
//		userInsertDTO.setUserName("Test1");
//		userInsertDTO.setUserEmail("test1@test.com");
//		userInsertDTO.setUserPassword("123");
//		userInsertDTO.setUserPasswordToken(null);
//		userInsertDTO.setUserRememberToken(null);
//		userInsertDTO.setUserPinCode(null);
	}

	@Test
	void findById() {
		userController.findById("2F52EB42-EEB0-974A-91AD-2F1309532733");
	}

//	@Test
//	@Order(1)
//	@Rollback(value = true)
//	@DisplayName("Test saveInsert Success")
//	public void saveInsert() {
//		userInsertDTO.setUserIsEnabled(true);
//		userInsertDTO.setUserIsAccountNonExpired(true);
//		userInsertDTO.setUserIsAccountNonLocked(true);
//		userInsertDTO.setUserIsCredentialsNonExpired(true);
//		userInsertDTO.setUserName("Test1");
//		userInsertDTO.setUserEmail("test1@test.com");
//		userInsertDTO.setUserPassword("123");
//		userInsertDTO.setUserPasswordToken(null);
//		userInsertDTO.setUserRememberToken(null);
//		userInsertDTO.setUserPinCode(null);
//
//		userId = userService.save(userInsertDTO);
//	}

//	@Test
//	@Order(2)
//	public void saveUpdate() {
//		System.out.println("-->" + userId);
//
//		final var userUpdateDTO = new UserUpdateDTO();
//		userUpdateDTO.setUserId(userId);
//		userUpdateDTO.setUserIsEnabled(false);
//		userUpdateDTO.setUserIsAccountNonExpired(false);
//		userUpdateDTO.setUserIsAccountNonLocked(false);
//		userUpdateDTO.setUserIsCredentialsNonExpired(false);
//		userUpdateDTO.setUserName("Test2");
//		userUpdateDTO.setUserEmail("test2@test.com");
//		userUpdateDTO.setUserPasswordToken(null);
//		userUpdateDTO.setUserRememberToken(null);
//		userUpdateDTO.setUserPinCode(null);
//
//		userService.save(userUpdateDTO);
//	}

}
