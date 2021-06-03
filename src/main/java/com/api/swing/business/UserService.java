package com.api.swing.business;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.api.swing.model.User;

/**
 * Here we define an interface that contains the method signatures for Users
 * service. We need to be able to find the user by e-mail address or reset
 * token. Also, we need to be able to update the user's record.
 * 
 * @author Zahra DUBEAU
 */

public interface UserService extends UserDetailsService {

	List<User> getUser();

	Optional<User> findById(int id);

	Optional<User> findByMail(String mail);

	Optional<User> findByUsername(String username);

	User save(User newUser);

	User update(int id, User updateUser);

	void deleteById(int id);
	
	//Optional<User> findUserWithObjectsById(int id);
}