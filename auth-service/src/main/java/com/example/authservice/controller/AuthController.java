package com.example.authservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.authservice.config.JwtTokenProvider;
import com.example.authservice.model.UserDto;
import com.example.authservice.model.request.LoginRequest;
import com.example.authservice.model.request.UserRequest;
import com.example.authservice.model.response.LoginReponse;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final RestTemplate restTemplate;
	private final BCryptPasswordEncoder passwordEncoder;
	private final JwtTokenProvider tokenProvider;

	@PostMapping("/register")
	public ResponseEntity<?> postUser(@RequestBody UserRequest user) {
		UserDto dto = new UserDto();
		dto.setAddress(user.getAddress());
		dto.setEmail(user.getEmail());
		dto.setFName(user.getFName());
		dto.setLName(user.getLName());
		dto.setLoginPassword(passwordEncoder.encode(user.getLoginPassword()));
		UserDto userDtoSave = restTemplate.postForObject("http://localhost:9004/api/v1/user", dto, UserDto.class);
		return ResponseEntity.ok(userDtoSave);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
		UserDto dto = restTemplate.getForObject("http://localhost:9004/api/v1/user?email=" + loginRequest.getUserName()
				+ "&password=" + loginRequest.getPassword(), UserDto.class);
		String jwt = tokenProvider.generateToken(dto);
		LoginReponse loginReponse = new LoginReponse();
		loginReponse.setAccess_token(jwt);
		loginReponse.setUser_name(dto.getEmail());
		return new ResponseEntity<>(loginReponse, HttpStatus.OK);
	}
}
