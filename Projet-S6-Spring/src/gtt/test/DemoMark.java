package gtt.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//import gtt.service.MatterService;
import gtt.service.MarkService;
//import gtt.service.CandidateService;
import gtt.model.setting.*;
import gtt.model.mark.*;
import gtt.model.candidate.*;
//import gtt.model.center.*;

public class DemoMark {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		MarkService markServ = ctx.getBean(MarkService.class);
		
		try {
		
			addMark(markServ);
		
		} catch (Exception ex) {
			
			ex.printStackTrace();
			
		}
	}
	
	static void addMark(MarkService serv) throws Exception {
		Mark mark = new Mark(1,1,1, (float)12.5);
		Matter matter = new Matter(1,1,"Droits",3,(float)25.00,"2018-05-02 08:00:00","2018-05-02 11:30:00");
		Candidate candidate = new Candidate(1,1);
		Contest contest = new Contest(1,"Concours des magistrats",false,"2018-05-02","2018-05-06");
		serv.addMark(mark,matter,candidate,contest);		
	}
}
