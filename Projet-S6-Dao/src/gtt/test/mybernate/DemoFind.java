package gtt.test.mybernate;

import java.util.function.Consumer;

import gtt.model.dao.mybernate.Mybernate;
import gtt.model.mark.Mark;

public class DemoFind {

	public static void main(String[] args) {
		
		try {
			
			//findById();
			//findAll();
			//findAllWithPage();
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
		}

	}
	
	static void findById() throws Exception {
		
		Mark m = new Mark(); m.setId(4);
		Mybernate mb = new Mybernate();
		mb.findById(m);
		
		System.out.println(m);
		
	}
	
	@SuppressWarnings("unchecked")
	static void findAll() throws Exception {
		
		Mark m = new Mark(); m.setMarkValue((float)16);
		Mybernate mb = new Mybernate();
		mb.findAll(m, null).forEach(new Consumer<Mark>() {

			@Override
			public void accept(Mark t) { System.out.println(t); }
			
		});
		
	}
	
	@SuppressWarnings("unchecked")
	static void findAllWithPage() throws Exception {
		
		Mark m = new Mark();
		Mybernate mb = new Mybernate();
		mb.findAll(1, 3, m, null).forEach(new Consumer<Mark>() {

			@Override
			public void accept(Mark t) { System.out.println(t); }
			
		});
		
	}

}
