package com.api.swing.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.swing.business.ObjectsService;
import com.api.swing.exceptions.RessourceNotFoundException;
import com.api.swing.model.Objects;
import com.api.swing.repository.ObjectRepository;
import com.api.swing.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/")
@Validated
public class ApiObjectController {

	@Autowired
	ObjectsService objectService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ObjectRepository objectRepository;

	@GetMapping("/objects/all")
	public List<Objects> getAllObjects() {
		if (objectService.getObjects().isEmpty()) {
			throw new RessourceNotFoundException("Object list is empty.");
		}
		return objectService.getObjects();
	}

	@PostMapping("/user/{user_id}/addObj")
	public Objects addNewObject(@PathVariable(value = "user_id") int id, @Valid @RequestBody Objects obj) {

//		if (objectService.findByTitle(obj.getTitle()).isPresent()) {
//			throw new DuplicateKeyException("Title: '" + obj.getTitle() + "' already exists !");
//		}
		
		// NOT NULL VALE
//		if (objectService.findByStatus(obj.getStatus()).isEmpty()) {
//			throw new NullPointerException("'Status' can't be NULL !");
//		}
//		if (objectService.findByTitle(obj.getTitle()).isEmpty()) {
//			throw new NullPointerException("'Title' can't be NULL !");
//		}
		
		return objectService.createObject(id, obj);
	}
}