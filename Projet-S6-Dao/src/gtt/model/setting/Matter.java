package gtt.model.setting;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import gtt.annotation.TableAttr;
import gtt.model.BaseModel;
import gtt.model.ModelException;

public class Matter extends BaseModel{
	
	// ATTRIBUTES :
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
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
	
	@Override public Integer getId() { return this.id; }

	@Override 
	public void setId(Integer id) throws ModelException {
		if(id > 0)
			this.id = id;
		else
			throw new ModelException("Invalid value on ID : " + id + " !");
	}
		
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

	@Override
	public void copy(BaseModel toCopy) throws Exception {
		
		if(toCopy instanceof Matter) {
			
			Matter matter = (Matter) toCopy;
			this.setId(matter.getId());
			this.setContest(matter.getContest());
			this.setDescription(matter.getDescription());
			this.setDatetimeBegin(matter.getDatetimeBegin());
			this.setDatetimeEnd(matter.getDatetimeEnd());
			this.setAverage(matter.getAverage());
			this.setCoefficient(matter.getCoefficient());
			
		} else throw new Exception("This model to copy is not instanciate with the correct class !");
		
	}
	
	
}
