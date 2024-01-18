package com.control_backend.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control_backend.model.User;
import com.control_backend.model.dto.AuthDTO;
import com.control_backend.service.AuthService;
import com.control_backend.service.TokenService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthDTO authDTO) {
		final var user = (User) authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authDTO.userName(), authDTO.userPassword()))
				.getPrincipal();

		final var json = new HashMap<String, String>();
		json.put("access_token", tokenService.create(user));
		json.put("token_type", "Bearer");
		json.put("expired_in", tokenService.expiredIn().toString());

		authService.saveLoggedOn(user.getUserId());

		return ResponseEntity.ok(json);
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		String remoteAddr = "";
		
		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}

		return ResponseEntity.ok().build();
	}

}
