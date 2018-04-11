package gtt.model.dao;

import java.util.List;

import gtt.model.BaseModel;
import gtt.model.TableCondition;

public interface InterfaceDAO {
	
	// METHODS TO INHERIT :
	
	BaseModel save(BaseModel model);
	
	BaseModel update(BaseModel model);
	
	void delete(TableCondition condition);
	
	BaseModel findById(TableCondition condition);
	
	List<BaseModel> findAll(TableCondition condition);
	
	List<BaseModel> findAll(int page, int row, TableCondition condition);

}
