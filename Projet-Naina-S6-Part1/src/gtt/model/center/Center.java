package gtt.model.center;

import gtt.model.BaseModel;
import gtt.model.ModelException;

public class Center extends BaseModel {
	
	// ATTRIBUTES :
	
	private Integer idContest;
	private String description;
	private String location;
	private Integer nbAllowable;
	
	//CONSTRUCTORS :
	
	public Center(Integer idContest, String description, String location, Integer nbAllowable)  throws ModelException{
		this.setIdContest(idContest);
		this.setDescription(description);
		this.setLocation(location);
		this.setNbAllowable(nbAllowable);
	}
	
	public Center(Integer id, Integer idContest, String description, String location, Integer nbAllowable)  throws ModelException{
		this.setId(id);
		this.setIdContest(idContest);
		this.setDescription(description);
		this.setLocation(location);
		this.setNbAllowable(nbAllowable);
	}
	
	// METHODS :
	
	public Integer getIdContest() {
		return idContest;
	}
	public void setIdContest(Integer idContest) throws ModelException {
		if(idContest > 0)
			this.idContest = idContest;
		else
			throw new ModelException("Invalid value on IDContest : " + idContest + " !");
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
	
}

