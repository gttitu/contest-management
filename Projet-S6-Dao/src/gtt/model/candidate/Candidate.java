package gtt.model.candidate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import gtt.annotation.DbTable;
import gtt.annotation.TableAttr;
import gtt.model.BaseModel;
import gtt.model.ModelException;

@Entity
@Table(name = "Candidate")
@DbTable(name = "Candidate")
public class Candidate extends BaseModel {
	
	// ATTRIBUTES :
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "idCenter")
	@TableAttr(name = "idCenter")
	private Integer center;
	
	// CONSTRUCTS :
	
	public Candidate() {}
	
	public Candidate(Integer center) throws ModelException {
		
		this.setCenter(center);
		
	}
	
	public Candidate(Integer id, Integer center) throws ModelException {
		
		this.setId(id);
		this.setCenter(center);
		
	}
	
	// GETTERS AND SETTERS :
	
	@Override public Integer getId() { return this.id; }

	@Override 
	public void setId(Integer id) throws ModelException {
		if(id > 0)
			this.id = id;
		else
			throw new ModelException("Invalid value on ID : " + id + " !");
	}
	
	public Integer getCenter() {
		return center;
	}

	public void setCenter(Integer center) throws ModelException {
		if(center > 0)
			this.center = center;
		else
			throw new ModelException("Invalid value on Center : " + center + " !");
	}
	
	// METHODS :
	
	@Override
	public String toString() {
		return "Candidate [center=" + center + ", id=" + id + "]";
	}

	@Override
	public void copy(BaseModel toCopy) throws Exception {
		
		if(toCopy instanceof Candidate) {
			
			Candidate candidate = (Candidate)toCopy;
			this.setId(candidate.getId());
			this.setCenter(candidate.getCenter());
			
		} else throw new Exception("This model to copy is not instanciate with the correct class !");
		
	}
	
	// STATIC METHODS :
	
	

}
