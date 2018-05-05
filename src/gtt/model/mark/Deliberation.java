package gtt.model.mark;

import gtt.model.BaseModel;
import gtt.model.ModelException;

public class Deliberation extends BaseModel{
	private Integer center;
	private Integer matter;
	private float markValue;
	
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
	public Integer getCenter() {
		return this.center;
	}
	public Integer getMatter() {
		return this.matter;
	}
	public float getMarkValue() {
		return this.markValue;
	}
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
	@Override
	public String toString() {
		return "Deliberation [center=" + center + ", matter=" + matter + ", markValue=" + markValue + ", id=" + id
				+ "]";
	}
}
