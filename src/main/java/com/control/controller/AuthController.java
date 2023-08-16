package com.control.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.control.model.User;
import com.control.model.dto.AuthDTO;
import com.control.service.TokenService;

@RestController
public class AuthController {

	@Autowired
	private TokenService tokenService;

	@Autowired(required = false)
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthDTO authDTO) {
		final var user = (User) authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authDTO.userName(), authDTO.userPassword()))
				.getPrincipal();

		final var json = new HashMap<String, String>();
		json.put("access_token", tokenService.create(user));
		json.put("token_type", "Bearer");
		json.put("expired_in", tokenService.getWithExpiresAt() + " minutes");
		
		return ResponseEntity.ok(json);
	}

}
