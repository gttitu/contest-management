package gtt.model;

public abstract class BaseModel {
	
	// ATTRIBUTES :
	
	protected Integer id;
	
	// METHODS :

	public Integer getId() { return id; }

	public void setId(Integer id) throws ModelException {
		if(id > 0)
			this.id = id;
		else
			throw new ModelException("Invalid value on ID : " + id + " !");
	}
	
	// METHODS TO DEFINE :
	
	public abstract void copy(BaseModel toCopy) throws Exception;

}
