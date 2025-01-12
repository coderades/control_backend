package com.control.backend.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.backend.auth.exception.UnauthorizedException;
import com.control.backend.auth.model.dto.AuthDTO;
import com.control.backend.auth.model.dto.RefreshTokenDTO;
import com.control.backend.auth.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<?> auth(@RequestBody @Validated AuthDTO authDTO) {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(authDTO.userName(), authDTO.userPassword()));
			return ResponseEntity.ok(authService.getToken(authDTO));
		} catch (Exception e) {
			throw new UnauthorizedException(e);
		}
	}

	@PostMapping("/refreshtoken")
	public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenDTO requestRefreshDTO) {
		return ResponseEntity.ok(authService.getRefreshToken(requestRefreshDTO.refreshToken()));
	}

}
