package gtt.model.dao.center;

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
import gtt.model.center.Center;

public class CenterDAO  implements InterfaceDAO{
	
	// ATTRIBUTES :
	
		private Connection connection;
		
	// CONSTRUCTS :
		
	public CenterDAO(Connection connection) {
		this.connection = connection;
	}
	
	// METHODS :
	
	public boolean isCorrect(BaseModel model) {
		return (model instanceof Center);
	}
	
	@Override
	public int save(BaseModel model)throws Exception {
		
		int result = -1;
		PreparedStatement preparedStmt = null;
		try {
			
			if(this.isCorrect(model)) {
			
				Center modelCenter = (Center) model;
				String query = "Insert into Center (contest, description, location, nbAllowable) values (?, ?, ?, ?)";
				preparedStmt = this.connection.prepareStatement(query);
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
			
				Center modelCenter = (Center) model;
				String query = "Update Center set contest = ?, description = ?, location = ?, nbAllowable = ? where id = ? ";
				preparedStmt = this.connection.prepareStatement(query);
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
				String query = "Delete from Center where id = ?";
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
	
	@Override
	public List findAll(BaseModel baseCond, String specCond) throws Exception {
		List result = new ArrayList<>();
		PreparedStatement stm = null;
		ResultSet set = null;
		try {
			
			if(this.isCorrect(baseCond)) {
			
				String query = "SELECT * FROM Center";
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
		
		String cond = " WHERE MATCH(description, location) AGAINST (? IN BOOLEAN MODE)";
		String query = "SELECT * FROM Center" + cond;
		System.out.println(query);
		PreparedStatement stm = null;
		
		return this.executeFullText(stm, query, keywords);
		
	}

	@Override
	public List findAllByFullText(Class<?> modelClass, String keywords) throws Exception {
		
		List<BaseModel> result = new ArrayList<>();
		
		if(keywords != null) {
			
			result = this.fullTextSearch(keywords);
			
		} return result;
		
	}
	
}
