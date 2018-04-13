package gtt.model.setting;

import gtt.model.*;

public class Contest extends BaseModel{
	
	// ATTRIBUTES :
	
	private String description;
	private boolean finished;
	
	// CONSTRUCTORS :
	
	public Contest(String description) throws ModelException{
		this.setDescription(description);
		this.setFinished(false);
	}

	public Contest(Integer id, String description, boolean finished) throws ModelException{
		this.setId(id);
		this.setDescription(description);
		this.setFinished(finished);
	}

	// METHODS :
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	

}
