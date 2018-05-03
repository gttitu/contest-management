package gtt.model.dao;

import java.util.List;

import gtt.model.BaseModel;

public interface InterfaceDAO {
	
	// METHODS TO INHERIT :
	
	int save(BaseModel model) throws Exception;
	
	int update(BaseModel model) throws Exception;
	
	int delete(BaseModel model) throws Exception;
	
	void findById(BaseModel model) throws Exception;
	
	List<?> findAll(BaseModel condition, String query) throws Exception;
	
	List<?> findAll(int page, int row, BaseModel condition, String query) throws Exception;

}
