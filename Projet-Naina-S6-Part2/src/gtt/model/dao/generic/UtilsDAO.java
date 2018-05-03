package gtt.model.dao.generic;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gtt.annotation.CondOperator;
import gtt.annotation.DbTable;
import gtt.annotation.ForFullText;
import gtt.annotation.NotTableAttr;
import gtt.annotation.TableAttr;
import gtt.model.BaseModel;

public class UtilsDAO {
	
	// STATIC METHODS :
	
	public static String getCondOperand(Field attribute) {
		
		if(attribute.isAnnotationPresent(CondOperator.class))
			return attribute.getAnnotation(CondOperator.class).term();
		else
			return "=";
		
	}
	
	public static Object getValueOf(BaseModel model, Field attribute) throws Exception {
		
		Object result = null;
		
		Method getter = getGetter(model, attribute.getName());
		Object[] params = null;
		result = getter.invoke(model, params);
		
		return result;
		
	}
	
	protected static List<Field> sort(List<Field> fields){
		
		List<Field> result = new ArrayList<>();
		
		for(Field f : fields) {
			
			if(!f.isAnnotationPresent(NotTableAttr.class))
				result.add(f);
			
		} return result;
		
	}
	
	protected static List<Field> concatFields(List<Field> fields1, List<Field> fields2) {
		
		List<Field> result = new ArrayList<>();
		
		for(Field f : fields1) {
			
			result.add(f);
			
		} for(Field f : fields2) {
			
			result.add(f);
			
		} return result;
		
	}
	
	public static List<Field> getAttributes(BaseModel model){
		
		List<Field> result = new ArrayList<>();
		
		Class<?> cl = model.getClass();
		while(!cl.getSimpleName().equals("BaseModel")) {
			
			result = concatFields(result, Arrays.asList(cl.getDeclaredFields()));
			cl = cl.getSuperclass();
			
		} result = concatFields(result, Arrays.asList(cl.getDeclaredFields()));
		
		return sort(result);
		
	}
	
	public static List<Field> getAttributesWithoutId(BaseModel model){
		
		List<Field> result = new ArrayList<>();
		
		List<Field> tmp = getAttributes(model);
		for(Field f : tmp) {
			
			if(!f.getName().equals("id"))
				result.add(f);
			
		} return result;
		
	}
	
	public static Method getMethod(BaseModel model, String name){
		
		Method result = null;
		
		Class<?> cl = model.getClass();
		List<Method> tmp = Arrays.asList(cl.getMethods());
		for(Method m : tmp) {
			
			if(m.getName().equals(name)) {
				result = m;
				break;
			}
			
		} return result;
		
	}
	
	protected static String capitalize(String str) {
		
		return str.substring(0, 1).toUpperCase() + str.substring(1);
		
	}
	
	public static Method getGetter(BaseModel model, String fieldname){
		
		Method result = getMethod(model, "get" + capitalize(fieldname));
		
		return result;
		
	}
	
	public static Method getSetter(BaseModel model, String fieldname){
		
		Method result = getMethod(model, "set" + capitalize(fieldname));
		
		return result;
		
	}
	
	public static String getAttrName(Field attribute) {
		
		String result = attribute.getName();
		
		if(attribute.isAnnotationPresent(TableAttr.class)) {
			
			TableAttr attr = attribute.getAnnotation(TableAttr.class);
			result = attr.name();
			
		} return result;
		
	}
	
	public static String getTableName(Class<?> modelClass) {
		
		String result = modelClass.getSimpleName();
		
		if(modelClass.isAnnotationPresent(DbTable.class)) {
			
			DbTable table = modelClass.getAnnotation(DbTable.class);
			result = table.name();
			
		} return result;
		
	}
	
	public static List<Field> getAttrNotNull(BaseModel model, List<Field> attributes) throws Exception{
		
		List<Field> result = new ArrayList<>();
		
		for(Field f : attributes) {
			
			Object tmp = getValueOf(model, f);
			if(tmp != null)
				result.add(f);
			
		} return result;
		
	}
	
	public static List<Field> getAttrForFullText(Class<?> modelClass) throws Exception{
		
		List<Field> result = new ArrayList<>();
		
		List<Field> attrs = UtilsDAO.getAttributesWithoutId((BaseModel)modelClass.newInstance());
		for(Field f : attrs) {
			
			if(f.isAnnotationPresent(ForFullText.class))
				result.add(f);
			
		} return result;
		
	}

}
