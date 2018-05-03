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

public class MatterDAO  implements InterfaceDAO{
	
	// ATTRIBUTES :
	
		private Connection connection;
		
	// CONSTRUCTS :
		
	public MatterDAO(Connection connection) {
		this.connection = connection;
	}
	
	// METHODS :
	
	public boolean isCorrect(BaseModel model) {
		return (model instanceof Matter);
	}
	
	@Override
	public int save(BaseModel model)throws Exception {
		
		int result = -1;
		PreparedStatement preparedStmt = null;
		try {
			
			if(this.isCorrect(model)) {
			
				Matter modelContest = (Matter) model;
				String query = "Insert into Matter (contest, description, coefficient, average, datetimeBegin, datetimeEnd) values (?, ?, ?, ?, ?, ?)";
				preparedStmt = this.connection.prepareStatement(query);
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
			return result;
		} finally {
			if(preparedStmt!=null)
				preparedStmt.close();
		}
		
	}

	@Override
	public int update(BaseModel model)throws Exception {
		int result = -1;
		PreparedStatement preparedStmt = null;
		try {
			
			if(this.isCorrect(model)) {
			
				Matter modelContest = (Matter) model;
				String query = "Update Matter set contest = ?, description = ?, coefficient = ?, average = ?, datetimeBegin = ?, datetimeEnd = ? where id = ? ";
				preparedStmt = this.connection.prepareStatement(query);
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
			return result;
		} finally {
			if(preparedStmt!=null)
				preparedStmt.close();
		}
		
	}

	@Override
	public int delete(BaseModel model)throws Exception {
		int result = -1;
		PreparedStatement preparedStmt = null;	
		try {
			
			if(this.isCorrect(model)) {
				String query = "Delete from Matter where id = ?";
				preparedStmt = this.connection.prepareStatement(query);
				preparedStmt.setInt(1, model.getId());
				result = preparedStmt.executeUpdate();
			}
			else			
				throw new DAOException("Incorrect type of model in parameter !");  	
			
			return result;
		} finally {
			if(preparedStmt!=null)
				preparedStmt.close();
		}
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
		PreparedStatement stm = null;
		ResultSet set = null;
		try {
			
			if(this.isCorrect(baseCond)) {
			
				String query = "SELECT * FROM Matter";
				if(specCond != null)
					query += " " + specCond;
				stm = this.connection.prepareStatement(query);
				set = stm.executeQuery();
				result = this.mapAll(set);
			
			} else {
				
				throw new DAOException("Incorrect type of model in parameter !");
				
			} 
			return result;
		} finally {
			if(set!=null)
				set.close();
			if(stm!=null)
				stm.close();
		}
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
	public List findAllByFullText(BaseModel baseCond, String keywords) throws Exception {
		
		List result = new ArrayList<>();
		PreparedStatement stm = null;
		ResultSet set = null;
		try {
			
			if(baseCond == null) {
			
				String query = "SELECT * FROM Matter";
				stm = null;
				if(keywords != null){
					keywords = "*"+ keywords + "*";
					query += " WHERE MATCH (description) AGAINST (? IN BOOLEAN MODE)";
					stm = this.connection.prepareStatement(query);
					stm.setObject(1, keywords);
					
				} else{
				
					stm = this.connection.prepareStatement(query);
				
				} set = stm.executeQuery();
				result = this.mapAll(set);
			
			} else {
				
				throw new DAOException("BaseCond doesn't work here . Use specCond for this approach !");
				
			} 
			return result;
		} finally {
			if(set!=null)
				set.close();
			if(stm!=null)
				stm.close();
		}
		
	}
	
	
}
