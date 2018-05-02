package gtt.model.dao.generic;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gtt.model.BaseModel;
import gtt.model.dao.InterfaceDAO;

public class GenericDAO implements InterfaceDAO {
	
	// ATTRIBUTES :
	
	private Connection connection;
	private boolean autoIncrement;
	
	// CONSTRUCTS :
	
	public GenericDAO(Connection connection, boolean autoIncrement) {
		
		this.setConnection(connection);
		this.setAutoIncrement(autoIncrement);
		
	}
	
	// GETTERS AND SETTERS :
	
	public Connection getConnection() { return connection; }

	public void setConnection(Connection connection) { this.connection = connection; }

	public boolean isAutoIncrement() { return autoIncrement; }

	public void setAutoIncrement(boolean autoIncrement) { this.autoIncrement = autoIncrement; }
	
	// INHERITED METHODS :
	
	protected int autoIncrementSave(BaseModel model) throws Exception{
		
		String tablename = UtilsDAO.getTableName(model.getClass());
		List<Field> attributes = UtilsDAO.getAttributesWithoutId(model);
		String query = this.buildInsert(tablename, attributes);
		
		return this.executeSave(model, query, attributes);
		
	}
	
	protected String listAttributes(List<Field> attributes) {
		
		String result = "(";
		
		for(int i = 0, size = attributes.size(); i < size; i++) {
			
			Field f = attributes.get(i);
			result += f.getName();
			if(i != size - 1)
				result += ",";
			
		} result += ")"; return result;
		
	}
	
	protected String prepareInsert(List<Field> attributes) {
		
		String result = "(";
		
		for(int i = 0, size = attributes.size(); i < size; i ++) {
			
			result += "?";
			if(i != size - 1)
				result += ",";
			
		} result += ")"; return result;
		
	}
	
	protected String buildInsert(String tablename, List<Field> attributes) {
		
		String query1 = "INSERT INTO " + tablename;
		String query2 = this.listAttributes(attributes);
		String query3 = " VALUES";
		String query4 = this.prepareInsert(attributes);
		
		return query1 + query2 + query3 + query4;
		
	}
	
	protected Object getValueOf(BaseModel model, Field field) throws Exception {
		
		Object result = null;
		
		Method getter = UtilsDAO.getGetter(model, field.getName());
		Object[] params = null;
		result = getter.invoke(model, params);
		
		return result;
		
	}
	
	protected int executeSave(BaseModel model, String query, List<Field> attributes) throws Exception {
		
		int result = -1;
		
		PreparedStatement stm = this.connection.prepareStatement(query);
		for(int i = 0, size = attributes.size(); i < size; i++) {
			
			Object value = this.getValueOf(model, attributes.get(i));
			stm.setObject((i+1), value);
			
		} result = stm.executeUpdate();
		
		return result;
		
	}
	
	protected int executeUpdate(BaseModel model, String query, List<Field> attributes) throws Exception {
		
		int result = -1;
		
		PreparedStatement stm = this.connection.prepareStatement(query);
		for(int i = 0, size = attributes.size(); i < size; i++) {
			
			Object value = this.getValueOf(model, attributes.get(i));
			stm.setObject((i+1), value);
			
		} stm.setObject(attributes.size() + 1, model.getId());
		
		result = stm.executeUpdate(); return result;
		
	}
	
	protected int simpleSave(BaseModel model) throws Exception{
		
		String tablename = UtilsDAO.getTableName(model.getClass());
		List<Field> attributes = UtilsDAO.getAttributes(model);
		String query = this.buildInsert(tablename, attributes);
		
		return this.executeSave(model, query, attributes);
		
	}

	@Override
	public int save(BaseModel model) throws Exception {
		
		int result = -1;
		
		if(this.isAutoIncrement())
			result = this.autoIncrementSave(model);
		else
			result = this.simpleSave(model);
		
		return result;
		
	}
	
	protected String prepareUpdate(List<Field> attributes) {
		
		String result = "SET ";
		
		for(int i = 0, size = attributes.size(); i < size; i ++) {
			
			result += attributes.get(i).getName() + " = ?";
			if(i != size - 1)
				result += ", ";
			
		} return result;
		
	}
	
	protected String buildUpdate(String tablename, List<Field> attributes) {
		
		String query1 = "UPDATE " + tablename + " ";
		String query2 = this.prepareUpdate(attributes);
		String query3 = " WHERE id = ?";
		
		return query1 + query2 + query3;
		
	}

	@Override
	public int update(BaseModel model) throws Exception {

		String tablename = UtilsDAO.getTableName(model.getClass());
		List<Field> attributes = UtilsDAO.getAttributesWithoutId(model);
		String query = this.buildUpdate(tablename, attributes);
		
		return this.executeUpdate(model, query, attributes);
		
	}
	
