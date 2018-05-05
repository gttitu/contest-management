package gtt.model.dao.mybernate;

import java.util.List;

import gtt.model.BaseModel;
import gtt.model.dao.InterfaceDAO;

public class Mybernate implements InterfaceDAO {

	@Override
	public int save(BaseModel model) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(BaseModel model) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(BaseModel model) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void findById(BaseModel model) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findAll(BaseModel baseCond, String specCond) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findAll(int page, int row, BaseModel baseCond, String specCond) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findAllByFullText(Class<?> modelClass, String keywords) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
