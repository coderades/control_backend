package com.control.backend.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.backend.auth.model.User;
import com.control.backend.auth.model.dto.UserDTO;
import com.control.backend.auth.model.dto.UserPasswordDTO;
import com.control.backend.auth.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	public Optional<User> findById(Long userId) {
		return userRepository.findById(userId);
	}

	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public List<User> findByUserNameIgnoreCaseContaining(String userName) {
		return userRepository.findByUserNameIgnoreCaseContaining(userName);
	}

	@Transactional(rollbackFor = Exception.class)
	public Long save(UserDTO userDTO) {
		final var user = new User();
		BeanUtils.copyProperties(userDTO, user);
		return userRepository.save(user).getUserId();
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(Long userId, UserDTO userDTO) {
		final var user = new User();
		BeanUtils.copyProperties(userDTO, user);
		user.setUserId(userId);
		userRepository.save(user);
	}

	@Transactional(rollbackFor = Exception.class)
	public void saveUserPassword(Long userId, UserPasswordDTO userPasswordDTO) {
		userRepository.saveUserPassword(userId, userPasswordDTO.userPassword());
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(Long userId) {
		userRepository.deleteById(userId);
	}

}
