package gtt.model.dao.center;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gtt.model.BaseModel;
import gtt.model.ModelException;
import gtt.model.center.Room;
import gtt.model.center.RoomDetail;
import gtt.model.dao.DAOException;
import gtt.model.dao.InterfaceDAO;

public class RoomDetailDAO implements InterfaceDAO {

	// ATTRIBUTES :
	
	private Connection connection;
	
	// CONSTRUCTS :
	
	public RoomDetailDAO(Connection connection) {
		
		this.connection = connection;
		
	}
	
	// METHODS :
	
	public boolean isCorrect(BaseModel model) {
		
		return (model instanceof RoomDetail);
		
	}
	
	// INHERITED METHODS :

	@Override
	public int save(BaseModel model) throws Exception {

		int result = -1;
		
		if(this.isCorrect(model)) {
		
			RoomDetail c = (RoomDetail)model;
			String query = "INSERT INTO RoomDetail(room, candidate) VALUES(?, ?)";
			PreparedStatement stm = this.connection.prepareStatement(query);
			stm.setObject(1, c.getRoom());
			stm.setObject(2, c.getCandidate());
			result = stm.executeUpdate();
		
		} else {
			
			throw new DAOException("Incorrect type of model in parameter !");
			
		} return result;
		
	}

	@Override
	public int update(BaseModel model) throws Exception {
		
		int result = -1;
				
		if(this.isCorrect(model)) {
		
			RoomDetail c = (RoomDetail)model;
			String query = "UPDATE Room SET room = ?, candidate = ? WHERE id = ?";
			PreparedStatement stm = this.connection.prepareStatement(query);
			stm.setObject(1, c.getRoom());
			stm.setObject(2, c.getCandidate());
			stm.setObject(3, c.getId());
			result = stm.executeUpdate();
			
		} else {
			
			throw new DAOException("Incorrect type of model in parameter !");
			
		} return result;
		
	}

	@Override
	public int delete(BaseModel model) throws Exception {
		
		int result = -1;
		
		if(this.isCorrect(model)) {
		
			String query = "DELETE FROM RoomDetail WHERE id = ?";
			PreparedStatement stm = this.connection.prepareStatement(query);
			stm.setObject(1, model.getId());
			result = stm.executeUpdate();
			
		} else {
			
			throw new DAOException("Incorrect type of model in parameter !");
			
		} return result;
		
	}

	@Override
	public void findById(BaseModel model) throws Exception {
		
		List<BaseModel> tmp = this.findAll(model, "WHERE id = " + model.getId());
		if(tmp.size() == 1) {
			RoomDetail c = (RoomDetail)tmp.get(0);
			((RoomDetail)model).setRoom(c.getRoom());
			((RoomDetail)model).setCandidate(c.getCandidate());
		}
		
	}
	
	private List<BaseModel> mapAll(ResultSet set) throws SQLException, ModelException{
		
		List<BaseModel> result = new ArrayList<>();
		
		while(set.next()) {
			
			result.add(new Room(set.getInt("id"), set.getInt("center")));
			
		} return result;
		
	}

	@Override
	public List<BaseModel> findAll(BaseModel baseCond, String specCond) throws Exception {
		
		List<BaseModel> result = new ArrayList<>();
		
		if(this.isCorrect(baseCond)) {
		
			String query = "SELECT * FROM RoomDetail";
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
			specCond = "LIMIT " + row + " OFFSET " + offset;
		else
			specCond += "LIMIT " + row + " OFFSET " + offset;
		
		return this.findAll(baseCond, specCond);
		
	}

}
