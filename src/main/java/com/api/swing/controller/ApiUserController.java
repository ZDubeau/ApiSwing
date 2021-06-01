package com.api.swing.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.swing.business.UserService;
import com.api.swing.exceptions.UserNotFoundException;
import com.api.swing.jwt.JwtUtil;
import com.api.swing.model.JwtRequest;
import com.api.swing.model.JwtResponse;
import com.api.swing.model.User;

import lombok.extern.slf4j.Slf4j;

/**
 * @HTTP_is_the_Platform To wrap your repository with a web layer -here is for
 *                       check in POSTMAN, you must turn to Spring MVC. With
 *                       Spring Boot, there is little in infrastructure to code.
 *
 * @RestController indicates that the data returned by each method will be
 *                 written straight into the response body instead of rendering
 *                 a template.
 *
 * @CrossOrigin Cross-Origin Resource Sharing (CORS) is a security concept that
 *              allows restricting the resources implemented in web browsers. It
 *              prevents the JavaScript code producing or consuming the requests
 *              against different origin.
 * 
 * @UserRepository is injected by constructor into the controller.
 *
 *                 We have routes for each operation :
 *                 ( @GetMapping, @PostMapping, @PutMapping and @DeleteMapping,
 *                 corresponding to HTTP GET, POST, PUT, and DELETE calls).
 *                 (NOTE: It’s useful to read each method and understand what
 *                 they do.)
 * 
 * @author zahra DUBEAU
 */

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api")
@Validated
public class ApiUserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		log.info(token);

		final String userInfo = jwtTokenUtil.getUsernameFromToken(token);

		log.info(userInfo);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@PostMapping("/registration")
	public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception, DuplicateKeyException {
		if (userService.findByUsername(user.getUsername()).isEmpty()) {
			if (userService.findByMail(user.getMail()).isEmpty()) {
				return ResponseEntity.ok(userService.save(user));
			} else {
				log.error("DuplicateKeyException : " + user.getMail());
				throw new DuplicateKeyException("Email: [" + user.getMail() + "] already exists !");
			}
		} else {
			if (userService.findByMail(user.getMail()).isEmpty()) {
				log.error("DuplicateKeyException : " + user.getUsername());
				throw new DuplicateKeyException("Username: [" + user.getUsername() + "] already exists !");
			} else {
				log.error("DuplicateKeyException : " + user.getUsername() + "and" + user.getMail());
				throw new DuplicateKeyException(
						"Username: [" + user.getUsername() + "] and Email: [" + user.getMail() + "] already exists !");
			}
		}
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);

		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS : " + username + " - " + password, e);
		}
	}

	@GetMapping(value = "/users")
	public List<User> getUser() {

		return userService.getUser();
	}

	@GetMapping(value = "/user/{id}")
	public User getById(@PathVariable("id") @Min(1) int id) {

		return userService.findUserById(id)
				.orElseThrow(() -> new UserNotFoundException("User with ID [" + id + "] Not Found!"));
	}

	@DeleteMapping("/user/{id}")
	public String deleteUser(@PathVariable("id") @Min(1) int id) {
		User user = userService.findUserById(id)
				.orElseThrow(() -> new UserNotFoundException("User with ID [" + id + "] Not Found!"));
		userService.deleteById(user.getId());
		return "User with ID : " + id + " is deleted";
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody User result) {
		return ResponseEntity.ok(userService.update(id, result));
	}
}
