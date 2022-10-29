package com.control.controller;

import javax.validation.Valid;

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

import com.control.model.dto.UserInsertDTO;
import com.control.model.dto.UserPasswordUpdateDTO;
import com.control.model.dto.UserUpdateDTO;
import com.control.service.UserService;

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

		final var user = userService.findAll(pageable);
		return user.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				: ResponseEntity.status(HttpStatus.FOUND).body(user);
	}

	@GetMapping("/{user_id}")
	public ResponseEntity<?> findById(@PathVariable("user_id") String user_id) {
		log.info("Parameter: userId={}", user_id);

		final var user = userService.findById(user_id);
		return user == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				: ResponseEntity.status(HttpStatus.FOUND).body(user);
	}

	@GetMapping("/findByName/{user_name}")
	public ResponseEntity<?> findByName(@PathVariable("user_name") String user_name) {
		log.info("Parameter: userName={}", user_name);

		final var user = userService.findByName(user_name);
		return user == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				: ResponseEntity.status(HttpStatus.FOUND).body(user);
	}

	@GetMapping("/findByNameContaining/{user_name}")
	public ResponseEntity<?> findByNameIgnoreCaseContaining(@PathVariable("user_name") String user_name) {
		log.info("Parameter: userName={}", user_name);

		final var user = userService.findByNameContaining(user_name);
		return user.size() == 0 ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				: ResponseEntity.status(HttpStatus.FOUND).body(user);
	}

	@GetMapping("/findByEmail/{user_email}")
	public ResponseEntity<?> findByEmail(@PathVariable("user_email") String user_email) {
		log.info("Parameter: userEmail={}", user_email);

		final var user = userService.findByEmail(user_email);
		return user == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				: ResponseEntity.status(HttpStatus.FOUND).body(user);
	}

	@GetMapping("/find/{find}")
	public ResponseEntity<?> findByUserNameIgnoreCaseContainingOrUserEmailIgnoreCaseContaining(
			@PathVariable("find") String find) {
		log.info("Parameter: find={}", find);

		final var user = userService.find(find);
		return user.size() == 0 ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				: ResponseEntity.status(HttpStatus.FOUND).body(user);
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody UserInsertDTO userInsertDTO) {
		log.info("Parameter: object {}", userInsertDTO.toString());

		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userInsertDTO));
	}

	@PutMapping
	public ResponseEntity<?> save(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
		log.info("Parameter: object {}", userUpdateDTO);

		userService.save(userUpdateDTO);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PutMapping("/password")
	public ResponseEntity<?> savePassword(@Valid @RequestBody UserPasswordUpdateDTO userPasswordUpdateDTO) {
		log.info("Parameter: object {}", userPasswordUpdateDTO);

		userService.savePassword(userPasswordUpdateDTO);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/{user_id}")
	public ResponseEntity<?> delete(@PathVariable("user_id") String user_id) {
		log.info("Parameter: userId={}", user_id);

		userService.delete(user_id);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
