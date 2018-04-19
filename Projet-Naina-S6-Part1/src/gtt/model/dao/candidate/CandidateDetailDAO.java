package gtt.model.dao.candidate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gtt.model.BaseModel;
import gtt.model.ModelException;
import gtt.model.candidate.CandidateDetail;
import gtt.model.dao.InterfaceDAO;

public class CandidateDetailDAO implements InterfaceDAO {
	
	// ATTRIBUTES :
	
	private Connection connection;
	
	// CONSTRUCTS :
	
	public CandidateDetailDAO(Connection connection) {
		
		this.connection = connection;
		
	}

	// INHERITED METHODS :

	@Override
	public int save(BaseModel model) throws SQLException {

		CandidateDetail cd = (CandidateDetail)model;
		String query = "INSERT INTO CandidateDetail(candidate, firstname, lastname, age, gender) VALUES(?, ?, ?, ?, ?)";
		PreparedStatement stm = this.connection.prepareStatement(query);
		stm.setObject(1, cd.getCandidate()); stm.setObject(2, cd.getFirstname());
		stm.setObject(3, cd.getLastname()); stm.setObject(4, cd.getAge());
		stm.setObject(5, cd.getGender());
		
		return stm.executeUpdate();
		
	}

	@Override
	public int update(BaseModel model) throws SQLException {
		
		CandidateDetail cd = (CandidateDetail)model;
		String query = "UPDATE CandidateDetail SET candidate = ?, firstname = ?, lastname = ?, age = ?, gender = ? WHERE id = ?";
		PreparedStatement stm = this.connection.prepareStatement(query);
		stm.setObject(1, cd.getCandidate()); stm.setObject(2, cd.getFirstname());
		stm.setObject(3, cd.getLastname()); stm.setObject(4, cd.getAge());
		stm.setObject(5, cd.getGender()); stm.setObject(6, cd.getId());
		
		return stm.executeUpdate();
		
	}

	@Override
	public int delete(BaseModel model) throws SQLException {
		
		String query = "DELETE FROM CandidateDetail WHERE id = ?";
		PreparedStatement stm = this.connection.prepareStatement(query);
		stm.setObject(1, model.getId());
		
		return stm.executeUpdate();
		
	}

	@Override
	public void findById(BaseModel model) throws SQLException, ModelException {
		
		List<BaseModel> tmp = this.findAll(null, "WHERE id = " + model.getId());
		if(tmp.size() == 1) {
			CandidateDetail c = (CandidateDetail)tmp.get(0);
			((CandidateDetail)model).setCandidate(c.getCandidate());
			((CandidateDetail)model).setFirstname(c.getFirstname());
			((CandidateDetail)model).setLastname(c.getLastname());
			((CandidateDetail)model).setAge(c.getAge());
			((CandidateDetail)model).setGender(c.getGender());
		}
		
	}
	
	private List<BaseModel> mapAll(ResultSet set) throws SQLException, ModelException{
		
		List<BaseModel> result = new ArrayList<>();
		
		while(set.next()) {
			
			result.add(new CandidateDetail(set.getInt("id"), set.getInt("candidate"), set.getString("firstname"), set.getString("lastname"), set.getInt("age"), set.getInt("gender")));
			
		} return result;
		
	}

	@Override
	public List<BaseModel> findAll(BaseModel baseCond, String specCond) throws SQLException, ModelException {
		
		String query = "SELECT * FROM CandidateDetail";
		if(specCond != null)
			query += " " + specCond;
		PreparedStatement stm = this.connection.prepareStatement(query);
		ResultSet set = stm.executeQuery();
		
		return this.mapAll(set);
		
	}

	@Override
	public List<BaseModel> findAll(int page, int row, BaseModel baseCond, String specCond) throws SQLException, ModelException {
		
		int offset = (page - 1) * row;
		if(specCond == null)
			specCond = "LIMIT " + row + " OFFSET " + offset;
		else
			specCond += "LIMIT " + row + " OFFSET " + offset;
		
		return this.findAll(baseCond, specCond);
		
	}

}
