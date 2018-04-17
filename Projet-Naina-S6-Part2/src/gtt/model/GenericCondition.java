package gtt.model;

public class GenericCondition extends TableCondition {
	
	// ATTRIBUTES :
	
	protected Class<?> modelClass;
	
	// CONSTRUCTS :
	
	public GenericCondition() {}
	
	public GenericCondition(Class<?> modelClass, Integer id) throws ModelException {
		
		super(id);
		this.setModelClass(modelClass);
		
	}
	
	public GenericCondition(Class<?> modelClass, String query) {
		
		super(query);
		this.setModelClass(modelClass);
		
	}
	
	// GETTERS AND SETTERS :
	
	public Class<?> getModelClass() {
		return modelClass;
	}

	public void setModelClass(Class<?> modelClass) {
		this.modelClass = modelClass;
	}
	
	// METHODS :
	
	
	
	// STATIC METHODS :
	
	

}
