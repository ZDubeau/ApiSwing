package com.api.swing.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.swing.model.Objects;
import com.api.swing.repository.ObjectRepository;


@Component
public class ObjectServiceImpl implements ObjectsService {

	@Autowired
	private ObjectRepository objectRepository;

	@Override
	public List<Objects> getObjects() {
		return (List<Objects>) objectRepository.findAll();
	}

	@Override
	public Objects createObject(Objects obj) {
		return objectRepository.save(obj);
	}

	@Override
	public Objects updateObject(Objects obj) {
		return objectRepository.save(obj);
	}
}
