package com.api.swing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.swing.business.ObjectsService;
import com.api.swing.model.Objects;

@RestController
@RequestMapping("/object")
public class ApiObjectController {

	@Autowired
	ObjectsService objectsService;

	@GetMapping("/all")
	public List<Objects> getArticles() {
		return objectsService.getObjects();
	}

	@PostMapping("/add")
	public Objects addArticle(@RequestBody Objects obj) {
		return objectsService.createObject(obj);
	}
}