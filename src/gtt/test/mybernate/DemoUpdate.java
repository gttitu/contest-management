package gtt.test.mybernate;

import gtt.model.dao.mybernate.Mybernate;
import gtt.model.mark.Deliberation;

public class DemoUpdate {

	public static void main(String[] args) {
		
		try {
			
			update();
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
		}

	}
	
	static void update() throws Exception {
		
		Deliberation d = new Deliberation(3, 3, 1, (float)7.5);
		
		Mybernate mb = new Mybernate();
		mb.update(d);
		
	}

}
