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
	
	Objects getObjById(int id) throws Exception;
	
	Optional<Object> findByStatus(ObjStatus objStatus);

	Optional<Object> findByTitle(String title);
}