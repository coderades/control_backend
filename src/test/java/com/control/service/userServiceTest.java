package com.control.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class userServiceTest {

	@Autowired
	MockMvc mockMvc;

//	@Autowired
//	User user;
//
//	@BeforeEach
//	void up() {
//		user.setUserIsEnabled(true);
//		user.setUserIsAccountNonExpired(true);
//		user.setUserIsAccountNonLocked(true);
//		user.setUserIsCredentialsNonExpired(true);
//		user.setUserName("castros8");
//		user.setUserEmail("castro@rar8.com");
//		user.setUserPassword("1234");
//		user.setUserPasswordToken(null);
//		user.setUserRememberToken(null);
//		user.setUserPinCode(null);
//	}

//	@Test
//	@Order(1)
//	@Rollback(value = true)
//	void saveInsert() throws Exception {
////		user = new User();
////		user.setUserId(userController
////				.save(new UserInsertDTO(true, true, true, true, "Test1", "test1@test.com", "1234", null, null, null))
////				.getBody().toString());
//
//		var userInsertDTO = new UserInsertDTO(true, true, true, true, "Test1", "test1@test.com", "1234", null, null,
//				null);
//
//		mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
//				.content(new ObjectMapper().writeValueAsString(userInsertDTO)).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().is(200));
//	}

//	@Test
//	@Order(1)
//	public void nonexistentUserCannotGetToken() throws Exception {
//	    final String username = "2";
//	    final String password = "123";
//	    final String body = "{\"userName\":\"" + username + "\", \"userPassword\":\"" + password + "\"}";
//
//	    System.out.println(body);
//	    
//	    mockMvc.perform(post("/api/auth/token")
//	            .content(body))
//	            .andExpect(status().isOk()).andReturn();
//	}
//	
	
//	@Test
//	public void findAll() throws Exception {
//		mockMvc.perform(get("/api/user")).andExpect(status().isForbidden());
//	}
//
//	@Test
//	public void findById() throws Exception {
//		mockMvc.perform(get("/api/user/{userId}", "2f52eb42-eeb0-974a-91ad-2f1309532733"))
//				.andExpect(status().isForbidden());
//	}
//
//	@Test
//	public void findByName() throws Exception {
//		mockMvc.perform(get("/api/user/findByName/{userName}", "1")).andExpect(status().isOk());
//	}

//	@Test
//	@Order(3)
//	@Rollback(value = true)
//	void saveUpdate() {
//		userController.save(new UserUpdateDTO(user.getUserId(), true, true, true, true, "Test2", "test2@test.com", null,
//				null, null));
//
//		System.out.println(user.getUserId());
//	}

}
