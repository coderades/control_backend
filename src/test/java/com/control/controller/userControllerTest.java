package com.control.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.control.model.dto.UserInsertDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class userControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	@Order(1)
	void saveInsert() throws Exception {
		final var userInsertDTO = new UserInsertDTO(true, true, true, true, "Test1", "test1@test.com", "1234", null,
				null, null, null);

		mockMvc.perform(post("/api/user/save").contentType("application/json")
				.content(objectMapper.writeValueAsString(userInsertDTO))).andExpect(status().isCreated());
	}

	@Test
	@Order(2)
	void findAll() throws Exception {
		mockMvc.perform(get("/api/user")).andExpect(status().isOk());
	}

}
