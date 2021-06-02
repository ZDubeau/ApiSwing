package com.api.swing.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.swing.model.User;

/**
 * Spring Data JPA repositories are interfaces with methods supporting -
 * creating, - reading, - updating, and - deleting records against a back end
 * data store. Some repositories also support data paging, and sorting, where
 * appropriate. Spring Data synthesizes implementations based on conventions
 * found in the naming of the methods in the interface.
 *
 * Spring makes accessing data easy. By simply declaring the following
 * "UserRepository" interface we automatically will be able to :
 *
 * Create new Users Update existing ones Delete Users Find Users (one, all, or
 * search by simple or complex properties)
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	Optional<User> findByMail(String mail); // SELECT * FROM user WHERE mail = ?
	Optional<User> findByUsername(String username); // SELECT * FROM user WHERE username = ?
}
