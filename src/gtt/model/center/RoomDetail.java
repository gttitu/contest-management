package gtt.model.center;

import gtt.annotation.DbTable;
import gtt.annotation.TableAttr;
import gtt.model.BaseModel;
import gtt.model.ModelException;

@DbTable(name = "RoomDetail")
public class RoomDetail extends BaseModel{
	
	// ATTRIBUTES :
	
	@TableAttr(name = "idRoom")
	private Integer room;
	
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
