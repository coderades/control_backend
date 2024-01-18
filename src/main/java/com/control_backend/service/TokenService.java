package com.control_backend.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.control_backend.model.User;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;

	public String create(User user) {
		try {
			return JWT.create().withIssuer("control").withSubject(user.getUsername()).withExpiresAt(expiredIn())
					.withExpiresAt(expiredIn()).sign(Algorithm.HMAC512(secret));
		} catch (JWTCreationException exception) {
			throw new RuntimeException(exception);
		}
	}

	public String getSubject(String token) {
		try {
			return JWT.require(Algorithm.HMAC512(secret)).withIssuer("control").build().verify(token).getSubject();
		} catch (JWTVerificationException exception) {
			throw new RuntimeException(exception);
		}
	}

	public Instant expiredIn() {
		return LocalDateTime.now().plusSeconds(120).toInstant(ZoneOffset.of("-03:00"));
	}

}