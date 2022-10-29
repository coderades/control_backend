package com.control.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.model.User;
import com.control.model.dto.UserInsertDTO;
import com.control.model.dto.UserPasswordUpdateDTO;
import com.control.model.dto.UserUpdateDTO;
import com.control.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Page<?> findAll(Pageable pageable) {
		final var user = userRepository.findAll(pageable);

		log.info("Elements: {}, Object: {}", user.getSize(), user.getContent());

		return user;
	}

	public User findById(String userId) {
		final var user = userRepository.findById(userId);

		log.info("Object: {}", user);

		return user.isPresent() ? user.get() : null;
	}

	public User findByName(String userName) {
		final var user = userRepository.findByUserName(userName);

		log.info("Object: {}", user);

		return user == null ? null : user;
	}

	public List<?> findByNameContaining(String userName) {
		final var user = userRepository.findByUserNameIgnoreCaseContaining(userName);

		log.info("Elements: {}, Object: {}", user.size(), user);

		return user == null ? null : user;
	}

	public User findByEmail(String userEmail) {
		final var user = userRepository.findByUserEmail(userEmail);

		log.info("Object: {}", user);

		return user.isPresent() ? user.get() : null;
	}

	public List<?> find(String find) {
		final var user = userRepository.findByUserNameIgnoreCaseContainingOrUserEmailIgnoreCaseContaining(find, find);

		log.info("Elements: {}, Object: {}", user.size(), user);

		return user == null ? null : user;
	}

	@Transactional(rollbackFor = Exception.class)
	public String save(UserInsertDTO userInsertDTO) {
		final var user = new User();

		BeanUtils.copyProperties(userInsertDTO, user);
		user.setUserPassword(new BCryptPasswordEncoder().encode(user.getUserPassword()));

		userRepository.save(user);
		log.info("Return: userId={}", user.getUserId());

		return user.getUserId();
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(UserUpdateDTO userUpdateDTO) {
		final var user = new User();

		BeanUtils.copyProperties(userUpdateDTO, user);
		user.setUserPassword(userRepository.findByUserPassword(userUpdateDTO.getUserId()));

		userRepository.save(user);
		log.info("Status: OK");
	}

	@Transactional(rollbackFor = Exception.class)
	public void savePassword(UserPasswordUpdateDTO userPasswordUpdateDTO) {
		final var user = new User();

		BeanUtils.copyProperties(userPasswordUpdateDTO, user);
		user.setUserPassword(new BCryptPasswordEncoder().encode(user.getUserPassword()));

		userRepository.saveUserPassword(user.getUserId(), user.getPassword());
		log.info("Status: OK");
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(String userId) {
		userRepository.deleteById(userId);
		log.info("Status: OK");
	}
}
