package gtt.model.setting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import gtt.annotation.DbTable;
import gtt.annotation.TableAttr;
import gtt.model.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "Contest")
@DbTable(name = "Contest")
public class Contest extends BaseModel implements java.io.Serializable{
	
	// ATTRIBUTES :

	@Column(name = "description")
	@TableAttr(name = "description")
	private String description;
	
	@Column(name = "finished")
	@TableAttr(name = "finished")
	private boolean finished;
	
	@Column(name = "dateBegin")
	@TableAttr(name = "dateBegin")
	private String dateBegin;
	
	@Column(name = "dateEnd")
	@TableAttr(name = "dateEnd")
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
