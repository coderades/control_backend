package com.control.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.backend.model.dto.UserIdDTO;
import com.control.backend.model.dto.UserInsertDTO;
import com.control.backend.model.dto.UserUpdateDTO;
import com.control.backend.model.dto.UserUpdatePasswordDTO;
import com.control.backend.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<?> findAll(
			@SortDefault(sort = "userName") @PageableDefault(size = 100) final Pageable pageable) {
		return ResponseEntity.ok(userService.findAll(pageable));
	}

	@GetMapping("/find/{find}")
	public ResponseEntity<?> find(@PathVariable("find") String find) {
		return ResponseEntity.ok(userService.find(find));
	}
	
	@GetMapping("/findByEmail/{userEmail}")
	public ResponseEntity<?> findByEmail(@PathVariable("userEmail") String userEmail) {
		return ResponseEntity.ok(userService.findByEmail(userEmail));
	}
	
	@GetMapping("/findById/{userId}")
	public ResponseEntity<?> findById(@PathVariable("userId") String userId) {
		return ResponseEntity.ok(userService.findById(userId));
	}

	@GetMapping("/findByName/{userName}")
	public ResponseEntity<?> findByName(@PathVariable("userName") String userName) {
		return ResponseEntity.ok(userService.findByName(userName));
	}
	
	@GetMapping("/findByNameContaining/{userName}")
	public ResponseEntity<?> findByNameContaining(@PathVariable("userName") String userName) {
		return ResponseEntity.ok(userService.findByNameContaining(userName));
	}
	
	@GetMapping("/findByEmailContaining/{userEmail}")
	public ResponseEntity<?> findByEmailContaining(@PathVariable("userEmail") String userEmail) {
		return ResponseEntity.ok(userService.findByEmailContaining(userEmail));
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody UserInsertDTO userInsertDTO) {
		return ResponseEntity.created(null).body(userService.save(userInsertDTO));
	}

	@PutMapping
	public ResponseEntity<?> save(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
		userService.save(userUpdateDTO);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/password")
	public ResponseEntity<?> savePassword(@Valid @RequestBody UserUpdatePasswordDTO userUpdatePasswordDTO) {
		userService.save(userUpdatePasswordDTO);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping
	public ResponseEntity<?> delete(@Valid @RequestBody UserIdDTO userIdDTO) {
		userService.delete(userIdDTO);
		return ResponseEntity.noContent().build();
	}

}
