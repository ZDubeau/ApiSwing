package com.api.swing.business;

import java.util.List;
import java.util.Optional;

import com.api.swing.model.ObjStatus;
import com.api.swing.model.Objects;

public interface ObjectsService {

	List<Objects> getObjects();

	Objects createObject(int id,Objects obj);

	Objects updateObject(int id, Objects obj);
	
	//Objects deleteObject(int id, Objects obj);
	
	Optional<Objects> getById(int id) throws Exception;
	
	Optional<Objects> findByStatus(ObjStatus objStatus);

	Optional<Objects> findByTitle(String title);
}