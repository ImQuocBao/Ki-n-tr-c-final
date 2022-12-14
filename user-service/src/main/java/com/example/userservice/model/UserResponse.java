package com.example.userservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
	private Integer id;
	private String fName;
	private String lName;
	private String email;
	private String address;
}
