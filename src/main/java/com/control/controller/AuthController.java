package com.control.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.model.User;
import com.control.model.dto.AuthDTO;
import com.control.service.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private TokenService tokenService;

	@Autowired(required = false)
	private AuthenticationManager authenticationManager;

	@PostMapping("/token")
	public ResponseEntity<?> token(@Valid @RequestBody AuthDTO authDTO) {
		final var user = (User) authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authDTO.userName(), authDTO.userPassword()))
				.getPrincipal();

		final var json = new HashMap<String, String>();
		json.put("token_type", "Bearer");
		json.put("expired_in", String.valueOf(tokenService.getWithExpiresAt()));
		json.put("user_id", user.getUserId());
		json.put("access_token", tokenService.create(user));

		return ResponseEntity.ok(json);
	}

}
