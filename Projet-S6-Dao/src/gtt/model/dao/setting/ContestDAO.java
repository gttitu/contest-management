package gtt.model.dao.setting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gtt.model.BaseModel;
import gtt.model.ModelException;
import gtt.model.dao.*;
import gtt.model.setting.*;
import gtt.cache.*;
import org.cacheonix.*;

public class ContestDAO implements InterfaceDAO{
	
	// ATTRIBUTES :
	
	//	private Connection connection;
		
	// CONSTRUCTS :
	public ContestDAO() {}	
	/*public ContestDAO(Connection connection) {
		this.connection = connection;
	}*/
	
	// METHODS :
	
	public boolean isCorrect(BaseModel model) {
		return (model instanceof Contest);
	}
	
	@Override
	public int save(BaseModel model)throws Exception {
		int result = -1;
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		try {
			
			if(this.isCorrect(model)) {
				connection = ConnUtils.get();
				Contest modelContest = (Contest) model;
				String query = "Insert into Contest (description, finished, dateBegin, dateEnd) values (?, ?, ?, ?)";
				preparedStmt = connection.prepareStatement(query);
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
				Contest modelContest = (Contest) model;
				String query = "Update Contest set description = ?, finished = ?, dateBegin = ?, dateEnd = ? where id = ? ";
				preparedStmt = connection.prepareStatement(query);
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
				String query = "Delete from Contest where id = ?";
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
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet set = null;
		try {
			
			if(this.isCorrect(baseCond)) {
				connection = ConnUtils.get();
				String query = "SELECT * FROM Contest";
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
	
	// METHODS FOR FULL TEXT :
	
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
			if(connection!=null) connection.close();
			
		} return result;
		
	}
	
	private List<BaseModel> fullTextSearch(String keywords) throws Exception{
		
		String cond = " WHERE MATCH(description) AGAINST (? IN BOOLEAN MODE)";
		String query = "SELECT * FROM Contest" + cond;
		PreparedStatement stm = null;
		
		return this.executeFullText(stm, query, keywords);
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List findAllByFullText(Class<?> modelClass, String keywords) throws Exception {
		List<BaseModel> result = new ArrayList<>();
		CacheData cache = new CacheData();
		if(keywords != null) {
			if(cache.isEnabled())
				result = this.findAllByFullTextFromCache(new Contest(), keywords);
			else
				result = this.fullTextSearch(keywords);
		} return result;
	}
	
	@SuppressWarnings("rawtypes")
	public List findAllByFullTextNoCache(Class<?> modelClass, String keywords) throws Exception {
		List<BaseModel> result = new ArrayList<>();
		if(keywords != null) {
			result = this.fullTextSearch(keywords);
		} return result;
		
	}
	

	@SuppressWarnings("rawtypes")
	public List findAllByFullTextFromCache(BaseModel model,  String keywords) throws Exception {
		CacheData cache = new CacheData();
		List result = new ArrayList<>();
		Cacheonix cacheManager = Cacheonix.getInstance();
		if(this.isCorrect(model)) {
			if(cacheManager.cacheExists(keywords + ".cache")) {
				if(cache.isExpired()) {
					cache.putInCache(this.findAllByFullTextNoCache(null, keywords), keywords);
				}
				result = cache.findFromCache(model, keywords);
			}else {
				System.out.println("reading database");
				result=this.findAllByFullTextNoCache(null, keywords);
				cache.putInCache(result, keywords);
			}
		}
		return result;
	}
	
}