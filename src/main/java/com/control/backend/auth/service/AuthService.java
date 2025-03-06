package com.control.backend.auth.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.control.backend.auth.exception.runtime.JWTCreationRuntimeException;
import com.control.backend.auth.exception.runtime.JWTVerificationRuntimeException;
import com.control.backend.auth.model.User;
import com.control.backend.auth.model.dto.AuthDTO;
import com.control.backend.auth.model.dto.ResponseTokenDTO;
import com.control.backend.auth.repository.UserRepository;
import com.control.backend.auth.util.StringUtil;

@Service
public class AuthService implements UserDetailsService {

	@Value("${auth.jwt.access-token.secret}")
	private String accessTokenSecret;

	@Value("${auth.jwt.access-token.expiration}")
	private Integer accessTokenExpiration;

	@Value("${auth.jwt.refresh-token.expiration}")
	private Integer refreshTokenExpiration;

	@Value("${auth.jwt.id-token.expiration}")
	private Integer idTokenExpiration;

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		return userRepository.findByUserName(username);
	}

	public ResponseTokenDTO createToken(AuthDTO authDTO) {
		final var user = userRepository.findByUserName(authDTO.userName());
		final var tokenResponseDTO = ResponseTokenDTO.builder().accessToken(generateToken(user, accessTokenExpiration))
				.refreshToken(generateToken(user, refreshTokenExpiration))
				.idToken(generateIdToken(user, idTokenExpiration)).tokenType("bearer").build();
		userRepository.saveLoggedAt(user.getUserId());
		return tokenResponseDTO;
	}

	public ResponseTokenDTO createRefreshToken(String refreshToken) {
		final var user = userRepository.findByUserName(validationToken(refreshToken));
		SecurityContextHolder.getContext()
				.setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
		return ResponseTokenDTO.builder().accessToken(generateToken(user, accessTokenExpiration))
				.refreshToken(generateToken(user, refreshTokenExpiration)).build();
	}

	public String validationToken(String token) {
		try {
			return JWT.require(Algorithm.HMAC512(accessTokenSecret)).withIssuer("auth-api").build().verify(token)
					.getSubject();
		} catch (JWTVerificationException e) {
			throw new JWTVerificationRuntimeException(e);
		}
	}

	private String generateToken(User user, Integer addExpiration) {
		try {
			return JWT.create().withIssuer("auth-api").withSubject(user.getUsername())
					.withAudience("http://localhost:8080").withIssuedAt(expirationTime(0))
					.withExpiresAt(expirationTime(addExpiration)).withClaim("nbf", expirationTime(0))
					.withJWTId(StringUtil.stringRandomUtil(32)).withClaim("uid", user.getUserId())
					.sign(Algorithm.HMAC512(accessTokenSecret));
		} catch (JWTCreationException e) {
			throw new JWTCreationRuntimeException(e);
		}
	}

	private String generateIdToken(User user, Integer addExpiration) {
		try {
			return JWT.create().withExpiresAt(expirationTime(addExpiration))
					.withClaim("userExternalId", user.getUserPublicId()).withClaim("userName", user.getUsername())
					.withClaim("userEmail", user.getUserEmail()).sign(Algorithm.HMAC512(accessTokenSecret));
		} catch (JWTCreationException e) {
			throw new JWTCreationRuntimeException(e);
		}
	}

	private Instant expirationTime(Integer addExpiration) {
		return LocalDateTime.now().plusHours(addExpiration).toInstant(ZoneOffset.of("-03:00"));
	}

}
