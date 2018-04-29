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
		
		if(this.isCorrect(model)) {
		
			Contest modelContest = (Contest) model;
			String query = "Insert into Contest (description, finished, dateBegin, dateEnd) values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = this.connection.prepareStatement(query);
		    preparedStmt.setString (1, modelContest.getDescription());
		    preparedStmt.setBoolean(2, modelContest.isFinished());
		    preparedStmt.setString (3, modelContest.getDateBegin());
		    preparedStmt.setString(4, modelContest.getDateEnd());
		    result = preparedStmt.executeUpdate();
	    }
		else			
			throw new DAOException("Incorrect type of model in parameter !");
		
		return result;
	}

	@Override
	public int update(BaseModel model)throws Exception {
		int result = -1;
		
		if(this.isCorrect(model)) {
		
			Contest modelContest = (Contest) model;
			String query = "Update Contest set description = ?, finished = ?, dateBegin = ?, dateEnd = ? where id = ? ";
			PreparedStatement preparedStmt = this.connection.prepareStatement(query);
			preparedStmt.setString(1, modelContest.getDescription());
			preparedStmt.setBoolean(2, modelContest.isFinished());
			preparedStmt.setString (3, modelContest.getDateBegin());
		    preparedStmt.setString(4, modelContest.getDateEnd());
		    preparedStmt.setInt(5, modelContest.getId());
		    result = preparedStmt.executeUpdate();
		}
		else			
			throw new DAOException("Incorrect type of model in parameter !");   
		    
	    return result;
	}

	@Override
	public int delete(BaseModel model)throws Exception {
		int result = -1;
			
		if(this.isCorrect(model)) {
			String query = "Delete from Contest where id = ?";
			PreparedStatement preparedStmt = this.connection.prepareStatement(query);
			preparedStmt.setInt(1, model.getId());
			result = preparedStmt.executeUpdate();
		}
		else			
			throw new DAOException("Incorrect type of model in parameter !");  	
	    return result;
	}

			
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
	
	@Override
	public List<BaseModel> findAll(BaseModel baseCond, String specCond) throws Exception {
		List<BaseModel> result = new ArrayList<>();
		
		if(this.isCorrect(baseCond)) {
		
			String query = "SELECT * FROM Contest";
			if(specCond != null)
				query += " " + specCond;
			PreparedStatement stm = this.connection.prepareStatement(query);
			ResultSet set = stm.executeQuery();
			result = this.mapAll(set);
		
		} else {
			
			throw new DAOException("Incorrect type of model in parameter !");
			
		} return result;
	}

	@Override
	public List<BaseModel> findAll(int page, int row, BaseModel baseCond, String specCond) throws Exception {
		int offset = (page - 1) * row;
		if(specCond == null)
			specCond = " LIMIT " + row + " OFFSET " + offset;
		else
			specCond += " LIMIT " + row + " OFFSET " + offset;
		
		return this.findAll(baseCond, specCond);
	}
	
	
}