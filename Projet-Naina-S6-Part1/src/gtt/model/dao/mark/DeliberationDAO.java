package gtt.model.dao.mark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gtt.model.BaseModel;
import gtt.model.ModelException;
import gtt.model.mark.Deliberation;
import gtt.model.dao.DAOException;
import gtt.model.dao.InterfaceDAO;

public class DeliberationDAO implements InterfaceDAO{
	
		//ATTRIBUTES:
		private Connection connection;
		//CONSTRUCTS:
		public DeliberationDAO(Connection connection) {		
			this.connection = connection;		
		}
		// METHODS :
			public boolean isCorrect(BaseModel model) {
				
				return (model instanceof Deliberation);
				
			}
			// INHERITED METHODS :

			@Override
			public int save(BaseModel model) throws Exception {

				int result = -1;
				PreparedStatement stm = null;
				try {
					if(this.isCorrect(model)) {
					
						Deliberation c = (Deliberation)model;
						String query = "INSERT INTO Delibration(center, matter, markValue) VALUES(?, ?, ?)";
						stm = this.connection.prepareStatement(query);
						stm.setObject(1, c.getCenter());
						stm.setObject(2, c.getMatter());
						stm.setObject(3, c.getMarkValue());
						result = stm.executeUpdate();
					
					} else {
						
						throw new DAOException("Incorrect type of model in parameter !");
						
					} return result;
				}finally {
					if(stm!=null) {
						stm.close();}
				}
				
				
			}
			@Override
			public int update(BaseModel model) throws Exception {
				
				int result = -1;
				PreparedStatement stm = null;	
				try {
					if(this.isCorrect(model)) {
					
						Deliberation c = (Deliberation)model;
						String query = "UPDATE Deliberation SET candidate = ?, matter = ?, markValue = ? WHERE id = ?";
						stm = this.connection.prepareStatement(query);
						stm.setObject(1, c.getCenter());
						stm.setObject(2, c.getMatter());
						stm.setObject(3, c.getMarkValue());
						result = stm.executeUpdate();
						
					} else {
						
						throw new DAOException("Incorrect type of model in parameter !");
						
					}return result;
				}
				finally {
					if(stm!=null) {
						stm.close();}
				}
				
				
			}
			@Override
			public int delete(BaseModel model) throws Exception {
				
				int result = -1;
				PreparedStatement stm = null;
				try {	
					if(this.isCorrect(model)) {
					
						String query = "DELETE FROM Deliberation WHERE id = ?";
						stm = this.connection.prepareStatement(query);
						stm.setObject(1, model.getId());
						result = stm.executeUpdate();
						
					} else {						
						throw new DAOException("Incorrect type of model in parameter !");						
					}return result;
				}finally {
						if(stm!=null) {
							stm.close();}
				}
				
				
			}
			@Override
			public void findById(BaseModel model) throws Exception {
				
				List<BaseModel> tmp = this.findAll(model, "WHERE id = " + model.getId());
				if(tmp.size() == 1) {
					Deliberation c = (Deliberation)tmp.get(0);
					((Deliberation)model).setCenter(c.getCenter());
					((Deliberation)model).setMatter(c.getMatter());
					((Deliberation)model).setMarkValue(c.getMarkValue());
				}
				
			}
			private List<BaseModel> mapAll(ResultSet set) throws SQLException, ModelException{
				
				List<BaseModel> result = new ArrayList<>();
				
				while(set.next()) {
					
					result.add(new Deliberation(set.getInt("id"), set.getInt("center"), set.getInt("matter"), set.getFloat("markValue")));
					
				} return result;
				
			}
			@Override
			public List findAll(BaseModel baseCond, String specCond) throws Exception {
				
				List result = new ArrayList<>();
				PreparedStatement stm = null;
				ResultSet set = null;
				try{
					if(this.isCorrect(baseCond)) {
				
					String query = "SELECT * FROM Deliberation";
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
			public List findAll(int page, int row, BaseModel baseCond, String specCond) throws Exception {
				
				int offset = (page - 1) * row;
				if(specCond == null)
					specCond = "LIMIT " + row + " OFFSET " + offset;
				else
					specCond += "LIMIT " + row + " OFFSET " + offset;
				
				return this.findAll(baseCond, specCond);
				
			}

	
}
