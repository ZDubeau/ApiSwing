package com.api.swing.business;

import java.util.List;

import com.api.swing.model.Objects;

public interface ObjectsService {

	public List<Objects> getObjects();

	public Objects createObject(Objects obj);

	public Objects updateObject(Objects obj);
}