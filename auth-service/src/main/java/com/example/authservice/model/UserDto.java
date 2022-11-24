package com.example.authservice.model;

import lombok.Data;

@Data
public class UserDto {
	private String fName;
	private String lName;
	private String email;
	private String address;
	private String loginPassword;
}
