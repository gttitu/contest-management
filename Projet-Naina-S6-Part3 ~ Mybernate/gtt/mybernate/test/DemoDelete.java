package gtt.mybernate.test;

import gtt.mybernate.Mybernate;
import gtt.mybernate.test.model.Metier;

public class DemoDelete {

	public static void main(String[] args) {
		
		try {
			
			Metier m = new Metier(); m.setId(6);
			
			Mybernate dao = new Mybernate();
			dao.delete(m);
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
		}

	}

}
