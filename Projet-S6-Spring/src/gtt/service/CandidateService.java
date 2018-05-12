package gtt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gtt.dao.mybernate.Mybernate;
import gtt.model.candidate.Candidate;
import gtt.model.candidate.CandidateDetail;
import gtt.model.center.Center;
import gtt.model.center.CenterDetail;

@Component
public class CandidateService {
	
	private Mybernate dataAccess;
	
	@Autowired
	private void init(Mybernate dataAccess) {
		
		this.dataAccess = dataAccess;
		
	}
	
	public void addCandidate(Candidate candidate, CandidateDetail detail) throws Exception {
		
		if(this.existsCenter(candidate.getCenter())) {
			
			if(this.checkNbByGender(candidate.getCenter(), detail.getGender())) {
				
				if(this.checkAge(candidate.getCenter(), detail.getAge())) {
					
					dataAccess.save(candidate);
					
					detail.setCandidate(candidate.getId());
					
					dataAccess.save(detail);
					
				} else throw new ServiceException("The candidate doesn't have the correct age for this contest !");
				
			} else throw new ServiceException("The number of candidate with this gender is already complete !");
			
		} else throw new ServiceException("The specified center doesn't exist !");
		
	}
	
	@SuppressWarnings("unchecked")
	private boolean checkAge(Integer center, Integer age) throws Exception {
		
		boolean result = false;
		
		CenterDetail search = new CenterDetail(); search.setCenter(center);
		List<CenterDetail> details = dataAccess.findAll(search, null);
		if(details.size() == 1) {
			
			CenterDetail detail = details.get(0);
			if(detail.getMinAge() <= age && detail.getMaxAge() >= age)
				result = true;
			
		} return result;
		
	}
	
	private boolean existsCenter(Integer id) throws Exception {
		
		boolean result = true;
		
		Center center = new Center(); center.setId(id);
		dataAccess.findById(center);
		if(center.getDescription() == null || center.getDescription() == null || center.getContest() == null || center.getLocation() == null)
			result = false;
		
		return result;
		
	}
	
	@SuppressWarnings("unchecked")
	private boolean checkNbByGender(Integer center, Integer gender) throws Exception {
		
		boolean result = false;
		
		CenterDetail search = new CenterDetail(); search.setCenter(center);
		List<CenterDetail> details = dataAccess.findAll(search, null);
		if(details.size() == 1) {
			
			result = this.doCheckNbByGender(details.get(0), center, gender);
			
		} return result;
		
	}
	
	private boolean doCheckNbByGender(CenterDetail detail, Integer center, Integer gender) throws Exception {
		
		boolean result = false;
		
		int nb = dataAccess.findAll(new CandidateDetail(), "FROM Candidate c, CandidateDetail cd WHERE c.id=cd.candidate AND c.center = " + center + " AND cd.gender = " + gender).size();
		if((gender == 1 && detail.getNbMen() >= (nb + 1)) || (gender == 0 && detail.getNbWomen() >= (nb + 1)))
			result = true;
		
		return result;
		
	}

}
