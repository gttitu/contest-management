package gtt.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import gtt.service.MatterService;
import gtt.model.setting.*;

public class DemoMatter {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		
		MatterService mattServ = ctx.getBean("mattServ", MatterService.class);
		
		try {
		
			addMatter(mattServ);
		
		} catch (Exception ex) {
			
			ex.printStackTrace();
			
		}

	}
	
	
	
	static void addMatter(MatterService serv) throws Exception {
		
		Matter matter = new Matter(1, "Maths", 5, (float)50, "2018-05-08 08:00", "2018-05-08 11:30");
		//Matter matter = new Matter(1, "Maths", 5, (float)50, "2018-05-08 11:00", "2018-05-08 08:00");
		serv.addMatter(matter);
		
	}

}
