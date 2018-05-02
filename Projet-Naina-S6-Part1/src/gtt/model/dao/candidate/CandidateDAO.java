package gtt.model.dao.candidate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gtt.model.BaseModel;
import gtt.model.ModelException;
import gtt.model.candidate.Candidate;
import gtt.model.dao.InterfaceDAO;

public class CandidateDAO implements InterfaceDAO {
	
	// ATTRIBUTES :
	
	private Connection connection;
	
	// CONSTRUCTS :
	
	public CandidateDAO(Connection connection) {
		
		this.connection = connection;
		
	}
	
	// INHERITED METHODS :

	@Override
	public int save(BaseModel model) throws SQLException {
		PreparedStatement stm = null;
		
		try {
			Candidate c = (Candidate)model;
			String query = "INSERT INTO Candidate(center) VALUES(?)";
			stm = this.connection.prepareStatement(query);
			stm.setObject(1, c.getCenter());
			return stm.executeUpdate();
		}finally {
			if(stm!=null)
				stm.close();
		}
		
		
		
	}

	@Override
	public int update(BaseModel model) throws SQLException {
		PreparedStatement stm = null;
		try {
			Candidate c = (Candidate)model;
			String query = "UPDATE Candidate SET center = ? WHERE id = ?";
			stm = this.connection.prepareStatement(query);
			stm.setObject(1, c.getCenter());
			stm.setObject(2, c.getId());
			return stm.executeUpdate();
		}finally {
			if(stm!=null)
				stm.close();
		}
		
	}

	@Override
	public int delete(BaseModel model) throws SQLException {
		PreparedStatement stm = null;
		try {
			String query = "DELETE FROM Candidate WHERE id = ?";
			stm = this.connection.prepareStatement(query);
			stm.setObject(1, model.getId());
			return stm.executeUpdate();
		}finally {
			if(stm!=null)
				stm.close();
		}		
		
	}

	@Override
	public void findById(BaseModel model) throws SQLException, ModelException {
		
		List<BaseModel> tmp = this.findAll(null, "WHERE id = " + model.getId());
		if(tmp.size() == 1) {
			Candidate c = (Candidate)tmp.get(0);
			((Candidate)model).setCenter(c.getCenter());
		}
		
	}
	
	private List<BaseModel> mapAll(ResultSet set) throws SQLException, ModelException{
		
		List<BaseModel> result = new ArrayList<>();
		
		while(set.next()) {
			
			result.add(new Candidate(set.getInt("id"), set.getInt("center")));
			
		} return result;
		
	}

	@Override
	public List findAll(BaseModel baseCond, String specCond) throws SQLException, ModelException {
		PreparedStatement stm = null;
		ResultSet set = null;
		try {
			String query = "SELECT * FROM Candidate";
			if(specCond != null)
				query += " " + specCond;
			stm = this.connection.prepareStatement(query);
			set = stm.executeQuery();
			return this.mapAll(set);
		}finally {
			if(set!=null)
				set.close();
			if(stm!=null)
				stm.close();
		}
		
		
	}

	@Override
	public List findAll(int page, int row, BaseModel baseCond, String specCond) throws SQLException, ModelException {
		
		int offset = (page - 1) * row;
		if(specCond == null)
			specCond = "LIMIT " + row + " OFFSET " + offset;
		else
			specCond += "LIMIT " + row + " OFFSET " + offset;
		
		return this.findAll(baseCond, specCond);
		
	}

}
