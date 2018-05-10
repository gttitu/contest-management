package gtt.dao.mark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gtt.model.BaseModel;
import gtt.model.ModelException;
import gtt.model.mark.Mark;
import gtt.dao.*;

public class MarkDAO implements InterfaceDAO{
	//ATTRIBUTES:
	//private Connection connection;
	//CONSTRUCTS:
	public MarkDAO() {}
	// METHODS :
		public boolean isCorrect(BaseModel model) {
			
			return (model instanceof Mark);
			
		}
		// INHERITED METHODS :

		@Override
		public int save(BaseModel model) throws Exception {

			int result = -1;
			Connection connection = null;
			PreparedStatement stm = null;
			try {
				if(this.isCorrect(model)) {
					connection = ConnUtils.get();
					Mark c = (Mark)model;
					String query = "INSERT INTO Mark(candidate, matter, markValue) VALUES(?, ?, ?)";
					stm = connection.prepareStatement(query);
					stm.setObject(1, c.getCandidate());
					stm.setObject(2, c.getMatter());
					stm.setObject(3, c.getMarkValue());
					result = stm.executeUpdate();
			
				} else {
				
					throw new DAOException("Incorrect type of model in parameter !");
					
				}
			}
			catch(Exception e) {
				throw e;
			}
				
			finally {
				if(stm!=null)
					stm.close();
				if(connection!=null)
					connection.close();
			}
			return result;
		}
		@Override
		public int update(BaseModel model) throws Exception {
			
			int result = -1;
			Connection connection = null;
			PreparedStatement stm = null;
			try {
				if(this.isCorrect(model)) {
					connection = ConnUtils.get();
					Mark c = (Mark)model;
					String query = "UPDATE Room SET candidate = ?, matter = ?, markValue = ? WHERE id = ?";
					stm = connection.prepareStatement(query);
					stm.setObject(1, c.getCandidate());
					stm.setObject(2, c.getMatter());
					stm.setObject(3, c.getMarkValue());
					result = stm.executeUpdate();
					
				} else {
					
					throw new DAOException("Incorrect type of model in parameter !");
				}
			}
			catch(Exception e) {
				throw e;
			}
			finally {
				if(stm!=null)
				stm.close(); 
				if(connection!=null)
					connection.close();
			}
			return result;
		}
		@Override
		public int delete(BaseModel model) throws Exception {
			
			int result = -1;
			Connection connection = null;
			PreparedStatement stm = null;
			try {
				if(this.isCorrect(model)) {
					connection = ConnUtils.get();
					String query = "DELETE FROM Mark WHERE id = ?";
					stm = connection.prepareStatement(query);
					stm.setObject(1, model.getId());
					result = stm.executeUpdate();
					
				} else {
					
					throw new DAOException("Incorrect type of model in parameter !");
					
				}
			}
			catch(Exception e) {
				throw e;
			}
			finally {
				if(stm!=null)
					stm.close();
				if(connection!=null)
					connection.close();
			}
			return result;
		}
		
		@SuppressWarnings("unchecked")
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
					String query = "SELECT * FROM Mark";
					if(specCond != null)
						query += " " + specCond;
					stm = connection.prepareStatement(query);
					set = stm.executeQuery();
					result = this.mapAll(set);
				
				} else {
					
					throw new DAOException("Incorrect type of model in parameter !");
					
				}
			}catch(Exception e) {
				throw e;
			}finally {
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
				specCond = "LIMIT " + row + " OFFSET " + offset;
			else
				specCond += "LIMIT " + row + " OFFSET " + offset;
			
			return this.findAll(baseCond, specCond);
			
		}
		@SuppressWarnings({ "rawtypes", "unused" })
		@Override
		public List findAllByFullText(Class<?> modelClass, String keywords) throws Exception {
			// TODO Auto-generated method stub
			if(true) throw new DAOException("Full text search can't be use here !");
			return null;
		}
}
