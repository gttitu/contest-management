package gtt.model.center;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import gtt.annotation.DbTable;
import gtt.annotation.TableAttr;
import gtt.model.BaseModel;
import gtt.model.ModelException;

@Entity
@Table(name = "Center")
@DbTable(name = "Center")
public class Center extends BaseModel {
	
	// ATTRIBUTES :
	
	@Column(name = "contest")
	@TableAttr(name = "contest")
	private Integer contest;
	
	@Column(name = "description")
	@TableAttr(name = "description")
	private String description;
	
	@Column(name = "location")
	@TableAttr(name = "location")
	private String location;
	
	@Column(name = "nbAllowable")
	@TableAttr(name = "nbAllowable")
	private Integer nbAllowable;
	
	//CONSTRUCTORS :
	
	public Center() {}
	
	public Center(Integer contest, String description, String location, Integer nbAllowable)  throws ModelException{
		this.setContest(contest);
		this.setDescription(description);
		this.setLocation(location);
		this.setNbAllowable(nbAllowable);
	}
	
	public Center(Integer id, Integer contest, String description, String location, Integer nbAllowable)  throws ModelException{
		this.setId(id);
		this.setContest(contest);
		this.setDescription(description);
		this.setLocation(location);
		this.setNbAllowable(nbAllowable);
	}
	
	// METHODS :
	
	public Integer getContest() {
		return contest;
	}
	public void setContest(Integer contest) throws ModelException {
		if(contest > 0)
			this.contest = contest;
		else
			throw new ModelException("Invalid value on IDContest : " + contest + " !");
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getNbAllowable() {
		return nbAllowable;
	}
	public void setNbAllowable(Integer nbAllowable) throws ModelException {
		if(nbAllowable > 0)
			this.nbAllowable = nbAllowable;
		else
			throw new ModelException("Invalid value on nbAllowable : " + nbAllowable + " !");
	}

	@Override
	public String toString() {
		return "Center [contest=" + contest + ", description=" + description + ", location=" + location
				+ ", nbAllowable=" + nbAllowable + ", id=" + id + "]";
	}
		
}
