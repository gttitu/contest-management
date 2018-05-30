package gtt.service;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gtt.model.dao.mybernate.Mybernate;
import gtt.model.setting.*;

@Component
public class MatterService {
	
	@Autowired
	private Mybernate dataAccess;
	
	public void addMatter(Matter matter) throws Exception {
		
		dataAccess.setAutoTransaction(false);
		
		Session session = dataAccess.createSession();
		
		try {
		
			session.beginTransaction();
			this.doAdd(session, matter);
			session.getTransaction().commit();
		
		} catch(Exception ex) {
			
			throw ex;
			
		} finally {
			
			session.close();
			
		}
		
	}
	
	private void doAdd(Session session, Matter matter) throws Exception {
		
		if(this.existsContest(matter.getContest())) {
			
			if(this.checkDates(matter, matter.getContest())){
				
				dataAccess.save(matter, session);
				
			} else throw new ServiceException("The dates of matter are out of the dates of contest !");
		
		} else throw new ServiceException("The specified contest doesn't exist !");
		
	}
	
	private boolean existsContest(Integer id) throws Exception {
		
		boolean result = true;
		
		Contest contest = new Contest(); contest.setId(id);
		dataAccess.findById(contest);
		if(contest.getDescription() == null || contest.getDateBegin() == null || contest.getDateEnd() == null)
			result = false;
		
		return result;
		
	}
	
	private boolean checkDates(Matter matter, Integer contest) throws Exception {
		
		boolean result = false;
		
		int nb = dataAccess.findAll(new Contest(), "FROM Contest c WHERE c.id=" + matter.getContest() + " AND c.dateBegin <= '" + matter.getDatetimeBegin() + "' AND c.dateEnd >= '" + matter.getDatetimeEnd() + "'").size();
		if(nb == 1)
			result = true;
		
		return result;
		
	}
}
