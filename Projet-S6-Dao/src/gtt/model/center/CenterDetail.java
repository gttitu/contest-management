package gtt.model.center;

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
@Table(name = "CenterDetail")
@DbTable(name = "CenterDetail")
public class CenterDetail  extends BaseModel {
	
	// ATTRIBUTES :
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "center")
	@TableAttr(name = "center")
	private Integer center;
	
	@Column(name = "nbMen")
	@TableAttr(name = "nbMen")
	private Integer nbMen;
	
	@Column(name = "nbWomen")
	@TableAttr(name = "nbWomen")
	private Integer nbWomen;
	
	@Column(name = "minAge")
	@TableAttr(name = "minAge")
	private Integer minAge;
	
	@Column(name = "maxAge")
	@TableAttr(name = "maxAge")
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

	@Override
	public void copy(BaseModel toCopy) throws Exception {
		
		if(toCopy instanceof CenterDetail) {
			
			CenterDetail detail = (CenterDetail)toCopy;
			this.setId(detail.getId());
			this.setCenter(detail.getCenter());
			this.setNbMen(detail.getNbMen());
			this.setNbWomen(detail.getNbWomen());
			this.setMinAge(detail.getMinAge());
			this.setMaxAge(detail.getMaxAge());
			
		} else throw new Exception("This model to copy is not instanciate with the correct class !");
		
	}
	
	
	
}
