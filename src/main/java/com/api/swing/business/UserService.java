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

	public List<User> getUser();

	public Optional<User> findUserById(int id);

	public Optional<User> findByMail(String mail);

	public Optional<User> findByUsername(String username);

	public User save(User newUser);

	public User update(int id, User updateUser);

	public void deleteById(int id);
}