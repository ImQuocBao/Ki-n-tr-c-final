package com.example.userservice.model;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String fName;
	private String lName;
	private String email;
	private String address;
	private String loginPassword;
	private String role;
}
