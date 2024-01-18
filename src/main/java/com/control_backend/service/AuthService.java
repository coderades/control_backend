package com.control_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.control_backend.repository.UserRepository;

@Service
public class AuthService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		return userRepository.findByUserName(username);
	}

	public void saveLoggedOn(String userId) {
			userRepository.saveLoggedOn(userId);
	}

}
