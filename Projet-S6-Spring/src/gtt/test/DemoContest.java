package gtt.test;

import java.util.function.Consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import gtt.model.setting.Contest;
import gtt.service.ContestService;

public class DemoContest {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		ContestService serv = ctx.getBean(ContestService.class);
		
		try {
		
			findAllContests(serv);
		
		} catch (Exception ex) {
			
			ex.printStackTrace();
			
		}

	}
	
	static void findAllContests(ContestService serv) throws Exception {
		
		serv.getContests().forEach(new Consumer<Contest>() {
			@Override
			public void accept(Contest c) {
				System.out.println(c);
			}
		});
		
	}

}
