package com.control.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.backend.model.User;
import com.control.backend.model.dto.UserIdDTO;
import com.control.backend.model.dto.UserInsertDTO;
import com.control.backend.model.dto.UserUpdateDTO;
import com.control.backend.model.dto.UserUpdatePasswordDTO;
import com.control.backend.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Page<User> findAll(Pageable pageable) {
		final var user = userRepository.findAll(pageable);
		return user;
	}

	public Optional<User> findById(String userId) {
		final var user = userRepository.findById(userId);
		return user;
	}

	public User findByName(String userName) {
		final var user = userRepository.findByUserName(userName);
		return user;
	}

	public User findByEmail(String userEmail) {
		final var user = userRepository.findByUserEmail(userEmail);
		return user;
	}

	public List<User> findByNameContaining(String userName) {
		final var user = userRepository.findByUserNameIgnoreCaseContaining(userName);
		return user;
	}

	public List<User> findByEmailContaining(String userName) {
		final var user = userRepository.findByUserEmailIgnoreCaseContaining(userName);
		return user;
	}

	public List<User> find(String find) {
		final var user = userRepository.findByUserNameIgnoreCaseContainingOrUserEmailIgnoreCaseContaining(find, find);
		return user;
	}

	@Transactional(rollbackFor = Exception.class)
	public UserIdDTO save(UserInsertDTO userInsertDTO) {
		final var user = new User();
		BeanUtils.copyProperties(userInsertDTO, user);
		return new UserIdDTO(userRepository.save(user).getUserId());
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(UserUpdateDTO userUpdateDTO) {
		final var user = new User();
		BeanUtils.copyProperties(userUpdateDTO, user);
		userRepository.save(user);
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(UserUpdatePasswordDTO userUpdatePasswordDTO) {
		userRepository.saveUserPassword(userUpdatePasswordDTO.userId(), userUpdatePasswordDTO.userPassword());
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(String userId) {
		userRepository.deleteById(userId);
	}

}
