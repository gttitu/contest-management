package gtt.model.center;

import gtt.model.BaseModel;
import gtt.model.ModelException;

public class RoomDetail extends BaseModel{
	
	// ATTRIBUTES :
	
	private Integer room;
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
	
	
	
	// STATIC METHODS :
	
	

}
