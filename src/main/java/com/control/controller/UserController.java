package com.control.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.model.dto.UserIdDTO;
import com.control.model.dto.UserInsertDTO;
import com.control.model.dto.UserPasswordDTO;
import com.control.model.dto.UserUpdateDTO;
import com.control.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<?> findAll(Pageable pageable) {
		log.info("Pagination: {}", pageable);

		final var entity = userService.findAll(pageable);
		return entity.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(entity);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> findById(@PathVariable("userId") String userId) {
		log.info("Parameter: userId={}", userId);

		final var entity = userService.findById(userId);
		return entity.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(entity);
	}

	@GetMapping("/findByName/{userName}")
	public ResponseEntity<?> findByName(@PathVariable("userName") String userName) {
		log.info("Parameter: userName={}", userName);

		final var entity = userService.findByName(userName);
		return entity == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(entity);
	}

	@GetMapping("/findByEmail/{userEmail}")
	public ResponseEntity<?> findByEmail(@PathVariable("userEmail") String userEmail) {
		log.info("Parameter: userEmail={}", userEmail);

		final var entity = userService.findByEmail(userEmail);
		return entity == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(entity);
	}

	@GetMapping("/findByNameContaining/{userName}")
	public ResponseEntity<?> findByNameContaining(@PathVariable("userName") String userName) {
		log.info("Parameter: userName={}", userName);

		final var entity = userService.findByNameContaining(userName);
		return entity.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(entity);
	}

	@GetMapping("/findByEmailContaining/{userEmail}")
	public ResponseEntity<?> findByEmailContaining(@PathVariable("userEmail") String userEmail) {
		log.info("Parameter: userName={}", userEmail);

		final var entity = userService.findByEmailContaining(userEmail);
		return entity.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(entity);
	}

	@GetMapping("/find/{find}")
	public ResponseEntity<?> find(@PathVariable("find") String find) {
		log.info("Parameter: find={}", find);

		final var entity = userService.find(find);

		return entity.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(entity);
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody UserInsertDTO userInsertDTO) {
		log.info("Parameter: object={}", userInsertDTO.toString());

		return ResponseEntity.ok(userService.save(userInsertDTO));
	}

	@PutMapping
	public ResponseEntity<?> save(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
		log.info("Parameter: object={}", userUpdateDTO);

		return ResponseEntity.ok().build();
	}

	@PutMapping("/password")
	public ResponseEntity<?> savePassword(@Valid @RequestBody UserPasswordDTO userPasswordUpdateDTO) {
		log.info("Parameter: object={}", userPasswordUpdateDTO);

		userService.savePassword(userPasswordUpdateDTO);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping()
	public ResponseEntity<?> delete(@Valid @RequestBody UserIdDTO userDTO) {
		log.info("Parameter: userId={}", userDTO.getUserId());

		userService.delete(userDTO);

		return ResponseEntity.noContent().build();
	}

}
