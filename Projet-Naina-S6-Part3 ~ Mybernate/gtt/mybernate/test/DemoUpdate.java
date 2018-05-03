package gtt.mybernate.test;

import gtt.mybernate.Mybernate;
import gtt.mybernate.test.model.Metier;

public class DemoUpdate {

	public static void main(String[] args) {
		
		try {
			
			Metier m = new Metier(5, "Divers");
			
			Mybernate dao = new Mybernate();
			dao.update(m);
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
		}

	}

}
