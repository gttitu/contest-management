package gtt.model;

public abstract class BaseModel {
	
	// ATTRIBUTES :
	protected Integer id;
	
	// METHODS :

	public abstract Integer getId();

	public abstract void setId(Integer id) throws ModelException;

}
