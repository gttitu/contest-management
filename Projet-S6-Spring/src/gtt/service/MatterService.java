package gtt.service;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
		
		if(this.existsContest(session, matter.getContest())) {
			
			if(!this.existsMatterDescription(session, matter, matter.getContest())) {
				
				if(this.isBeginBeforeEnd(matter)){
			
					if(this.checkDates(session, matter, matter.getContest())){
						
						dataAccess.save(matter, session);
						
					} else throw new ServiceException("The dates of matter are out of the dates of contest !");
				
				} else throw new ServiceException("The start datetime of the matter is not before the end datetime !");
				
			} else throw new ServiceException("The description of the matter already exists for this Contest !");
						
		} else throw new ServiceException("The specified contest doesn't exist !");
				
	}
	
	private boolean existsContest(Session session, Integer id) throws Exception {
		
		boolean result = true;
		
		Contest contest = new Contest(); contest.setId(id);
		dataAccess.findById(contest, session);
		if(contest.getDescription() == null || contest.getDateBegin() == null || contest.getDateEnd() == null)
			result = false;
		
		return result;
		
	}
	
	private boolean checkDates(Session session, Matter matter, Integer contest) throws Exception {
		
		boolean result = false;
		
		int nb = dataAccess.findAll(new Contest(), "FROM Contest c WHERE c.id=" + matter.getContest() + " AND c.dateBegin <= '" + matter.getDatetimeBegin() + "' AND c.dateEnd >= '" + matter.getDatetimeEnd() + "'", session).size();
		if(nb == 1)
			result = true;
		
		return result;
		
	}
	
	private boolean isBeginBeforeEnd(Matter matter) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTimeBegin = LocalDateTime.parse(matter.getDatetimeBegin(), formatter);
		LocalDateTime dateTimeEnd = LocalDateTime.parse(matter.getDatetimeEnd(), formatter);
		return dateTimeBegin.isBefore(dateTimeEnd) ;
		
	}
	
	private boolean existsMatterDescription(Session session, Matter matter, Integer contest) throws Exception {
		
		boolean result = false;
		
		int nb = dataAccess.findAll(new Matter(), "FROM Contest c, Matter m WHERE c.id=m.contest and m.description='" + matter.getDescription() + "'", session).size();
		if(nb>=1)
			result = true;
		
		return result;
		
	}
}
