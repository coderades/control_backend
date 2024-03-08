package com.control.backend.controller;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.backend.model.User;
import com.control.backend.model.dto.AuthDTO;
import com.control.backend.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthDTO authDTO) {
		final var json = new LinkedHashMap<String, Object>();
		final var user = (User) authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authDTO.userName(), authDTO.userPassword()))
				.getPrincipal();
		final var accessToken = authService.generateJWTToken(user, authDTO.applicationId());

		try {
			final var timeZoneOffSet = TimeZone.getDefault().getOffset(0) / 1000L;
			final var tokenHeader = new ObjectMapper().readTree(authService.decodingJWTTokenHeader(accessToken));
			final var tokenPayload = new ObjectMapper().readTree(authService.decodingJWTTokenPayload(accessToken));
		
			json.put("access_token", accessToken);
			json.put("access_type", tokenHeader.get("typ").asText());
			json.put("access_auth", tokenPayload.get("aut").asText());
			json.put("user_id", tokenPayload.get("uid").asText());
			json.put("token_id", tokenPayload.get("jti").asText());
			json.put("issued_at", Instant.ofEpochSecond(tokenPayload.get("iat").asLong() + timeZoneOffSet));
			json.put("expired_at", Instant.ofEpochSecond(tokenPayload.get("exp").asLong() + timeZoneOffSet));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		authService.saveLoggedOn(user.getUserId());

		return ResponseEntity.ok(json);
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletRequest request) {
		String token = request.getHeader("Authorization");

		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);

			var remoteAddr = request.getHeader("X-FORWARDED-FOR");

			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}

		return ResponseEntity.ok().build();
	}

}
