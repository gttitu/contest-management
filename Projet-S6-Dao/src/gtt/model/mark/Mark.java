package gtt.model.mark;

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
@Table(name = "Mark")
@DbTable(name = "Mark")
public class Mark extends BaseModel{
	
	// ATTRIBUTES :
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "candidate")
	@TableAttr(name = "candidate")
	private Integer candidate;
	
	@Column(name = "matter")
	@TableAttr(name = "matter")
	private Integer matter;
	
	@Column(name = "markValue")
	@TableAttr(name = "markValue")
	private Float markValue;
	
	// CONSTRUCTS :
	
	public Mark() {}
	
	public Mark(Integer candidate, Integer matter, Float markValue)throws ModelException{
		
		this.setCandidate(candidate);
		this.setMatter(matter);
		this.setMarkValue(markValue);
		
	}
	
	public Mark(Integer id, Integer candidate, Integer matter, Float markValue) throws ModelException{
		
		this.setId(id);
		this.setCandidate(candidate);
		this.setMatter(matter);
		this.setMarkValue(markValue);
		
	}
	
	// GETTERS AND SETTERS :
	
	@Override
	public Integer getId() { return id; }

	@Override
	public void setId(Integer id) throws ModelException {
		
		if(id > 0)
			this.id = id;
		else
			throw new ModelException("Invalid value on ID : " + id + " !");
		
	}
	
	public Integer getCandidate() {
		
		return this.candidate;
		
	}

	public void setCandidate(Integer candidate) throws ModelException {
		
		if(candidate > 0)
			this.candidate = candidate;
		else
			throw new ModelException("Invalid value on IDContest : " + candidate + " !");
		
	}
	
	public Integer getMatter() {
		
		return this.matter;
		
	}
	
	public void setMatter(Integer matter) throws ModelException {
		
		if(matter > 0)
			this.matter = matter;
		else
			throw new ModelException("Invalid value on IDContest : " + matter + " !");
		
	}
	
	public Float getMarkValue() {
		
		return markValue;
		
	}
	
	public void setMarkValue(Float markValue)  throws ModelException {
		
		if(markValue >= 0)
			this.markValue = markValue;
		else
			throw new ModelException("Invalid value on Average : " + markValue + " !");
		
	}
	
	// METHODS :
	
	@Override
	public String toString() {
		return "Mark [id=" + id + ", candidate=" + candidate + ", matter=" + matter + ", markValue=" + markValue + "]";
	}

	@Override
	public void copy(BaseModel toCopy) throws ModelException {
		
		if(toCopy instanceof Mark) {
			
			Mark cpy = (Mark)toCopy;
			this.setCandidate(cpy.getCandidate());
			this.setMarkValue(cpy.getMarkValue());
			this.setMatter(cpy.getMatter());
			
		} else throw new ModelException("The object to copy on this is not a Mark !");
		
	}

}
