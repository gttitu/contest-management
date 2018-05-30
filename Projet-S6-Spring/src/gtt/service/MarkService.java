package gtt.service;

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
		int totalMark = countMarkTotal(contest);
		if(totalMark < NumberTotalMarkContest(contest)) {
			if(candidateHadnotMark(candidate, matter)) {
				dataAccess.save(mark);
			}else throw new ServiceException("The candidate already had mark for this matter!!");
		}else throw new ServiceException("There are too many marks!!");
	}
	
	private int countMarkTotal(Contest contest)throws Exception {
		int result = 0;
		result = dataAccess.findAll(new Mark(), "FROM Mark m, Matter mt where m.matter = mt.id AND mt.contest = "+contest.getId()).size();
		return result;
	}
	private int countCandidateContest(Contest contest)throws Exception{
		int result = 0;
		result = dataAccess.findAll(new Candidate(),"FROM Candidate c, Center ct where c.idCenter = ct.id AND ct.contest = "+contest.getId()).size();
		return result;
	}
	private int countMatterContest(Contest contest)throws Exception{
		int result = 0;
		result = dataAccess.findAll(new Matter(),"FROM Matter m where contest = "+contest.getId()).size();
		return result;
	}
	
	private int NumberTotalMarkContest(Contest contest)throws Exception{
		int result = 0;
		int numberCandidate = countCandidateContest(contest);
		int numberMatter = countMatterContest(contest);
		result = numberCandidate*numberMatter;
		return result;
	}
	
	private boolean candidateHadnotMark(Candidate candidate, Matter matter)throws Exception {
		boolean result = true;
		int verifMark = dataAccess.findAll(new Mark(), "FROM Mark m where candidate = "+candidate.getId() +"and matter = "+matter.getId()).size();
		if(verifMark==0)
			result = false;
		return result;
	}
}
