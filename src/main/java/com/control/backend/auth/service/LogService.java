package com.control.backend.auth.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.control.backend.auth.model.dto.LogDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LogService {

	public void save(LogDTO logDTO) {
		try {
			final var headers = new LinkedMultiValueMap<String, String>();
			headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());

			new RestTemplate().postForObject("http://localhost:8083/api/log",
					new HttpEntity<String>(new ObjectMapper().writeValueAsString(logDTO), headers), String.class);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
}
