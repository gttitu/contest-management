package gtt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gtt.dao.mybernate.Mybernate;
import gtt.model.setting.*;

@Component
public class MatterService {
	
	private Mybernate dataAccess;
	
	@Autowired
	private void init(Mybernate dataAccess) {
		
		this.dataAccess = dataAccess;
		
	}
	
	public void addMatter(Matter matter) throws Exception {
		
		if(this.existsContest(matter.getContest())) {
			
			if(this.checkDates(matter, matter.getContest())){
				
				dataAccess.save(matter);
				
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
		
		int nb = dataAccess.findAll(new Contest(), "FROM Contest c, Matter m WHERE c.id=m.contest AND c.dateBegin <= '" + matter.getDatetimeBegin() + "' AND c.dateEnd >= '" + matter.getDatetimeEnd() + "'").size();
		if(nb==1)
			result = true;
		
		return result;
		
	}
}
