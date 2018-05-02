package gtt.model.center;

import gtt.model.BaseModel;
import gtt.model.ModelException;

public class Room extends BaseModel {
	
	// ATTRIBUTES :
	
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
		
		
		
		// STATIC METHODS :
		
		

}
