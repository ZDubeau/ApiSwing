package com.api.swing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.swing.model.ObjStatus;
import com.api.swing.model.Objects;

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
public interface ObjectRepository extends JpaRepository<Objects, Integer> {

	Optional<Objects> findByTitle(String title);
	
	Optional<Objects> findByStatus(ObjStatus objStatus);
}