package com.control.backend.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.backend.auth.model.User;
import com.control.backend.auth.model.dto.UserDTO;
import com.control.backend.auth.model.dto.UserPasswordDTO;
import com.control.backend.auth.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<Page<User>> findAll(
			@SortDefault(sort = "userId") @PageableDefault(size = 20) final Pageable pageable) {
		return ResponseEntity.ok(userService.findAll(pageable));
	}

	@GetMapping("/findbyid/{userId}")
	public ResponseEntity<?> findById(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.findById(userId));
	}

	@GetMapping("/findbyname/{userName}")
	public ResponseEntity<?> findByName(@PathVariable String userName) {
		return ResponseEntity.ok(userService.findByName(userName));
	}

	@GetMapping("/findbynamecontaining/{userName}")
	public ResponseEntity<?> findByNameContaining(@PathVariable String userName) {
		return ResponseEntity.ok(userService.findByNameContaining(userName));
	}

	@GetMapping("/admin")
	private String getAdmin() {
		return "permissão de administrador";
	}

	@GetMapping("/user")
	private String getUser() {
		return "permissão de usuário";
	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody UserDTO userDTO) {
		final var jsonObject = new JSONObject();
		try {
			jsonObject.put("userId", userService.save(userDTO));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ResponseEntity.created(null).body(jsonObject.toString());
	}

	@PutMapping("/{userId}")
	public ResponseEntity<?> save(@PathVariable Long userId, @Valid @RequestBody UserDTO userDTO) {
		userService.save(userId, userDTO);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/password/{userId}")
	public ResponseEntity<?> savePassword(@PathVariable Long userId,
			@Valid @RequestBody UserPasswordDTO userPasswordDTO) {
		userService.saveUserPassword(userId, userPasswordDTO);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<?> delete(@PathVariable Long userId) {
		userService.delete(userId);
		return ResponseEntity.noContent().build();
	}

}