	@Override
	public int delete(BaseModel model) throws Exception {
		
		String query = "DELETE FROM " + UtilsDAO.getTableName(model.getClass()) + " WHERE id = ?";
		PreparedStatement stm = this.connection.prepareStatement(query);
		stm.setObject(1, model.getId());
		
		return stm.executeUpdate();
		
	}
	
	protected void mapById(BaseModel model, BaseModel tmp) throws Exception {
		
		List<Field> attributes = UtilsDAO.getAttributesWithoutId(model);
		for(Field f : attributes) {
			
			Method getter = UtilsDAO.getGetter(tmp, f.getName());
			Object params = null;
			Object returned = getter.invoke(tmp, params);
			if(returned != null) {
				
				Method setter = UtilsDAO.getSetter(model, f.getName());
				setter.invoke(model, returned);
				
			}
			
		}
		
	}

	@Override
	public void findById(BaseModel model) throws Exception {
		
		List<BaseModel> models = this.findAll(model, null);
		if(models != null && models.size() == 1){
			BaseModel tmp = models.get(0);
			this.mapById(model, tmp);
		}
		
	}
	
	protected BaseModel map(Class<?> modelClass, ResultSet reader) throws Exception {
		
		BaseModel result = (BaseModel)modelClass.newInstance();
		
		List<Field> attributes = UtilsDAO.getAttributes(result);
		for(Field f : attributes) {
			
			Method tmp = UtilsDAO.getSetter(result, f.getName());
			if(tmp != null) {
				Object[] params = { reader.getObject(UtilsDAO.getAttrName(f)) };
				tmp.invoke(result, params);
			}
			
		} return result;
		
	}
	
	protected List<BaseModel> mapAll(Class<?> modelClass, PreparedStatement stm) throws Exception{
		
		List<BaseModel> result = new ArrayList<>();
		
		ResultSet reader = stm.executeQuery();
		while(reader.next()) {
			
			BaseModel tmp = this.map(modelClass, reader);
			if(tmp != null)
				result.add(tmp);
			
		} return result;
		
	}

	@Override
	public List<BaseModel> findAll(BaseModel baseCond, String specCond) throws Exception {
		
		String query = "SELECT * FROM " + UtilsDAO.getTableName(baseCond.getClass()) + this.buildCondition(baseCond, specCond);
		PreparedStatement stm = this.connection.prepareStatement(query);
		
		return this.mapAll(baseCond.getClass(), stm);
		
	}

	@Override
	public List<BaseModel> findAll(int page, int row, BaseModel baseCond, String specCond) throws Exception {
		
		int offset = (page - 1) * row;
		if(specCond == null)
			specCond = "LIMIT " + row + " OFFSET " + offset;
		else
			specCond += " LIMIT " + row + " OFFSET " + offset;
		
		return this.findAll(baseCond, specCond);
		
	}
	
	// METHODS :
	
	protected String writeConditionWith(BaseModel model, Field attribute) {
		
		String result = UtilsDAO.getAttrName(attribute);
		
		result += " " + UtilsDAO.getCondOperand(attribute) + " ?";
		
		return result;
		
	}
	
	protected String getPreparedQuery(String query, BaseModel model, List<Field> attributes) throws Exception {
		
		PreparedStatement stm = this.connection.prepareStatement(query);
		for(int i = 0, size = attributes.size(); i < size; i++) {
			
			stm.setObject(i + 1, UtilsDAO.getValueOf(model, attributes.get(i)));
			
		} int index = stm.toString().indexOf(": ");
		
		return stm.toString().substring(index + 2);
		
	}
	
	protected String arrangeBaseCond(BaseModel baseCond) throws Exception {
		
		String result = ""; List<Field> attributes = new ArrayList<>();
		
		if(baseCond != null && (attributes = UtilsDAO.getAttrNotNull(baseCond, UtilsDAO.getAttributes(baseCond))).size() > 0) {
			
			result = "WHERE (";
			
			for(int i = 0, size = attributes.size(); i < size; i++) {
				
				result += this.writeConditionWith(baseCond, attributes.get(i));
				if(i != size-1)
					result += " AND ";
				
			} result += ")";
			
		} return this.getPreparedQuery(result, baseCond, attributes);
		
	}
	
	protected String arrangeSpecCond(String specCond) {
		
		String result = "";
		
		if(specCond != null) {
			
			result = specCond;
			
		} return result;
		
	}
	
	protected String addCorrectlySpecCond(String result, String specCond) {
		
		String sc = this.arrangeSpecCond(specCond), sc2 = sc.toLowerCase();
		int index = sc2.indexOf("where ");
		if(index >= 0)
			result += " AND " + sc.substring(index + 6);
		else
			result += " " + sc;
		
		return result;
		
	}
	
	protected String buildCondition(BaseModel baseCond, String specCond) throws Exception {
		
		String result = this.arrangeBaseCond(baseCond);
		
		if(result.length() == 0)
			result = this.arrangeSpecCond(specCond);
		else
			result = this.addCorrectlySpecCond(result, specCond);
		if(result.length() > 0)
			result = " " + result;
			
		return result;
		
	}

}
