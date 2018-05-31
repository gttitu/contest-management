package gtt.service;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gtt.model.dao.mybernate.Mybernate;
import gtt.model.candidate.Candidate;
import gtt.model.setting.Contest;
import gtt.model.mark.Mark;
import gtt.model.setting.Matter;

@Component
public class MarkService {
	private Mybernate dataAccess;
	
	@Autowired
	private void init(Mybernate dataAccess) {
		this.dataAccess = dataAccess;	
	}
	
	public void addMark(Mark mark, Matter matter, Candidate candidate, Contest contest)throws Exception{
		dataAccess.setAutoTransaction(false);
		
		Session session = dataAccess.createSession();
		try {
			session.beginTransaction();
			this.doAdd(session, mark, matter, candidate, contest);
			session.getTransaction().commit();
		}catch(Exception ex) {
			throw ex;
		}finally {
			session.close();
		}		
	}
	
	private void doAdd(Session session, Mark mark, Matter matter, Candidate candidate, Contest contest)throws Exception{
		int totalMark = countMarkTotal(session, contest);
		if(totalMark != 0) {
			if(totalMark < NumberTotalMarkContest(session, contest)) {
				if(candidateHadnotMark(candidate, matter)) {
					dataAccess.save(mark, session);
				}
				else { throw new ServiceException("The candidate already had mark for this matter!!");}
			}else throw new ServiceException("There are too many marks!!");
		}
		else {
			if(candidateHadnotMark(candidate, matter)) {
				dataAccess.save(mark, session);
			}else { throw new ServiceException("The candidate already had mark for this matter!!");}
		}
	}
	
	private int countMarkTotal(Session session, Contest contest)throws Exception {
		int result = 0;
		result = dataAccess.findAll(new Mark(), "FROM Mark m, Matter mt where m.matter = mt.id AND mt.contest = "+contest.getId(), session).size();
		return result;
	}
	private int countCandidateContest(Session session, Contest contest)throws Exception{
		int result = 0;
		result = dataAccess.findAll(new Candidate(),"FROM Candidate c, Center ct where c.center = ct.id AND ct.contest = "+contest.getId(), session).size();
		return result;
	}
	private int countMatterContest(Session session, Contest contest)throws Exception{
		int result = 0;
		result = dataAccess.findAll(new Matter(),"FROM Matter m where contest = "+contest.getId(), session).size();
		return result;
	}
	
	private int NumberTotalMarkContest(Session session, Contest contest)throws Exception{
		int result = 0;
		int numberCandidate = countCandidateContest(session, contest);
		int numberMatter = countMatterContest(session, contest);
		result = numberCandidate*numberMatter;
		return result;
	}
	
	private boolean candidateHadnotMark(Candidate candidate, Matter matter)throws Exception {
		boolean result = false;
		int verifMark = dataAccess.findAll(new Mark(), "FROM Mark m where candidate = "+candidate.getId() +"and matter = "+matter.getId()).size();
		if(verifMark==0) {
			result = true;
		}
		return result;
	}
}
