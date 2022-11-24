package com.example.userservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.userservice.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	@Query("select u from User u where u.email=?1 and u.loginPassword=?2")
	User findUserByEmailAndPassword(String email, String password);
	@Query("select u from User u where u.email=?1")
	User findUserByEmail(String email);

}
