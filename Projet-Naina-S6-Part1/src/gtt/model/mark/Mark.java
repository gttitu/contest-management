package gtt.model.mark;

import gtt.model.BaseModel;
import gtt.model.ModelException;

public class Mark extends BaseModel{
	private Integer candidate;
	private Integer matter;
	float markValue;
	public Mark() {}
	public Mark(Integer candidate, Integer matter, float markValue)throws ModelException{
		this.setCandidate(candidate);
		this.setMatter(matter);
		this.setMarkValue(markValue);
	}
	public Mark(Integer id, Integer candidate, Integer matter, float markValue) throws ModelException{
		this.setId(id);
		this.setCandidate(candidate);
		this.setMatter(matter);
		this.setMarkValue(markValue);
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
		if(markValue > 0)
			this.markValue = markValue;
		else
			throw new ModelException("Invalid value on Average : " + markValue + " !");
	}
	@Override
	public String toString() {
		return "Mark [candidate=" + candidate + ", matter=" + matter + ", markValue=" + markValue + ", id=" + id + "]";
	}
}
