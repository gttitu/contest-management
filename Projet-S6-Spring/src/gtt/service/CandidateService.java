package gtt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gtt.dao.mybernate.Mybernate;
import gtt.model.candidate.Candidate;
import gtt.model.candidate.CandidateDetail;
import gtt.model.center.Center;

@Component
public class CandidateService {
	
	private Mybernate dataAccess;
	
	@Autowired
	private void init(Mybernate dataAccess) {
		
		this.dataAccess = dataAccess;
		
	}
	
	public void addCandidate(Candidate candidate, CandidateDetail detail) throws Exception {
		
		if(this.existsCenter(candidate.getCenter())) {
			
			
			
		} else throw new ServiceException("The specified center doesn't exist !");
		
	}
	
	private boolean existsCenter(Integer id) throws Exception {
		
		boolean result = true;
		
		Center center = new Center(); center.setId(id);
		dataAccess.findById(center);
		if(center.getDescription() == null)
			result = false;
		
		return result;
		
	}

}
