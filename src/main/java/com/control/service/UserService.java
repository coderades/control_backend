package com.control.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.model.User;
import com.control.model.dto.UserIdDTO;
import com.control.model.dto.UserInsertDTO;
import com.control.model.dto.UserPasswordUpdateDTO;
import com.control.model.dto.UserUpdateDTO;
import com.control.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Boolean existsById(String userId) {
		final var entity = userRepository.existsById(userId);

		log.info("Return: Object={}", entity);

		return entity;
	}

	public Boolean existsByUserName(String userName) {
		final var entity = userRepository.existsByUserName(userName);

		log.info("Return: Object={}", entity);

		return entity;
	}

	public Boolean existsByUserEmail(String userEmail) {
		final var entity = userRepository.existsByUserEmail(userEmail);

		log.info("Return: Object={}", entity);

		return entity;
	}

	public Boolean existsByUserIdIsNotAndUserName(String userId, String userName) {
		final var entity = userRepository.existsByUserIdIsNotAndUserName(userId, userName);

		log.info("Return: Object={}", entity);

		return entity;
	}

	public Boolean existsByUserIdIsNotAndUserEmail(String userId, String userEmail) {
		final var entity = userRepository.existsByUserIdIsNotAndUserEmail(userId, userEmail);

		log.info("Return: Object={}", entity);

		return entity;
	}

	public Page<User> findAll(Pageable pageable) {
		final var entity = userRepository.findAll(pageable);

		log.info("Return: Elements={}, Object={}", entity.getSize(), entity);

		return entity;
	}

	public Optional<User> findById(String userId) {
		final var entity = userRepository.findById(userId);

		log.info("Return: Object={}", entity);

		return entity;
	}

	public User findByName(String userName) {
		final var entity = userRepository.findByUserName(userName);

		log.info("Return: Object={}", entity);

		return entity;
	}

	public User findByEmail(String userEmail) {
		final var entity = userRepository.findByUserEmail(userEmail);

		log.info("Return: Object={}", entity);

		return entity;
	}

	public List<User> findByNameContaining(String userName) {
		final var entity = userRepository.findByUserNameIgnoreCaseContaining(userName);

		log.info("Return: Elements={}, Object={}", entity.size(), entity);

		return entity;
	}

	public List<User> findByEmailContaining(String userName) {
		final var entity = userRepository.findByUserEmailIgnoreCaseContaining(userName);

		log.info("Return: Elements={}, Object={}", entity.size(), entity);

		return entity;
	}

	public List<User> find(String find) {
		final var entity = userRepository.findByUserNameIgnoreCaseContainingOrUserEmailIgnoreCaseContaining(find, find);

		log.info("Return: Elements={}, Object={}", entity.size(), entity);

		return entity;
	}

	@Transactional(rollbackFor = Exception.class)
	public String save(UserInsertDTO userInsertDTO) {
		final var entity = new User();

		BeanUtils.copyProperties(userInsertDTO, entity);
		entity.setUserPassword(new BCryptPasswordEncoder().encode(entity.getUserPassword()));

		userRepository.save(entity);
		log.info("Return: userId={}", entity.getUserId());

		return entity.getUserId();
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(UserUpdateDTO userUpdateDTO) {
		final var entity = new User();

		BeanUtils.copyProperties(userUpdateDTO, entity);
		entity.setUserPassword(userRepository.findByUserPassword(userUpdateDTO.getUserId()));

		userRepository.save(entity);
		log.info("Status: OK");
	}

	@Transactional(rollbackFor = Exception.class)
	public void savePassword(UserPasswordUpdateDTO userPasswordUpdateDTO) {
		final var entity = new User();

		BeanUtils.copyProperties(userPasswordUpdateDTO, entity);
		entity.setUserPassword(new BCryptPasswordEncoder().encode(entity.getUserPassword()));

		userRepository.updateUserPassword(entity.getUserId(), entity.getPassword());
		log.info("Status: OK");
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(UserIdDTO userIdDTO) {
		userRepository.deleteById(userIdDTO.getUserId());
		log.info("Status: OK");
	}
}
