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
import com.control.model.dto.UserPasswordDTO;
import com.control.model.dto.UserPostDTO;
import com.control.model.dto.UserPutDTO;
import com.control.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	public Optional<User> findById(String userId) {
		return userRepository.findById(userId);
	}

	public User findByName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public User findByEmail(String userEmail) {
		return userRepository.findByUserEmail(userEmail);
	}

	public List<User> findByNameContaining(String userName) {
		return userRepository.findByUserNameIgnoreCaseContaining(userName);
	}

	public List<User> findByEmailContaining(String userName) {
		return userRepository.findByUserEmailIgnoreCaseContaining(userName);
	}

	public List<User> find(String find) {
		return userRepository.findByUserNameIgnoreCaseContainingOrUserEmailIgnoreCaseContaining(find, find);
	}

	@Transactional(rollbackFor = Exception.class)
	public String save(UserPostDTO userInsertDTO) {
		final var entity = new User();
		BeanUtils.copyProperties(userInsertDTO, entity);
		entity.setUserPassword(new BCryptPasswordEncoder().encode(entity.getUserPassword()));
		userRepository.save(entity);
		return entity.getUserId();
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(UserPutDTO userUpdateDTO) {
		final var entity = new User();
		BeanUtils.copyProperties(userUpdateDTO, entity);
		entity.setUserPassword(userRepository.findByUserPassword(userUpdateDTO.getUserId()));
		userRepository.save(entity);
	}

	@Transactional(rollbackFor = Exception.class)
	public void savePassword(UserPasswordDTO userPasswordUpdateDTO) {
		final var entity = new User();
		BeanUtils.copyProperties(userPasswordUpdateDTO, entity);
		entity.setUserPassword(new BCryptPasswordEncoder().encode(entity.getUserPassword()));
		userRepository.updateUserPassword(entity.getUserId(), entity.getPassword());
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(UserIdDTO userIdDTO) {
		userRepository.deleteById(userIdDTO.getUserId());
	}

}
