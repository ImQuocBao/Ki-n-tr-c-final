package com.example.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.example.userservice.model.User;
import com.example.userservice.model.UserDto;
import com.example.userservice.model.UserResponse;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

	private final BCryptPasswordEncoder encoder;
	private final UserService service;
	private final UserRepository repository;

	@GetMapping("/users")
	public ResponseEntity<?> getAllUser() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/user")
	public ResponseEntity<?> getUserById(
			@RequestParam(value = "user_id", required = false) Integer id,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "password", required = false) String password) {
		if (id != null) {
			return ResponseEntity.ok(service.findById(id));
		} else if (email != null && password != null) {
			User user = repository.findUserByEmail(email);
			encoder.matches(password, user.getLoginPassword());

			return ResponseEntity.ok(UserResponse.builder()
					.address(user.getEmail())
					.email(user.getEmail())
					.fName(user.getFName())
					.lName(user.getLName())
					.id(user.getId())
					.build());
		}
		return null;
	}

	@PostMapping("/user")
	public ResponseEntity<?> createUser(@RequestBody UserDto dto) {
		return ResponseEntity.ok(service.createdUser(dto));
	}
	
	@PostMapping("/user/{username}")
	public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username){
		return ResponseEntity.ok(repository.findUserByEmail(username));
	}
}
