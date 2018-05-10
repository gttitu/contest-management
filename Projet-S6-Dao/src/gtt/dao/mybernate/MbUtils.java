package gtt.dao.mybernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MbUtils {
	
	private static SessionFactory factory;
	
	public static SessionFactory getFactory() {
		
		if(factory == null)
			initFactory();
		
		return factory;
		
	}
	
	private static void initFactory() {
		
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		
		factory = cfg.buildSessionFactory();
		
	}

}
