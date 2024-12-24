package com.control.backend.auth.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.control.backend.auth.repository.UserRepository;
import com.control.backend.auth.service.AuthService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private AuthService autenticacaoService;

	@Autowired
	private UserRepository usuarioRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final var tokenHeader = extractTokeHeader(request);

		if (tokenHeader != null) {
			final var tokenValidation = autenticacaoService.validationToken(tokenHeader);
			final var user = usuarioRepository.findByUserName(tokenValidation);
			SecurityContextHolder.getContext()
					.setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
		}

		filterChain.doFilter(request, response);
	}

	public String extractTokeHeader(HttpServletRequest request) {
		final var authHeader = request.getHeader("Authorization");

		if (authHeader == null) {
			return null;
		}

		if (!"Bearer".equals(authHeader.split(" ")[0])) {
			return null;
		}

		return authHeader.split(" ")[1];
	}

}
