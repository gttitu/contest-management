package gtt.service;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gtt.model.dao.mybernate.Mybernate;
import gtt.model.candidate.Candidate;
import gtt.model.candidate.CandidateDetail;
import gtt.model.center.Center;
import gtt.model.center.CenterDetail;

@Component
public class CandidateService {
	
	@Autowired
	private Mybernate dataAccess;
	
	@SuppressWarnings("unchecked")
	public List<CandidateDetail> getCandidates() throws Exception{
		
		return dataAccess.findAll(new CandidateDetail(), null);
		
	}
	
	public void addCandidate(Candidate candidate, CandidateDetail detail) throws Exception {
		
		dataAccess.setAutoTransaction(false);
		
		Session session = dataAccess.createSession();
		
		try {
		
			session.beginTransaction();
			this.doAdd(session, candidate, detail);
			session.getTransaction().commit();
		
		} catch(Exception ex) {
			
			throw ex;
			
		} finally {
			
			session.close();
			
		}
		
	}
	
	private void doAdd(Session session, Candidate candidate, CandidateDetail detail) throws Exception {
		
		if(!this.somethingIsNull(candidate, detail)) {
		
			if(this.existsCenter(session, candidate.getCenter())) {
				
				if(this.checkNbByGender(session, candidate.getCenter(), detail.getGender())) {
					
					if(this.checkAge(session, candidate.getCenter(), detail.getAge())) {
						
						dataAccess.save(candidate, session);
						
						detail.setCandidate(candidate.getId());
						
						dataAccess.save(detail, session);
						
					} else throw new ServiceException("The candidate doesn't have the correct age for this contest !");
					
				} else throw new ServiceException("The number of candidate with this gender is already complete !");
				
			} else throw new ServiceException("The specified center doesn't exist !");
		
		} else throw new ServiceException("There is something null !");
		
	}
	
	private boolean somethingIsNull(Candidate candidate, CandidateDetail detail) {
		
		boolean result = false;
		
		if(candidate.getCenter() == null || detail.getFirstname() == null || detail.getLastname() == null ||
				detail.getAge() == null || detail.getGender() == null) {
			
			result = true;
			
		} return result;
		
	}
	
	@SuppressWarnings("unchecked")
	private boolean checkAge(Session session, Integer center, Integer age) throws Exception {
		
		boolean result = false;
		
		CenterDetail search = new CenterDetail(); search.setCenter(center);
		List<CenterDetail> details = dataAccess.findAll(search, null, session);
		if(details.size() == 1) {
			
			CenterDetail detail = details.get(0);
			if(detail.getMinAge() <= age && detail.getMaxAge() >= age)
				result = true;
			
		} return result;
		
	}
	
	private boolean existsCenter(Session session, Integer id) throws Exception {
		
		boolean result = true;
		
		Center center = new Center(); center.setId(id);
		dataAccess.findById(center, session);
		if(center.getDescription() == null || center.getDescription() == null || center.getContest() == null || center.getLocation() == null)
			result = false;
		
		return result;
		
	}
	
	@SuppressWarnings("unchecked")
	private boolean checkNbByGender(Session session, Integer center, Integer gender) throws Exception {
		
		boolean result = false;
		
		CenterDetail search = new CenterDetail(); search.setCenter(center);
		List<CenterDetail> details = dataAccess.findAll(search, null, session);
		if(details.size() == 1) {
			
			result = this.doCheckNbByGender(session, details.get(0), center, gender);
			
		} return result;
		
	}
	
	private boolean doCheckNbByGender(Session session, CenterDetail detail, Integer center, Integer gender) throws Exception {
		
		boolean result = false;
		
		int nb = dataAccess.findAll(new CandidateDetail(), "FROM Candidate c, CandidateDetail cd WHERE c.id=cd.candidate AND c.center = " + center + " AND cd.gender = " + gender, session).size();
		if((gender == 1 && detail.getNbMen() >= (nb + 1)) || (gender == 0 && detail.getNbWomen() >= (nb + 1)))
			result = true;
		
		return result;
		
	}

}
