package test.service;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gtt.dao.mybernate.Mybernate;
import gtt.model.mark.Mark;

@Component
public class MarkService {
	
	private Mybernate dataAccess;
	
	@Autowired
	private void initDAO(Mybernate dataAccess) {
		
		this.dataAccess = dataAccess;
		
	}
	
	@SuppressWarnings("unchecked")
	public void run() {
		
		try {
		
			dataAccess.findAll(new Mark(), null).forEach(new Consumer<Mark>() {

				@Override public void accept(Mark t) { System.out.println(t); }
				
			});
		
		} catch (Exception ex) {
			
			ex.printStackTrace();
			
		}
		
	}

}
