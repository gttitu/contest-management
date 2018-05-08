package gtt.model.dao.setting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gtt.model.BaseModel;
import gtt.model.ModelException;
import gtt.model.dao.ConnUtils;
import gtt.model.dao.DAOException;
import gtt.model.dao.InterfaceDAO;
import gtt.model.setting.*;

public class MatterDAO  implements InterfaceDAO{
	
	// ATTRIBUTES :
	
	//	private Connection connection;
		
	// CONSTRUCTS :
	public MatterDAO() {}
	/*public MatterDAO(Connection connection) {
		this.connection = connection;
	}*/
	
	// METHODS :
	
	public boolean isCorrect(BaseModel model) {
		return (model instanceof Matter);
	}
	
	@Override
	public int save(BaseModel model)throws Exception {
		
		int result = -1;
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		try {
			
			if(this.isCorrect(model)) {
				connection = ConnUtils.get();
				Matter modelContest = (Matter) model;
				String query = "Insert into Matter (contest, description, coefficient, average, datetimeBegin, datetimeEnd) values (?, ?, ?, ?, ?, ?)";
				preparedStmt = connection.prepareStatement(query);
				preparedStmt.setInt (1, modelContest.getContest());
			    preparedStmt.setString (2, modelContest.getDescription());
			    preparedStmt.setInt(3, modelContest.getCoefficient());
			    preparedStmt.setFloat(4, modelContest.getAverage());
			    preparedStmt.setString (5, modelContest.getDatetimeBegin());
			    preparedStmt.setString(6, modelContest.getDatetimeEnd());
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
				Matter modelContest = (Matter) model;
				String query = "Update Matter set contest = ?, description = ?, coefficient = ?, average = ?, datetimeBegin = ?, datetimeEnd = ? where id = ? ";
				preparedStmt = connection.prepareStatement(query);
				preparedStmt.setInt (1, modelContest.getContest());
			    preparedStmt.setString (2, modelContest.getDescription());
			    preparedStmt.setInt(3, modelContest.getCoefficient());
			    preparedStmt.setFloat(4, modelContest.getAverage());
			    preparedStmt.setString (5, modelContest.getDatetimeBegin());
			    preparedStmt.setString(6, modelContest.getDatetimeEnd());
			    preparedStmt.setInt(7, modelContest.getId());
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
				String query = "Delete from Matter where id = ?";
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
			Matter c = (Matter)tmp.get(0);
			((Matter)model).setContest(c.getContest());
			((Matter)model).setDescription(c.getDescription());
			((Matter)model).setCoefficient(c.getCoefficient());
			((Matter)model).setAverage(c.getAverage());
			((Matter)model).setDatetimeBegin(c.getDatetimeBegin());
			((Matter)model).setDatetimeEnd(c.getDatetimeEnd());
		}
	}

	private List<BaseModel> mapAll(ResultSet set) throws SQLException, ModelException{
		
		List<BaseModel> result = new ArrayList<>();
		
		while(set.next()) {
				result.add(new Matter(set.getInt("id"), set.getInt("contest"), set.getString("description"), set.getInt("coefficient"), set.getFloat("average"), set.getDate("datetimeBegin").toString(), set.getDate("datetimeEnd").toString()));
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
				String query = "SELECT * FROM Matter";
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
		
		String cond = " WHERE MATCH(description) AGAINST (? IN BOOLEAN MODE)";
		String query = "SELECT * FROM Matter" + cond;
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
