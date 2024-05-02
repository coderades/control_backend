package com.control.backend.service;

import java.time.Instant;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.control.backend.model.User;
import com.control.backend.repository.UserRepository;

@Service
public class AuthService implements UserDetailsService {

	// -------------------------------------------------------
	// User
	// -------------------------------------------------------

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		return userRepository.findByUserName(username);
	}

	public void saveLoggedOn(String userId) {
		userRepository.updateLoggedOn(userId);
	}

	// -------------------------------------------------------
	// Token
	// -------------------------------------------------------

	@Autowired
	private ApplicationService applicationService;

	@Value("${api.security.token.secret}")
	private String secret;

	public String generateJWTToken(User user, String applicationId) {
		try {
			final var applicationTokenExpiration = applicationService.findByApplicationTokenExpiration(applicationId);
			return JWT.create().withIssuer("control").withSubject(user.getUsername()).withClaim("uid", user.getUserId())
					.withClaim("aut", "Bearer").withJWTId(UUID.randomUUID().toString()).withIssuedAt(Instant.now())
					.withExpiresAt(Instant.now().plusSeconds(applicationTokenExpiration.applicationTokenExpiration()))
					.sign(Algorithm.HMAC512(secret));
		} catch (JWTCreationException exception) {
			throw new RuntimeException(exception);
		}
	}

	public String verifyJWTToken(String token) {
		try {
			return JWT.require(Algorithm.HMAC512(secret)).withIssuer("control").build().verify(token).getSubject();
		} catch (JWTVerificationException exception) {
			throw new RuntimeException(exception);
		}
	}

	public String decodingJWTTokenHeader(String accessToken) {
		final String[] chunks = accessToken.split("\\.");
		final var decoder = Base64.getUrlDecoder();
		return new String(decoder.decode(chunks[0]));
	}

	public String decodingJWTTokenPayload(String accessToken) {
		final String[] chunks = accessToken.split("\\.");
		final var decoder = Base64.getUrlDecoder();
		return new String(decoder.decode(chunks[1]));
	}

}
