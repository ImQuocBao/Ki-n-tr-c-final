package com.example.authservice.model.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserRequest {

	private String fName;
	private String lName;
	private String email;
	private String address;
	private String loginPassword;
	private String confirmPassword;
}
