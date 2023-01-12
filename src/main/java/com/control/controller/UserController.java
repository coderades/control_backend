package com.control.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.model.dto.UserDTO;
import com.control.model.dto.UserInsertDTO;
import com.control.model.dto.UserPasswordUpdateDTO;
import com.control.model.dto.UserUpdateDTO;
import com.control.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<?> findAll(Pageable pageable) {
		log.info("Pagination: {}", pageable);

		final var entity = userService.findAll(pageable);
		return !entity.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body(entity)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> findById(@PathVariable("userId") String userId) {
		log.info("Parameter: userId={}", userId);

		final var entity = userService.findById(userId);
		return ResponseEntity.status(HttpStatus.FOUND).body(entity);
	}

	@GetMapping("/findByName/{userName}")
	public ResponseEntity<?> findByName(@PathVariable("user_name") String userName) {
		log.info("Parameter: userName={}", userName);

		final var entity = userService.findByName(userName);
		return ResponseEntity.status(HttpStatus.FOUND).body(entity);
	}

	@GetMapping("/findByEmail/{userEmail}")
	public ResponseEntity<?> findByEmail(@PathVariable("user_email") String userEmail) {
		log.info("Parameter: userEmail={}", userEmail);

		final var entity = userService.findByEmail(userEmail);
		return  ResponseEntity.status(HttpStatus.FOUND).body(entity);
	}

	@GetMapping("/findByNameContaining/{userName}")
	public ResponseEntity<?> findByNameIgnoreCaseContaining(@PathVariable("userName") String userName) {
		log.info("Parameter: userName={}", userName);

		final var entity = userService.findByNameContaining(userName);
		return ResponseEntity.status(HttpStatus.FOUND).body(entity);
	}

	@GetMapping("/find/{find}")
	public ResponseEntity<?> findByUserNameIgnoreCaseContainingOrUserEmailIgnoreCaseContaining(
			@PathVariable("find") String find) {
		log.info("Parameter: find={}", find);

		final var entity = userService.find(find);
		return ResponseEntity.status(HttpStatus.FOUND).body(entity);
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody UserInsertDTO userInsertDTO) {
		log.info("Parameter: object={}", userInsertDTO.toString());

		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userInsertDTO));
	}

	@PutMapping
	public ResponseEntity<?> save(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
		log.info("Parameter: object={}", userUpdateDTO);
		userService.save(userUpdateDTO);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PutMapping("/password")
	public ResponseEntity<?> savePassword(@Valid @RequestBody UserPasswordUpdateDTO userPasswordUpdateDTO) {
		log.info("Parameter: object={}", userPasswordUpdateDTO);
		userService.savePassword(userPasswordUpdateDTO);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping()
	public ResponseEntity<?> delete(@Valid @RequestBody UserDTO userDTO) {
		log.info("Parameter: userId={}", userDTO.getUserId());
		userService.delete(userDTO);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
