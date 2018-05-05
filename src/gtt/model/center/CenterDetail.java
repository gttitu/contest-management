package gtt.model.center;

import gtt.model.BaseModel;
import gtt.model.ModelException;

public class CenterDetail  extends BaseModel {
	
	// ATTRIBUTES :
	
	private Integer center;
	private Integer nbMen;
	private Integer nbWomen;
	private Integer minAge;
	private Integer maxAge;
	
	// CONSTRUCTORS :
	public CenterDetail() {}
	
	public CenterDetail(Integer center, Integer nbMen, Integer nbWomen, Integer minAge, Integer maxAge) throws ModelException {
		this.setCenter(center);
		this.setNbMen(nbMen);
		this.setNbWomen(nbWomen);
		this.setMinAge(minAge);
		this.setMaxAge(maxAge);
	}
	
	public CenterDetail(Integer id, Integer center, Integer nbMen, Integer nbWomen, Integer minAge, Integer maxAge) throws ModelException {
		this.setId(id);
		this.setCenter(center);
		this.setNbMen(nbMen);
		this.setNbWomen(nbWomen);
		this.setMinAge(minAge);
		this.setMaxAge(maxAge);
	}
	
	// GETTERS AND SETTERS :
	
	public Integer getCenter() {
		return center;
	}
	public void setCenter(Integer center) throws ModelException {
		if(center > 0)
			this.center = center;
		else
			throw new ModelException("Invalid value on Center : " + center + " !");
	}
	public Integer getNbMen() {
		return nbMen;
	}
	public void setNbMen(Integer nbMen) throws ModelException {
		if(nbMen >= 0)
			this.nbMen = nbMen;
		else
			throw new ModelException("Invalid value on nbMen : " + nbMen + " !");
	}
	public Integer getNbWomen() {
		return nbWomen;
	}
	public void setNbWomen(Integer nbWomen) throws ModelException {
		if(nbMen >= 0)
			this.nbWomen = nbWomen;
		else
			throw new ModelException("Invalid value on nbWomen : " + nbWomen + " !");
	}
	public Integer getMinAge() {
		return minAge;
	}
	public void setMinAge(Integer minAge) throws ModelException {
		if(minAge >= 0)
			this.minAge = minAge;
		else
			throw new ModelException("Invalid value on minAge : " + minAge + " !");
	}
	public Integer getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(Integer maxAge) throws ModelException {
		if(maxAge >= 0)
			this.maxAge = maxAge;
		else
			throw new ModelException("Invalid value on maxAge : " + maxAge + " !");
	}

	@Override
	public String toString() {
		return "CenterDetail [center=" + center + ", nbMen=" + nbMen + ", nbWomen=" + nbWomen + ", minAge=" + minAge
				+ ", maxAge=" + maxAge + ", id=" + id + "]";
	}
	
	
	
}
