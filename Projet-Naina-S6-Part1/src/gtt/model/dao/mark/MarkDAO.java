package gtt.model.dao.mark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gtt.model.BaseModel;
import gtt.model.ModelException;
import gtt.model.mark.Mark;
import gtt.model.dao.DAOException;
import gtt.model.dao.InterfaceDAO;

public class MarkDAO implements InterfaceDAO{
	//ATTRIBUTES:
	private Connection connection;
	//CONSTRUCTS:
	public MarkDAO(Connection connection) {		
		this.connection = connection;		
	}
	// METHODS :
		public boolean isCorrect(BaseModel model) {
			
			return (model instanceof Mark);
			
		}
		// INHERITED METHODS :

		@Override
		public int save(BaseModel model) throws Exception {

			int result = -1;
			PreparedStatement stm = null;
			try {
				if(this.isCorrect(model)) {
			
					Mark c = (Mark)model;
					String query = "INSERT INTO Mark(candidate, matter, markValue) VALUES(?, ?, ?)";
					stm = this.connection.prepareStatement(query);
					stm.setObject(1, c.getCandidate());
					stm.setObject(2, c.getMatter());
					stm.setObject(3, c.getMarkValue());
					result = stm.executeUpdate();
			
				} else {
				
					throw new DAOException("Incorrect type of model in parameter !");
					
				}
				return result;
			}finally {
				if(stm!=null)
					stm.close(); 
			}
			
		}
		@Override
		public int update(BaseModel model) throws Exception {
			
			int result = -1;
			PreparedStatement stm = null;
			try {
				if(this.isCorrect(model)) {
			
					Mark c = (Mark)model;
					String query = "UPDATE Room SET candidate = ?, matter = ?, markValue = ? WHERE id = ?";
					stm = this.connection.prepareStatement(query);
					stm.setObject(1, c.getCandidate());
					stm.setObject(2, c.getMatter());
					stm.setObject(3, c.getMarkValue());
					result = stm.executeUpdate();
					
				} else {
					
					throw new DAOException("Incorrect type of model in parameter !");
				}
				return result;
			}
			finally {
				if(stm!=null)
				stm.close(); 
			}
			
		}
		@Override
		public int delete(BaseModel model) throws Exception {
			
			int result = -1;
			PreparedStatement stm = null;
			try {
				if(this.isCorrect(model)) {
				
					String query = "DELETE FROM Mark WHERE id = ?";
					stm = this.connection.prepareStatement(query);
					stm.setObject(1, model.getId());
					result = stm.executeUpdate();
					
				} else {
					
					throw new DAOException("Incorrect type of model in parameter !");
					
				}
				return result;
			}
			finally {
				if(stm!=null)
					stm.close();
			}
			
		}
		@Override
		public void findById(BaseModel model) throws Exception {
			
			List<BaseModel> tmp = this.findAll(model, "WHERE id = " + model.getId());
			if(tmp.size() == 1) {
				Mark c = (Mark)tmp.get(0);
				((Mark)model).setCandidate(c.getCandidate());
				((Mark)model).setMatter(c.getMatter());
				((Mark)model).setMarkValue(c.getMarkValue());
			}
			
		}
		private List<BaseModel> mapAll(ResultSet set) throws SQLException, ModelException{
			
			List<BaseModel> result = new ArrayList<>();
			
			while(set.next()) {
				
				result.add(new Mark(set.getInt("id"), set.getInt("candidate"), set.getInt("matter"), set.getFloat("markValue")));
				
			} return result;
			
		}
		@Override
		public List<BaseModel> findAll(BaseModel baseCond, String specCond) throws Exception {
			
			List<BaseModel> result = new ArrayList<>();
			PreparedStatement stm = null;
			ResultSet set = null;
			try {
				if(this.isCorrect(baseCond)) {
				
					String query = "SELECT * FROM Mark";
					if(specCond != null)
						query += " " + specCond;
					stm = this.connection.prepareStatement(query);
					set = stm.executeQuery();
					result = this.mapAll(set);
				
				} else {
					
					throw new DAOException("Incorrect type of model in parameter !");
					
				}return result;
			}finally {
				if(set!=null)
					set.close();
				if(stm!=null)
					stm.close();
			}			
			
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
