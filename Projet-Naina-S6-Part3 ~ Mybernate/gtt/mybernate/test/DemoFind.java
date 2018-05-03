package gtt.mybernate.test;

import java.util.List;
import java.util.function.Consumer;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import gtt.mybernate.Mybernate;
import gtt.mybernate.test.model.Metier;

public class DemoFind {

	public static void main(String[] args) {
		
		try {
			
			//findAllByCriteria();
			findAll();
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
		}

	}
	
	static void findById() throws Exception {
		
		Metier m = new Metier(); m.setId(1);
		
		Mybernate dao = new Mybernate();
		dao.findById(m);
		
		System.out.println(m);
		
	}
	
	@SuppressWarnings("unchecked")
	static void findAllByHQL() throws Exception {
		
		Mybernate dao = new Mybernate();
		List<Metier> metiers = (List<Metier>) dao.findAllByHQL(Metier.class, "FROM Metier m WHERE m.id > 2");
		metiers.forEach(new Consumer<Metier>() {

			@Override
			public void accept(Metier m) {
				
				System.out.println(m);
				
			}
			
		});
		
	}
	
	@SuppressWarnings("unchecked")
	static void findAll() throws Exception{
		
		Metier m = new Metier("%e%");
		
		Mybernate dao = new Mybernate();
		List<Metier> metiers = (List<Metier>) dao.findAll(2, 2, m, null);
		
		metiers.forEach(new Consumer<Metier>() {

			@Override
			public void accept(Metier m) {
				
				System.out.println(m);
				
			}
			
		});
		
	}
	
	@SuppressWarnings("unchecked")
	static void findAllByCriteria() throws Exception{
		
		Mybernate dao = new Mybernate();
		Criteria crt = dao.loadCriteria(Metier.class);
		crt.add(Restrictions.like("nom", "%er%"));
		List<Metier> metiers = (List<Metier>) dao.findAllByCriteria(Metier.class, crt);
		
		metiers.forEach(new Consumer<Metier>() {

			@Override
			public void accept(Metier m) {
				
				System.out.println(m);
				
			}
			
		});
		
	}

}
