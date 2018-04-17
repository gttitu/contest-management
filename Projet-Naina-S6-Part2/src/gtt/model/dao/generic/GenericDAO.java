package gtt.model.dao.generic;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gtt.model.BaseModel;
import gtt.model.GenericCondition;
import gtt.model.TableCondition;
import gtt.model.dao.DaoException;
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
	
	protected int autoIncrement_save(BaseModel model) throws Exception{
		
		String tablename = UtilsDAO.getTableName(model.getClass());
		List<Field> attributes = UtilsDAO.getAttributesWithoutId(model);
		String query = this.buildInsert(tablename, attributes);
		
		return this.executeUpdate(model, query, attributes);
		
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
	
	protected int executeUpdate(BaseModel model, String query, List<Field> attributes) throws Exception {
		
		int result = -1;
		
		PreparedStatement stm = this.connection.prepareStatement(query);
		for(int i = 0, size = attributes.size(); i < size; i++) {
			
			Object value = this.getValueOf(model, attributes.get(i));
			stm.setObject((i+1), value);
			
		} result = stm.executeUpdate();
		
		return result;
		
	}
	
	protected int simple_save(BaseModel model) throws Exception{
		
		String tablename = UtilsDAO.getTableName(model.getClass());
		List<Field> attributes = UtilsDAO.getAttributes(model);
		String query = this.buildInsert(tablename, attributes);
		
		return this.executeUpdate(model, query, attributes);
		
	}

	@Override
	public int save(BaseModel model) throws Exception {
		
		int result = -1;
		
		this.connection.setAutoCommit(false);
		if(this.isAutoIncrement())
			result = this.autoIncrement_save(model);
		else
			result = this.simple_save(model);
		this.connection.commit();
		
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
	
	protected String buildUpdate(String tablename, List<Field> attributes, Integer id) {
		
		String query1 = "UPDATE " + tablename + " ";
		String query2 = this.prepareUpdate(attributes);
		String query3 = " WHERE id = " + id;
		
		return query1 + query2 + query3;
		
	}

	@Override
	public int update(BaseModel model) throws Exception {

		String tablename = UtilsDAO.getTableName(model.getClass());
		List<Field> attributes = UtilsDAO.getAttributesWithoutId(model);
		String query = this.buildUpdate(tablename, attributes, model.getId());
		
		return this.executeUpdate(model, query, attributes);
		
	}
	
	protected int generic_delete(GenericCondition condition) throws Exception {
		
		String query = "DELETE FROM " + UtilsDAO.getTableName(condition.getModelClass());
		if(condition.getId() == null)
			query += condition.getQuery();
		else
			query += " WHERE id = " + condition.getId();
		PreparedStatement stm = this.connection.prepareStatement(query);
		
		return stm.executeUpdate();
		
	}

	@Override
	public int delete(TableCondition condition) throws Exception {
		
		int result = -1;
		
		if(condition instanceof GenericCondition) {
			
			GenericCondition cond = (GenericCondition)condition;
			result = this.generic_delete(cond);
		
		} else {
			
			throw new DaoException("You must use generic condition with this generic DAO !");
			
		} return result;
		
	}

	@Override
	public BaseModel findById(TableCondition condition) throws Exception {
		
		BaseModel result = null;
		
		condition.setQuery("WHERE id = " + condition.getId());
		List<BaseModel> models = this.findAll(condition);
		if(models.size() == 1)
			result = models.get(0);
		
		return result;
		
	}
	
	protected BaseModel map(Class<?> modelClass, ResultSet reader) throws Exception {
		
		BaseModel result = (BaseModel)modelClass.newInstance();
		
		List<Field> attributes = UtilsDAO.getAttributes(result);
		for(Field f : attributes) {
			
			Method tmp = UtilsDAO.getSetter(result, f.getName());
			if(tmp != null) {
				Object[] params = { reader.getObject(f.getName()) };
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
	
	protected List<BaseModel> generic_findAll(GenericCondition condition) throws Exception{
		
		String query = "SELECT * FROM " + UtilsDAO.getTableName(condition.getModelClass()) + condition.getQuery();
		PreparedStatement stm = this.connection.prepareStatement(query);
		
		return this.mapAll(condition.getModelClass(), stm);
		
	}

	@Override
	public List<BaseModel> findAll(TableCondition condition) throws Exception {
		
		List<BaseModel> result = new ArrayList<>();
		
		if(condition instanceof GenericCondition) {
			
			GenericCondition cond = (GenericCondition)condition;
			result = this.generic_findAll(cond);
		
		} else {
			
			throw new DaoException("You must use generic condition with this generic DAO !");
			
		} return result;
		
	}

	@Override
	public List<BaseModel> findAll(int page, int row, TableCondition condition) throws Exception {
		
		int offset = (page - 1) * row;
		condition.setQuery(condition.getQuery() + " LIMIT " + row + " OFFSET " + offset);
		return this.findAll(condition);
		
	}

}
