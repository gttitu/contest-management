package gtt.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import gtt.model.candidate.Candidate;
import gtt.model.candidate.CandidateDetail;
import gtt.service.CandidateService;

public class DemoCandidate {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		CandidateService serv = ctx.getBean(CandidateService.class);
		
		try {
		
			addCandidate(serv);
		
		} catch (Exception ex) {
			
			ex.printStackTrace();
			
		}

	}
	
	static void addCandidate(CandidateService serv) throws Exception {
		
		Candidate candidate = new Candidate(4);
		CandidateDetail detail = new CandidateDetail(1, "Drago", "Malfoy", 13, 1);
		//CandidateDetail detail = new CandidateDetail(1, "Harry", "Potter", 13, 1);
		//CandidateDetail detail = new CandidateDetail(1, "Hermione", "Granger", 12, 0);
		//CandidateDetail detail = new CandidateDetail(1, "Ron", "Weasley", 12, 1);
		//CandidateDetail detail = new CandidateDetail(1, "Jean", "Kevin", 14, 1);
		
		serv.addCandidate(candidate, detail);
		
	}

}
