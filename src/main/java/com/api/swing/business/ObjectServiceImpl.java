package com.api.swing.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.swing.exceptions.RessourceNotFoundException;
import com.api.swing.model.ObjStatus;
import com.api.swing.model.Objects;
import com.api.swing.model.User;
import com.api.swing.repository.ObjectRepository;
import com.api.swing.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ObjectServiceImpl implements ObjectsService {

	@Autowired
	private ObjectRepository objectRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Objects> getObjects() {
		return (List<Objects>) objectRepository.findAll();
	}

	public Objects getObjById(int id) throws Exception {
		Optional<Objects> optionalObject = objectRepository.findById(id);
		if (!optionalObject.isPresent()) {
			return optionalObject.orElseThrow(() -> new Exception());
		}
		return objectRepository.findObjeById(optionalObject.get());
	}

	@Override
	public Optional<Object> findByStatus(ObjStatus objStatus) {
		return objectRepository.findByStatus(objStatus);
	}
	
	@Override
	public Optional<Object> findByTitle(String title) {
		return objectRepository.findByTitle(title);
	}
	
	@Override
	public Objects createObject(int id, Objects obj) {
		return userRepository.findById(id).map(u -> {
			obj.setUser(u);
			return objectRepository.save(obj);
		}).orElseThrow(() -> new RessourceNotFoundException("User with id: [" + id + "] not found"));
	}

	@Override
	public Objects updateObject(int id, Objects obj) {

		Optional<User> optionalUser = userRepository.findById(obj.getId());
		if (!optionalUser.isPresent()) {
			return objectRepository.save(obj);
		}

		Optional<Objects> optionalObj = objectRepository.findById(id);
		if (!optionalObj.isPresent()) {
			return objectRepository.save(obj);
		}
		obj.setUser(optionalUser.get());
		obj.setId(optionalObj.get().getId());
		return objectRepository.save(obj);
	}

//	@Override
//	public Objects deleteObject(int id, Objects obj) {
//
//		Optional<Objects> optionalObj = objectRepository.findById(id);
//		if (!optionalObj.isPresent()) {
//			return objectRepository.save(obj);
//		}
//		return objectRepository.removeById(optionalObj.get());
//	}
}
