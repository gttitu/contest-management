package gtt.model.dao;

import java.util.List;

import gtt.model.BaseModel;
import gtt.model.TableCondition;

public interface InterfaceDAO {
	
	// METHODS TO INHERIT :
	
	int save(BaseModel model) throws Exception;
	
	int update(BaseModel model) throws Exception;
	
	int delete(TableCondition condition) throws Exception;
	
	BaseModel findById(TableCondition condition) throws Exception;
	
	List<BaseModel> findAll(TableCondition condition) throws Exception;
	
	List<BaseModel> findAll(int page, int row, TableCondition condition) throws Exception;

}
