package gtt.test.mybernate;

import gtt.dao.mybernate.Mybernate;
import gtt.model.mark.Deliberation;

public class DemoDelete {

	public static void main(String[] args) {
		
		try {
			
			delete();
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
		}

	}
	
	static void delete() throws Exception {
		
		Deliberation d = new Deliberation(); d.setId(5);
		
		Mybernate mb = new Mybernate();
		mb.delete(d);
		
	}

}
