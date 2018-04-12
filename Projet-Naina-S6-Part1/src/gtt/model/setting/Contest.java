package gtt.model.setting;

import gtt.model.*;

public class Contest extends BaseModel{
	
	// ATTRIBUTES :
	
	private String description;
	
	// CONSTRUCTORS :
	
	public Contest(String description) throws ModelException{
		this.setDescription(description);
	}

	public Contest(Integer id, String description) throws ModelException{
		this.setId(id);
		this.setDescription(description);
	}

	// METHODS :
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
