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
import gtt.model.dao.InterfaceDAO;

public class RoomDAO implements InterfaceDAO {

	// ATTRIBUTES :
	
	private Connection connection;
	
	// CONSTRUCTS :
	
	public RoomDAO(Connection connection) {
		
		this.connection = connection;
		
	}
	
	// INHERITED METHODS :

	@Override
	public int save(BaseModel model) throws SQLException {

		Room c = (Room)model;
		String query = "INSERT INTO Room(center) VALUES(?)";
		PreparedStatement stm = this.connection.prepareStatement(query);
		stm.setObject(1, c.getCenter());
		
		return stm.executeUpdate();
		
	}

	@Override
	public int update(BaseModel model) throws SQLException {
		
		Room c = (Room)model;
		String query = "UPDATE Room SET center = ? WHERE id = ?";
		PreparedStatement stm = this.connection.prepareStatement(query);
		stm.setObject(1, c.getCenter());
		stm.setObject(2, c.getId());
		
		return stm.executeUpdate();
		
	}

	@Override
	public int delete(BaseModel model) throws SQLException {
		
		String query = "DELETE FROM Room WHERE id = ?";
		PreparedStatement stm = this.connection.prepareStatement(query);
		stm.setObject(1, model.getId());
		
		return stm.executeUpdate();
		
	}

	@Override
	public void findById(BaseModel model) throws SQLException, ModelException {
		
		List<BaseModel> tmp = this.findAll(null, "WHERE id = " + model.getId());
		if(tmp.size() == 1) {
			Room c = (Room)tmp.get(0);
			((Room)model).setCenter(c.getCenter());
		}
		
	}
	
	private List<BaseModel> mapAll(ResultSet set) throws SQLException, ModelException{
		
		List<BaseModel> result = new ArrayList<>();
		
		while(set.next()) {
			
			result.add(new Room(set.getInt("id"), set.getInt("center")));
			
		} return result;
		
	}

	@Override
	public List<BaseModel> findAll(BaseModel baseCond, String specCond) throws SQLException, ModelException {
		
		String query = "SELECT * FROM Room";
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
