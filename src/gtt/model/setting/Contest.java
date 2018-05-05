package gtt.model.setting;

import gtt.model.*;

public class Contest extends BaseModel{
	
	// ATTRIBUTES :
	
	private String description;
	private boolean finished;
	private String dateBegin;
	private String dateEnd;
	
	// CONSTRUCTORS :
	
	public Contest(){}

	public Contest(String description, String dateBegin, String dateFinished) throws ModelException{
		this.setDescription(description);
		this.setFinished(false);
		this.setDateBegin(dateBegin);
		this.setDateEnd(dateFinished);
	}

	public Contest(Integer id, String description, boolean finished, String dateBegin, String dateFinished) throws ModelException{
		this.setId(id);
		this.setDescription(description);
		this.setFinished(finished);
		this.setFinished(false);
		this.setDateBegin(dateBegin);
		this.setDateEnd(dateFinished);
	}

	// GETTERS AND SETTERS :
	
	public String getDescription() {
		return description;
	}

	public String getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(String dateBegin) {
		this.dateBegin = dateBegin;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
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

	@Override
	public String toString() {
		return "Contest [description=" + description + ", finished=" + finished + ", dateBegin=" + dateBegin
				+ ", dateEnd=" + dateEnd + ", id=" + id + "]";
	}
	
	
	

}
