package gtt.model.dao;

import java.util.List;

import gtt.model.BaseModel;

public interface InterfaceDAO {
	
	// METHODS TO INHERIT :
	
	BaseModel save(BaseModel model);
	
	BaseModel update(BaseModel model);
	
	void delete(String id);
	
	List<BaseModel> findById(String id);
	
	List<BaseModel> findAll();

}
