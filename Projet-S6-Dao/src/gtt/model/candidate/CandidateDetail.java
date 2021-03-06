package gtt.model.candidate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import gtt.annotation.DbTable;
import gtt.annotation.ForFullText;
import gtt.annotation.TableAttr;
import gtt.model.BaseModel;
import gtt.model.ModelException;

@Entity
@Table(name = "CandidateDetail")
@DbTable(name = "CandidateDetail")
public class CandidateDetail extends BaseModel {
	
	// ATTRIBUTES :
	
	@Column(name = "idCandidate")
	@TableAttr(name = "idCandidate")
	private Integer candidate;
	
	@Column(name = "firstname")
	@ForFullText
	@TableAttr(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	@ForFullText
	@TableAttr(name = "lastname")
	private String lastname;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "gender")
	private Integer gender;
	
	// CONSTRUCTS :
	
	public CandidateDetail() {}
	
	public CandidateDetail(Integer candidate, String firstname, String lastname, Integer age, Integer gender) throws ModelException {
		
		this.setCandidate(candidate);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setAge(age);
		this.setGender(gender);
		
	}
	
	public CandidateDetail(Integer id, Integer candidate, String firstname, String lastname, Integer age, Integer gender) throws ModelException {
		
		this.setId(id);
		this.setCandidate(candidate);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setAge(age);
		this.setGender(gender);
		
	}

	// GETTERS AND SETTERS :
	
	public Integer getCandidate() {
		return candidate;
	}
	
	public void setCandidate(Integer candidate) throws ModelException {
		if(candidate > 0)
			this.candidate = candidate;
		else
			throw new ModelException("Invalid value on Candidate : " + candidate + " !");
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) throws ModelException {
		if(age > 0)
			this.age = age;
		else
			throw new ModelException("Invalid value on Age : " + age + " !");
	}
	
	public Integer getGender() {
		return gender;
	}
	
	public void setGender(Integer gender) throws ModelException {
		if(gender == 0 || gender == 1)
			this.gender = gender;
		else
			throw new ModelException("Invalid value on Gender : " + gender + " !");
	}
	
	// METHODS :
	
	@Override
	public String toString() {
		return "CandidateDetail [candidate=" + candidate + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", age=" + age + ", gender=" + gender + ", id=" + id + "]";
	}
	
	// STATIC METHODS :
	
	

}
