package gtt.model.setting;

import gtt.model.BaseModel;
import gtt.model.ModelException;

public class Matter extends BaseModel{
	
	// ATTRIBUTES :
	
	private Integer idContest;
	private String description;
	private Integer coefficient;
	private Float average;
	
	// CONSTRUCTORS :
	
	public Matter(Integer idContest, String description, Integer coefficient, Float average) throws ModelException{
		this.setIdContest(idContest);
		this.setDescription(description);
		this.setCoefficient(coefficient);
		this.setAverage(average);
	}
	
	public Matter(Integer id, Integer idContest, String description, Integer coefficient, Float average) throws ModelException{
		this.setId(id);
		this.setIdContest(idContest);
		this.setDescription(description);
		this.setCoefficient(coefficient);
		this.setAverage(average);
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
	public Integer getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(Integer coefficient) throws ModelException {
		if(coefficient > 0)
			this.coefficient = coefficient;
		else
			throw new ModelException("Invalid value on Coefficient : " + coefficient + " !");
	}
	public Float getAverage() {
		return average;
	}
	public void setAverage(Float average)  throws ModelException {
		if(average > 0)
			this.average = average;
		else
			throw new ModelException("Invalid value on Average : " + average + " !");
	}
	
	
}
