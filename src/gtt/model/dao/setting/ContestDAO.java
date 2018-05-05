package gtt.model.dao.setting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gtt.model.BaseModel;
import gtt.model.ModelException;
import gtt.model.dao.DAOException;
import gtt.model.dao.InterfaceDAO;
import gtt.model.setting.*;

public class ContestDAO implements InterfaceDAO{
	
	// ATTRIBUTES :
	
		private Connection connection;
		
	// CONSTRUCTS :
		
	public ContestDAO(Connection connection) {
		this.connection = connection;
	}
	
	// METHODS :
	
	public boolean isCorrect(BaseModel model) {
		return (model instanceof Contest);
	}
	
	@Override
	public int save(BaseModel model)throws Exception {
		
		int result = -1;
		PreparedStatement preparedStmt = null;
		try {
			
			if(this.isCorrect(model)) {
			
				Contest modelContest = (Contest) model;
				String query = "Insert into Contest (description, finished, dateBegin, dateEnd) values (?, ?, ?, ?)";
				preparedStmt = this.connection.prepareStatement(query);
			    preparedStmt.setString (1, modelContest.getDescription());
			    preparedStmt.setBoolean(2, modelContest.isFinished());
			    preparedStmt.setString (3, modelContest.getDateBegin());
			    preparedStmt.setString(4, modelContest.getDateEnd());
			    result = preparedStmt.executeUpdate();
		    }
			else			
				throw new DAOException("Incorrect type of model in parameter !");
			
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(preparedStmt!=null)
				preparedStmt.close();
		}
		return result;
		
		
	}

	@Override
	public int update(BaseModel model)throws Exception {
		int result = -1;
		PreparedStatement preparedStmt = null;
		try {
			
			if(this.isCorrect(model)) {
			
				Contest modelContest = (Contest) model;
				String query = "Update Contest set description = ?, finished = ?, dateBegin = ?, dateEnd = ? where id = ? ";
				preparedStmt = this.connection.prepareStatement(query);
				preparedStmt.setString(1, modelContest.getDescription());
				preparedStmt.setBoolean(2, modelContest.isFinished());
				preparedStmt.setString (3, modelContest.getDateBegin());
			    preparedStmt.setString(4, modelContest.getDateEnd());
			    preparedStmt.setInt(5, modelContest.getId());
			    result = preparedStmt.executeUpdate();
			}
			else			
				throw new DAOException("Incorrect type of model in parameter !");   
			
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(preparedStmt!=null)
				preparedStmt.close();
		}
		return result;
	}

	@Override
	public int delete(BaseModel model)throws Exception {
		int result = -1;
		PreparedStatement preparedStmt = null;
		try {
			
			if(this.isCorrect(model)) {
				String query = "Delete from Contest where id = ?";
				preparedStmt = this.connection.prepareStatement(query);
				preparedStmt.setInt(1, model.getId());
				result = preparedStmt.executeUpdate();
			}
			else			
				throw new DAOException("Incorrect type of model in parameter !");  	
			
		   
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(preparedStmt!=null)
				preparedStmt.close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")		
	@Override
	public void findById(BaseModel model)throws Exception {
		
		List<BaseModel> tmp = this.findAll(model, "WHERE id = " + model.getId());
		if(tmp.size() == 1) {
			Contest c = (Contest)tmp.get(0);
			((Contest)model).setDescription(c.getDescription());
			((Contest)model).setFinished(c.isFinished());
		}
	}

	private List<BaseModel> mapAll(ResultSet set) throws SQLException, ModelException{
		
		List<BaseModel> result = new ArrayList<>();
		
		while(set.next()) {
				result.add(new Contest(set.getInt("id"), set.getString("description"), set.getBoolean("finished"), set.getDate("dateBegin").toString(), set.getDate("dateEnd").toString()));
		} 
		return result;
		
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List findAll(BaseModel baseCond, String specCond) throws Exception {
		List result = new ArrayList<>();
		PreparedStatement stm = null;
		ResultSet set = null;
		try {
			
			if(this.isCorrect(baseCond)) {
			
				String query = "SELECT * FROM Contest";
				if(specCond != null)
					query += " " + specCond;
				stm = this.connection.prepareStatement(query);
				set = stm.executeQuery();
				result = this.mapAll(set);
			
			} else {
				
				throw new DAOException("Incorrect type of model in parameter !");
				
			}
			
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(set!=null)
				set.close();
			if(stm!=null)
				stm.close();
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
	
	// METHODS FOR FULL TEXT :
	
	private List<BaseModel> executeFullText(PreparedStatement stm, String query, String keywords) throws Exception{
		
		List<BaseModel> result = new ArrayList<>();
		ResultSet set = null;
		
		try {
			
			stm = this.connection.prepareStatement(query);
			stm.setObject(1, keywords);
			set = stm.executeQuery();
			result = this.mapAll(set);
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
		} finally {
			
			if(set != null) set.close();
			if(stm != null) stm.close();
			
		} return result;
		
	}
	
	private List<BaseModel> fullTextSearch(String keywords) throws Exception{
		
		String cond = " WHERE MATCH(description) AGAINST (? IN BOOLEAN MODE)";
		String query = "SELECT * FROM Contest" + cond;
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
	
	// METHODS FOR FULL TEXT - END
	
		
}
