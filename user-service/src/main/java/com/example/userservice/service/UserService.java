package com.example.userservice.service;

import org.springframework.stereotype.Service;

import com.example.userservice.model.User;
import com.example.userservice.model.UserDto;
import com.example.userservice.model.UserResponse;
import com.example.userservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repository;

	public User createdUser(UserDto dto) {
		User user = new User();
		user.setFName(dto.getFName());
		user.setLName(dto.getLName());
		user.setAddress(dto.getAddress());
		user.setEmail(dto.getEmail());
		user.setLoginPassword(dto.getLoginPassword());
		return repository.save(user);
	}

	public UserResponse findById(Integer id) {
		User user = repository.findById(id).get();
		return UserResponse.builder().address(user.getEmail()).email(user.getEmail()).fName(user.getFName())
				.lName(user.getLName()).id(user.getId()).build();
	}

}
