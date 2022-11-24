package com.example.authservice.model.response;

import lombok.Data;

@Data
public class LoginReponse {
	String access_token;
	String user_name;
}
