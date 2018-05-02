package gtt.model;

import gtt.annotation.TableAttr;

public abstract class BaseModel {
	
	// ATTRIBUTES :
	
	@TableAttr(name = "id")
	protected Integer id;
	
	// METHODS :

	public Integer getId() { return id; }

	public void setId(Integer id) throws ModelException {
		if(id > 0)
			this.id = id;
		else
			throw new ModelException("Invalid value on ID : " + id + " !");
	}

}
