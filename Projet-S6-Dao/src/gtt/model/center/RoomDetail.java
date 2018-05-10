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
@Table(name = "RoomDetail")
@DbTable(name = "RoomDetail")
public class RoomDetail extends BaseModel{
	
	// ATTRIBUTES :
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "idRoom")
	@TableAttr(name = "idRoom")
	private Integer room;
	
	@Column(name = "idCandidate")
	@TableAttr(name = "idCandidate")
	private Integer candidate;
	
	// CONSTRUCTS :
	
	public RoomDetail() {}
	
	public RoomDetail(Integer room, Integer candidate) throws ModelException {
		
		this.setRoom(room);
		this.setCandidate(candidate);
		
	}
	
	public RoomDetail(Integer id, Integer room, Integer candidate) throws ModelException {
		
		this.setId(id);
		this.setRoom(room);
		this.setCandidate(candidate);
		
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
	
	public Integer getRoom() {
		return room;
	}

	public void setRoom(Integer room) throws ModelException {
		if(room > 0)
			this.room = room;
		else
			throw new ModelException("Invalid value on Room : " + room + " !");
	}
	
	public Integer getCandidate() {
		return candidate;
	}
	
	public void setCandidate(Integer candidate) throws ModelException {
		if(candidate > 0)
			this.candidate = candidate;
		else
			throw new ModelException("Invalid value on Candidate : " + candidate + " !");
	}
	
	// METHODS :
	
	@Override
	public String toString() {
		return "RoomDetail [room=" + room + ", candidate=" + candidate + ", id=" + id + "]";
	}

	@Override
	public void copy(BaseModel toCopy) {
		// TODO Auto-generated method stub
		
	}
	
	// STATIC METHODS :
	
	

}
