package com.api.swing.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.api.swing.model.User;
import com.api.swing.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<User> getUser() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public Optional<User> findById(final int id) {
		
		return userRepository.findById(id);
		
	}
	@Override
	public User get(final Integer id) {
        return userRepository.findById(id).get();
               // .map(user -> (user, new User()))
               // .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

	@Override
	public Optional<User> findByMail(String mail) {
		return userRepository.findByMail(mail);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User save(User newUser) {
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		return userRepository.save(newUser);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> user = userRepository.findByMail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Nom d'utilisateur incorrect.");
		}
		List<GrantedAuthority> role = new ArrayList<>();
		return new org.springframework.security.core.userdetails.User(user.get().getMail(), user.get().getPassword(),
				role);
	}

	public User update(int id, User updateUser) {

		User update = findById(id)
				.orElseThrow(() -> new EntityNotFoundException(User.class + "id : " + updateUser));

		update.setPassword(passwordEncoder.encode(updateUser.getPassword()));
		update.setUsername(updateUser.getUsername());
		update.setFirstname(updateUser.getFirstname());
		update.setLastname(updateUser.getLastname());
		update.setMail(updateUser.getMail());

		log.info("In update method userServiceImpl : " + userRepository.save(update));
		return userRepository.save(update);
	}

	@Override
	public void deleteById(final int id) {
		userRepository.deleteById(id);
	}

	// @Transactional
	// public User addObjectToUser(int userId, int objId) {
	// Optional<User> user = findUserById(userId);
	// Optional<Objects> object = objectService.getObjectById(objId);
	// user.addObject(object);
	// return user;
	// }
	//
	// @Transactional
	// public User removeObjectToUser(int userId, int objId) {
	// Optional<User> user = findUserById(userId);
	// Optional<Objects> object = objectService.getObjectById(objId);
	// user.removeObject(object);
	// return user;
	// }
}
