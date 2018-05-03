package gtt.mybernate.test;

import gtt.mybernate.Mybernate;
import gtt.mybernate.test.model.Metier;

public class DemoSave {

	public static void main(String[] args) {
		
		try {
			
			Metier m = new Metier("Serveur");
			
			Mybernate dao = new Mybernate();
			dao.save(m);
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
		}

	}

}
