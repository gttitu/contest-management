package gtt.test.mybernate;

import gtt.dao.mybernate.Mybernate;
import gtt.model.mark.Mark;

public class DemoSave {

	public static void main(String[] args) {
		
		try {
			
			save();
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
		}

	}
	
	static void save() throws Exception{
		
		Mark m = new Mark(4, 4, (float)15.667);
		
		Mybernate mb = new Mybernate();
		mb.save(m);
		
	}

}
