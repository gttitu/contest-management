package gtt.model.dao.generic;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gtt.model.BaseModel;
import gtt.model.dao.ConnUtils;
import gtt.model.dao.InterfaceDAO;
import gtt.model.dao.UtilsDAO;

public class GenericDAO implements InterfaceDAO {
	
	// ATTRIBUTES :
	
	private boolean autoIncrement;
	
	// CONSTRUCTS :
	
	public GenericDAO(boolean autoIncrement) {
		
		this.setAutoIncrement(autoIncrement);
		
	}
	
	// GETTERS AND SETTERS :
	
	
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
			result += UtilsDAO.getAttrName(f);
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
	
	protected void loopForExecuteSave(List<Field> attributes, BaseModel model, PreparedStatement stm) throws Exception {
		
		for(int i = 0, size = attributes.size(); i < size; i++) {
			
			Object value = this.getValueOf(model, attributes.get(i));
			stm.setObject((i+1), value);
			
		}
		
	}
	
	protected int executeSave(BaseModel model, String query, List<Field> attributes) throws Exception{
		
		int result = -1;
		Connection connection = null;
		PreparedStatement stm = null;
		
		try {
			
			connection = ConnUtils.get();
			stm = connection.prepareStatement(query);
			this.loopForExecuteSave(attributes, model, stm);
			result = stm.executeUpdate();
			
		} catch(Exception ex) {
			
			throw ex;
			
		} finally{
			
			if(stm != null) stm.close();
			if(connection != null) connection.close();
			
		} return result;
		
	}
	
	protected void loopForExecuteUpdate(List<Field> attributes, BaseModel model, PreparedStatement stm) throws Exception{
		
		for(int i = 0, size = attributes.size(); i < size; i++) {
			
			Object value = this.getValueOf(model, attributes.get(i));
			stm.setObject((i+1), value);
			
		} stm.setObject(attributes.size() + 1, model.getId());
		
	}
	
	protected int executeUpdate(BaseModel model, String query, List<Field> attributes) throws Exception {
		
		int result = -1;
		Connection connection = null;
		PreparedStatement stm = null;
		
		try {
		
			connection = ConnUtils.get();
			stm = connection.prepareStatement(query);
			this.loopForExecuteUpdate(attributes, model, stm);
			result = stm.executeUpdate();
		
		} catch(Exception ex) {
			
			throw ex;
			
		} finally {
			
			if(stm != null) stm.close();
			if(connection != null) connection.close();
			
		} return result;
		
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
			
			result += UtilsDAO.getAttrName(attributes.get(i)) + " = ?";
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
		
		int result = -1;
		String query = "DELETE FROM " + UtilsDAO.getTableName(model.getClass()) + " WHERE id = ?";
		Connection connection = null;
		PreparedStatement stm = null;
		
		try {
		
			connection = ConnUtils.get();
			stm = connection.prepareStatement(query);
			stm.setObject(1, model.getId());
			result = stm.executeUpdate();
		
		} catch(Exception ex) {
			
			throw ex;
			
		} finally {
			
			if(stm != null) stm.close();
			if(connection != null) connection.close();
			
		} return result;
		
	}
	
	protected void loopForMapById(BaseModel model, BaseModel tmp) throws Exception {
		
		List<Field> attributes = UtilsDAO.getAttributesWithoutId(model);
		for(Field f : attributes) {
			
			Method getter = UtilsDAO.getGetter(tmp, f.getName());
			Object[] params = null;
			Object returned = getter.invoke(tmp, params);
			if(returned != null) {
				
				Method setter = UtilsDAO.getSetter(model, f.getName());
				setter.invoke(model, returned);
				
			}
			
		}
		
	}
	
	protected void mapById(BaseModel model, PreparedStatement stm) throws Exception {
		
		ResultSet reader = null;
		
		try {
			
			BaseModel tmp = null;
			reader = stm.executeQuery();
			if(reader.next()) tmp = this.map(model.getClass(), reader);
			if(tmp != null) this.loopForMapById(model, tmp);
		
		} catch(Exception ex) {
			
			throw ex;
			
		} finally {
			
			if(reader != null) reader.close();
			
		}
		
	}

	@Override
	public void findById(BaseModel model) throws Exception {
		
		String query = "SELECT * FROM " + UtilsDAO.getTableName(model.getClass()) + " WHERE id = ?";
		Connection connection = null;
		PreparedStatement stm = null;
		
		try {
			
			connection = ConnUtils.get();
			stm = connection.prepareStatement(query);
			stm.setObject(1, model.getId());
			this.mapById(model, stm);
			
		} catch(Exception ex) {
			
			throw ex;
			
		} finally {
			
			if(stm != null) stm.close();
			if(connection != null) connection.close();
			
		}
		
	}
	
