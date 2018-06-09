package gtt.model.mark;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import gtt.annotation.DbTable;
import gtt.annotation.TableAttr;
import gtt.model.BaseModel;
import gtt.model.ModelException;

@Entity
@Table(name = "Deliberation")
@DbTable(name = "Deliberation")
public class Deliberation extends BaseModel{
	
	// ATTRIBUTES :
	
	@Column(name = "center")
	@TableAttr(name = "center")
	private Integer center;
	
	@Column(name = "matter")
	@TableAttr(name = "matter")
	private Integer matter;
	
	@Column(name = "markValue")
	@TableAttr(name = "markValue")
	private float markValue;
	
	// CONSTRUCTS :
	
	public Deliberation() {}
	
	public Deliberation(Integer center, Integer matter, float markValue)throws ModelException {
		
		this.setCenter(center);
		this.setMatter(matter);
		this.setMarkValue(markValue);
		
	}
	
	public Deliberation(Integer id, Integer center, Integer matter, float markValue)throws ModelException {
		
		this.setId(id);
		this.setCenter(center);
		this.setMatter(matter);
		this.setMarkValue(markValue);
		
	}
	
	// GETTERS AND SETTERS :
	
	public Integer getCenter() { return this.center; }
	
	public Integer getMatter() { return this.matter; }
	
	public float getMarkValue() { return this.markValue; }
	
	public void setCenter(Integer center)throws ModelException{
		if(center > 0)
			this.center = center;
		else
			throw new ModelException("Invalid value on Center : " + center + " !");
	}
	
	public void setMatter(Integer matter)throws ModelException{
		if(matter > 0)
			this.matter = matter;
		else
			throw new ModelException("Invalid value on Center : " + matter + " !");
	}
	
	public void setMarkValue(float markValue)throws ModelException {
		if(markValue > 0)
			this.markValue = markValue;
		else
			throw new ModelException("Invalid value on Average : " + markValue + " !");
	}
	
	// METHODS :
	
	@Override
	public String toString() {
		return "Deliberation [id=" + id + ", center=" + center + ", matter=" + matter + ", markValue=" + markValue
				+ "]";
	}

}
