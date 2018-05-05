package gtt.model.dao.mybernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import gtt.model.mark.Deliberation;
import gtt.model.mark.Mark;

public class MbUtils {
	
	private static SessionFactory factory;
	
	public static SessionFactory getFactory() {
		
		if(factory == null)
			initFactory();
		
		return factory;
		
	}
	
	private static void initFactory() {
		
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Mark.class);
		cfg.addAnnotatedClass(Deliberation.class);
		
		factory = cfg.buildSessionFactory();
		
	}

}