	protected BaseModel map(Class<?> modelClass, ResultSet reader) throws Exception {
		
		BaseModel result = (BaseModel)modelClass.newInstance();
		
		List<Field> attributes = UtilsDAO.getAttributes(result);
		for(Field f : attributes) {
			
			Method tmp = UtilsDAO.getSetter(result, f.getName());
			if(tmp != null) {
				String name = UtilsDAO.getAttrName(f);
				Object param = reader.getObject(name);
				tmp.invoke(result, param);
			}
			
		} return result;
		
	}
	
	protected void loopForMapAll(ResultSet reader, Class<?> modelClass, List<BaseModel> result) throws Exception{
		
		while(reader.next()) {
			
			BaseModel tmp = this.map(modelClass, reader);
			if(tmp != null)
				result.add(tmp);
			
		} 
		
	}
	
	protected List<BaseModel> mapAll(Class<?> modelClass, PreparedStatement stm) throws Exception{
		
		List<BaseModel> result = new ArrayList<>();
		ResultSet reader = null;
		
		try {
		
			reader = stm.executeQuery();
			this.loopForMapAll(reader, modelClass, result);
		
		} catch(Exception ex) {
			
			throw ex;
			
		} finally {
			
			if(reader != null) reader.close();
			
		} return result;
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findAll(BaseModel baseCond, String specCond) throws Exception {
		
		List<BaseModel> result = new ArrayList<>();
		String query = "SELECT * FROM " + UtilsDAO.getTableName(baseCond.getClass()) + this.buildCondition(baseCond, specCond);
		Connection connection = null;
		PreparedStatement stm = null;
		
		try {
			
			connection = ConnUtils.get();
			stm = connection.prepareStatement(query);
			result = this.mapAll(baseCond.getClass(), stm);
			
		} catch(Exception ex) {
			
			throw ex;
			
		} finally {
			
			if(stm != null) stm.close();
			if(connection != null) connection.close();
			
		} return result;
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findAll(int page, int row, BaseModel baseCond, String specCond) throws Exception {
		
		int offset = (page - 1) * row;
		if(specCond == null)
			specCond = "LIMIT " + row + " OFFSET " + offset;
		else
			specCond += " LIMIT " + row + " OFFSET " + offset;
		
		return this.findAll(baseCond, specCond);
		
	}
	
	private String getCondForMatch(Class<?> modelClass) throws Exception {
		
		String result = "";
		
		List<Field> attrs = UtilsDAO.getAttrForFullText(modelClass);
		for(int i = 0, size = attrs.size(); i < size; i++) {
			
			result += UtilsDAO.getAttrName(attrs.get(i));
			if(i < size - 1)
				result += ", ";
			
		} return result;
		
	}
	
	private List<BaseModel> executeFullText(Class<?> modelClass, String query, String keywords) throws Exception{
		
		List<BaseModel> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement stm = null;
		
		try {
			
			connection = ConnUtils.get();
			stm = connection.prepareStatement(query);
			stm.setObject(1, keywords);
			result = this.mapAll(modelClass, stm);
			
		} catch(Exception ex) {
			
			throw ex;
			
		} finally {
			
			if(stm != null) stm.close();
			if(connection != null) connection.close();
			
		} return result;
		
	}
	
	private List<BaseModel> fullTextSearch(Class<?> modelClass, String keywords) throws Exception{
		
		String cond = "", cond1 = " WHERE MATCH(", cond2 = this.getCondForMatch(modelClass), cond3 = ") AGAINST (? IN BOOLEAN MODE)";
		if(!cond2.equals(""))
			cond = cond1 + cond2 + cond3;
		String query = "SELECT * FROM " + UtilsDAO.getTableName(modelClass) + cond;
		
		return this.executeFullText(modelClass, query, keywords);
		
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List findAllByFullText(Class<?> modelClass, String keywords) throws Exception {
		
		List<BaseModel> result = new ArrayList<>();
		
		if(keywords != null) {
			
			result = this.fullTextSearch(modelClass, keywords);
			
		} return result;
		
	}
	
	// METHODS :
	
	protected String writeConditionWith(BaseModel model, Field attribute) {
		
		String result = UtilsDAO.getAttrName(attribute);
		
		result += " " + UtilsDAO.getCondOperand(attribute) + " ?";
		
		return result;
		
	}
	
	protected void loopForGetPreparedQuery(List<Field> attributes, BaseModel model, PreparedStatement stm) throws Exception{
		
		for(int i = 0, size = attributes.size(); i < size; i++) {
			
			stm.setObject(i + 1, UtilsDAO.getValueOf(model, attributes.get(i)));
			
		}
		
	}
	
	protected String getPreparedQuery(String query, BaseModel model, List<Field> attributes) throws Exception {
		
		String result = null;
		Connection connection = null;
		PreparedStatement stm = null;
		
		try {
		
			connection = ConnUtils.get();
			stm = connection.prepareStatement(query);
			this.loopForGetPreparedQuery(attributes, model, stm);
			int index = stm.toString().indexOf(": ");
			result = stm.toString().substring(index + 2);
		
		} catch(Exception ex) {
			
			throw ex;
			
		} finally {
			
			if(stm != null) stm.close();
			if(connection != null) connection.close();
			
		} return result;
		
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
