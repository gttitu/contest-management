package gtt.model.dao.center;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gtt.model.BaseModel;
import gtt.model.ModelException;
import gtt.model.center.CenterDetail;
import gtt.model.dao.*;

public class CenterDetailDAO implements InterfaceDAO{
	
	// ATTRIBUTES :

	// CONSTRUCTS :
		
	public CenterDetailDAO() {}
	
	// METHODS :
	
	public boolean isCorrect(BaseModel model) {
		return (model instanceof CenterDetail);
	}
	
	@Override
	public int save(BaseModel model)throws Exception {
		
		int result = -1;
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		try {
			
			if(this.isCorrect(model)) {
				connection = ConnUtils.get();
				CenterDetail modelCenter = (CenterDetail) model;
				String query = "Insert into CenterDetail (center, nbMen, nbWomen, minAge, maxAge) values (?, ?, ?, ?, ?)";
				preparedStmt = connection.prepareStatement(query);
				preparedStmt.setInt (1, modelCenter.getCenter());
			    preparedStmt.setInt (2, modelCenter.getNbMen());
			    preparedStmt.setInt(3, modelCenter.getNbWomen());
			    preparedStmt.setInt(4, modelCenter.getMinAge());
			    preparedStmt.setInt(5, modelCenter.getMaxAge());
			    result = preparedStmt.executeUpdate();
		    }
			else			
				throw new DAOException("Incorrect type of model in parameter !");
			
		} 
		catch(Exception e) {
			throw e;
			//e.printStackTrace();
		}
		finally {
			if(preparedStmt!=null)
				preparedStmt.close();
			if(connection!=null)
				connection.close();
		}
		return result;
	}

	@Override
	public int update(BaseModel model)throws Exception {
		int result = -1;
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		try {
			
			if(this.isCorrect(model)) {
				connection = ConnUtils.get();
				CenterDetail modelCenter = (CenterDetail) model;
				String query = "Update CenterDetail set center = ?, nbMen = ?, nbWomen = ?, minAge = ?, maxAge= ? where id = ? ";
				preparedStmt = connection.prepareStatement(query);
				preparedStmt.setInt (1, modelCenter.getCenter());
			    preparedStmt.setInt (2, modelCenter.getNbMen());
			    preparedStmt.setInt(3, modelCenter.getNbWomen());
			    preparedStmt.setInt(4, modelCenter.getMinAge());
			    preparedStmt.setInt(5, modelCenter.getMaxAge());
			    preparedStmt.setInt(6, modelCenter.getId());
			    result = preparedStmt.executeUpdate();
			}
			else			
				throw new DAOException("Incorrect type of model in parameter !");   
			
		} 
		catch(Exception e) {
			throw e;
			//e.printStackTrace();
		}
		finally {
			if(preparedStmt!=null)
				preparedStmt.close();
			if(connection!=null)
				connection.close();
		}
		return result;
	}

	@Override
	public int delete(BaseModel model)throws Exception {
		int result = -1;
		Connection connection = null;
		PreparedStatement preparedStmt = null;	
		try {
			
			if(this.isCorrect(model)) {
				connection = ConnUtils.get();
				String query = "Delete from CenterDetail where id = ?";
				preparedStmt = connection.prepareStatement(query);
				preparedStmt.setInt(1, model.getId());
				result = preparedStmt.executeUpdate();
			}
			else			
				throw new DAOException("Incorrect type of model in parameter !");  	
			
		} 
		catch(Exception e) {
			throw e;
			//e.printStackTrace();
		}
		finally {
			if(preparedStmt!=null)
				preparedStmt.close();
			if(connection!=null)
				connection.close();
		}
		return result;
	}

			
	@SuppressWarnings("unchecked")
	@Override
	public void findById(BaseModel model)throws Exception {
		
		List<BaseModel> tmp = this.findAll(model, "WHERE id = " + model.getId());
		if(tmp.size() == 1) {
			CenterDetail c = (CenterDetail)tmp.get(0);
			((CenterDetail)model).setCenter(c.getCenter());
			((CenterDetail)model).setNbMen(c.getNbMen());
			((CenterDetail)model).setNbWomen(c.getNbWomen());
			((CenterDetail)model).setMinAge(c.getMinAge());
			((CenterDetail)model).setMaxAge(c.getMaxAge());
		}
	}

	private List<BaseModel> mapAll(ResultSet set) throws SQLException, ModelException{
		
		List<BaseModel> result = new ArrayList<>();
		
		while(set.next()) {
				result.add(new CenterDetail(set.getInt("id"), set.getInt("center"), set.getInt("nbMen"), set.getInt("nbWomen"), set.getInt("minAge"), set.getInt("maxAge")));
		} 
		return result;
		
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List findAll(BaseModel baseCond, String specCond) throws Exception {
		List result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet set = null;
		try {
			
			if(this.isCorrect(baseCond)) {
				connection = ConnUtils.get();
				String query = "SELECT * FROM CenterDetail";
				if(specCond != null)
					query += " " + specCond;
				stm = connection.prepareStatement(query);
				set = stm.executeQuery();
				result = this.mapAll(set);
			
			} else {
				
				throw new DAOException("Incorrect type of model in parameter !");
				
			} 
			
		} 
		catch(Exception e) {
			throw e;
			//e.printStackTrace();
		}
		finally {
			if(set!=null)
				set.close();
			if(stm!=null)
				stm.close();
			if(connection!=null)
				connection.close();
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findAll(int page, int row, BaseModel baseCond, String specCond) throws Exception {
		int offset = (page - 1) * row;
		if(specCond == null)
			specCond = " LIMIT " + row + " OFFSET " + offset;
		else
			specCond += " LIMIT " + row + " OFFSET " + offset;
		
		return this.findAll(baseCond, specCond);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List findAllByFullText(Class<?> modelClass, String keywords) throws Exception {
		throw new DAOException("findAllByFullText doesn't work here . Please Use findAll !");
						
	}
	
	
}
