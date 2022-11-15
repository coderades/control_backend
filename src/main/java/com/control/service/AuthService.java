package com.control.service;

import java.util.Optional;

import org.slf4j.MDC;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;

import com.control.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class AuthService implements UserDetailsService {

	private final UserRepository userRepository;

	public AuthService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		final var entity = userRepository.findByUserName(username);

		MDC.put("sessionId", RequestContextHolder.currentRequestAttributes().getSessionId());

		if (entity == null) {
			log.warn("Login: Fail from userName {}", username);
		} else {
			log.info("Login: OK from userName {} linked to userId {}", username, entity.getUserId());
		}

		return Optional
				.ofNullable(new User(entity.getUsername(), entity.getPassword(), entity.isEnabled(),
						entity.isAccountNonExpired(), entity.isAccountNonExpired(), entity.isAccountNonLocked(),
						entity.getAuthorities()))
				.orElseThrow(() -> new UsernameNotFoundException(
						new StringBuilder().append("Login: fail from username ").append(username).toString()));
	}

}
