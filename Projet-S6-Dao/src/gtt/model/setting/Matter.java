package gtt.model.setting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import gtt.annotation.DbTable;
import gtt.annotation.TableAttr;
import gtt.model.BaseModel;
import gtt.model.ModelException;

@Entity
@Table(name = "Matter")
@DbTable(name = "Matter")
public class Matter extends BaseModel{
	
	// ATTRIBUTES :
	
	@Column(name = "contest")
	@TableAttr(name = "contest")
	private Integer contest;
	
	@Column(name = "description")
	@TableAttr(name = "description")
	private String description;
	
	@Column(name = "coefficient")
	@TableAttr(name = "coefficient")
	private Integer coefficient;
	
	@Column(name = "average")
	@TableAttr(name = "average")
	private Float average;
	
	@Column(name = "datetimeBegin")
	@TableAttr(name = "datetimeBegin")
	private String datetimeBegin;
	
	@Column(name = "datetimeEnd")
	@TableAttr(name = "datetimeEnd")
	private String datetimeEnd;
	
	// CONSTRUCTORS :
	
	public Matter() {}
	
	public Matter(Integer contest, String description, Integer coefficient, Float average, String datetimeBegin, String datetimeEnd) throws ModelException{
		this.setContest(contest);
		this.setDescription(description);
		this.setCoefficient(coefficient);
		this.setAverage(average);
		this.setDatetimeBegin(datetimeBegin);
		this.setDatetimeEnd(datetimeEnd);
	}
	
	public Matter(Integer id, Integer contest, String description, Integer coefficient, Float average,  String datetimeBegin, String datetimeEnd) throws ModelException{
		this.setId(id);
		this.setContest(contest);
		this.setDescription(description);
		this.setCoefficient(coefficient);
		this.setAverage(average);
		this.setDatetimeBegin(datetimeBegin);
		this.setDatetimeEnd(datetimeEnd);
	}
	
	// METHODS :
		
	public String getDatetimeBegin() {
		return datetimeBegin;
	}

	public void setDatetimeBegin(String datetimeBegin) {
		this.datetimeBegin = datetimeBegin;
	}

	public String getDatetimeEnd() {
		return datetimeEnd;
	}

	public void setDatetimeEnd(String datetimeEnd) {
		this.datetimeEnd = datetimeEnd;
	}
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
	@Override
	public String toString() {
		return "Matter [contest=" + contest + ", description=" + description + ", coefficient=" + coefficient
				+ ", average=" + average + ", datetimeBegin=" + datetimeBegin + ", datetimeEnd=" + datetimeEnd + ", id="
				+ id + "]";
	}
	
	
}
