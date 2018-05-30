package gtt.model.dao;

import java.util.List;

import gtt.model.BaseModel;

public interface InterfaceDAO {
	
	// METHODS TO INHERIT :
	
	int save(BaseModel model) throws Exception;
	
	int update(BaseModel model) throws Exception;
	
	int delete(BaseModel model) throws Exception;
	
	void findById(BaseModel model) throws Exception;
	
	@SuppressWarnings("rawtypes")
	List findAll(BaseModel baseCond, String specCond) throws Exception;
	
	@SuppressWarnings("rawtypes")
	List findAll(int page, int row, BaseModel baseCond, String specCond) throws Exception;
	
	@SuppressWarnings("rawtypes")
	List findAllByFullText(Class<?> modelClass, String keywords) throws Exception;
	
}
