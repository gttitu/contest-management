package gtt.model.center;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import gtt.annotation.DbTable;
import gtt.annotation.TableAttr;
import gtt.model.BaseModel;
import gtt.model.ModelException;

@Entity
@Table(name = "Room")
@DbTable(name = "Room")
public class Room extends BaseModel {
	
	// ATTRIBUTES :
		
	@Column(name = "idCenter")
	@TableAttr(name = "idCenter")
	private Integer center;
	
	// CONSTRUCTS :
	
	public Room() {}
	
	public Room(Integer center) throws ModelException {
		
		this.setCenter(center);
		
	}
	
	public Room(Integer id, Integer center) throws ModelException {
		
		this.setId(id);
		this.setCenter(center);
		
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
	
	// METHODS :
	
	@Override
	public String toString() {
		return "Room [center=" + center + ", id=" + id + "]";
	}
	
	// STATIC METHODS :
	
	

}
