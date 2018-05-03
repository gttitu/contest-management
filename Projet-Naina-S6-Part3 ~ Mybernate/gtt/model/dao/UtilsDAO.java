package gtt.model.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;

import gtt.model.BaseModel;

public class UtilsDAO {
	
	// STATIC METHODS :
	
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
			
			if(f.isAnnotationPresent(Column.class))
				result.add(f);
			
		} return result;
		
	}
	
	protected static boolean containsFieldWithName(List<Field> fields, String name) {
		
		boolean result = false;
		
		for(Field f : fields) {
			
			if(f.getName().equals(name)) {
				result = true;
				break;
			}
			
		} return result;
		
	}
	
	protected static List<Field> concatFields(List<Field> fields1, List<Field> fields2) {
		
		List<Field> result = new ArrayList<>();
		
		for(Field f : fields1) {
			
			if(!containsFieldWithName(result, f.getName())) result.add(f);
			
		} for(Field f : fields2) {
			
			if(!containsFieldWithName(result, f.getName())) result.add(f);
			
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
	
	public static String getColName(Field attribute) {
		
		String result = attribute.getName();
		
		Class<?> attrClass = attribute.getType();
		if(attrClass.isAnnotationPresent(Column.class)) {
			
			Column attr = attrClass.getAnnotation(Column.class);
			result = attr.name();
			
		} return result;
		
	}
	
	public static String getTableName(Class<?> modelClass) {
		
		String result = modelClass.getSimpleName();
		
		if(modelClass.isAnnotationPresent(Table.class)) {
			
			Table table = modelClass.getAnnotation(Table.class);
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

}
