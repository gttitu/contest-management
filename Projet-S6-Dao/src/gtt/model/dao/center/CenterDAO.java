package gtt.model.dao.center;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gtt.model.BaseModel;
import gtt.model.ModelException;
import gtt.model.center.Center;
import gtt.model.dao.*;

public class CenterDAO  implements InterfaceDAO{
	
	// ATTRIBUTES :
		
	// CONSTRUCTS :
		public CenterDAO() {}
	
	// METHODS :
	
	public boolean isCorrect(BaseModel model) {
		return (model instanceof Center);
	}
	
	@Override
	public int save(BaseModel model)throws Exception {
		
		int result = -1;
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		try {
			
			if(this.isCorrect(model)) {
				connection = ConnUtils.get();
				Center modelCenter = (Center) model;
				String query = "Insert into Center (contest, description, location, nbAllowable) values (?, ?, ?, ?)";
				preparedStmt = connection.prepareStatement(query);
				preparedStmt.setInt (1, modelCenter.getContest());
			    preparedStmt.setString (2, modelCenter.getDescription());
			    preparedStmt.setString(3, modelCenter.getLocation());
			    preparedStmt.setInt(4, modelCenter.getNbAllowable());
			    result = preparedStmt.executeUpdate();
		    }
			else			
				throw new DAOException("Incorrect type of model in parameter !");
			
		} 
		catch(Exception e) {
			throw e;
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
				Center modelCenter = (Center) model;
				String query = "Update Center set contest = ?, description = ?, location = ?, nbAllowable = ? where id = ? ";
				preparedStmt = connection.prepareStatement(query);
				preparedStmt.setInt (1, modelCenter.getContest());
			    preparedStmt.setString (2, modelCenter.getDescription());
			    preparedStmt.setString(3, modelCenter.getLocation());
			    preparedStmt.setInt(4, modelCenter.getNbAllowable());
			    preparedStmt.setInt(5, modelCenter.getId());
			    result = preparedStmt.executeUpdate();
			}
			else			
				throw new DAOException("Incorrect type of model in parameter !");   
			
		} 
		catch(Exception e) {
			throw e;
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
				String query = "Delete from Center where id = ?";
				preparedStmt = connection.prepareStatement(query);
				preparedStmt.setInt(1, model.getId());
				result = preparedStmt.executeUpdate();
			}
			else			
				throw new DAOException("Incorrect type of model in parameter !");  	
			
		} 
		catch(Exception e) {
			throw e;
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
			Center c = (Center)tmp.get(0);
			((Center)model).setContest(c.getContest());
			((Center)model).setDescription(c.getDescription());
			((Center)model).setLocation(c.getLocation());
			((Center)model).setNbAllowable(c.getNbAllowable());
			
		}
	}

	private List<BaseModel> mapAll(ResultSet set) throws SQLException, ModelException{
		
		List<BaseModel> result = new ArrayList<>();
		
		while(set.next()) {
				result.add(new Center(set.getInt("id"), set.getInt("contest"), set.getString("description"), set.getString("location"), set.getInt("nbAllowable")));
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
				String query = "SELECT * FROM Center";
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
	
	private List<BaseModel> executeFullText(PreparedStatement stm, String query, String keywords) throws Exception{
		
		List<BaseModel> result = new ArrayList<>();
		Connection connection = null;
		ResultSet set = null;
		
		try {
			connection = ConnUtils.get();
			stm = connection.prepareStatement(query);
			stm.setObject(1, keywords);
			set = stm.executeQuery();
			result = this.mapAll(set);
			
		} catch(Exception ex) {
			
			throw ex;
			
		} finally {
			
			if(set != null) set.close();
			if(stm != null) stm.close();
			if(connection!=null)
				connection.close();
			
		} return result;
		
	}
	
	private List<BaseModel> fullTextSearch(String keywords) throws Exception{
		
		String cond = " WHERE MATCH(description, location) AGAINST (? IN BOOLEAN MODE)";
		String query = "SELECT * FROM Center" + cond;
		PreparedStatement stm = null;
		
		return this.executeFullText(stm, query, keywords);
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findAllByFullText(Class<?> modelClass, String keywords) throws Exception {
		
		List<BaseModel> result = new ArrayList<>();
		
		if(keywords != null) {
			
			result = this.fullTextSearch(keywords);
			
		} return result;
		
	}
	
}
