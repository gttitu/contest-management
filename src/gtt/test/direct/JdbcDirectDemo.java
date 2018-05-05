package gtt.test.direct;

import java.util.function.Consumer;

import gtt.model.center.Center;
import gtt.model.center.CenterDetail;
import gtt.model.dao.center.CenterDAO;
import gtt.model.dao.center.CenterDetailDAO;
import gtt.model.dao.mark.DeliberationDAO;
import gtt.model.dao.mark.MarkDAO;
import gtt.model.dao.setting.ContestDAO;
import gtt.model.dao.setting.MatterDAO;
import gtt.model.mark.Deliberation;
import gtt.model.mark.Mark;
import gtt.model.setting.Contest;
import gtt.model.setting.Matter;

public class JdbcDirectDemo {
	
	public static void main(String[] args) {
		
		try {
		
			//requestContest(connection);
			//requestMatter(connection);
			//requestCenter(connection);
			//requestCenterDetail(connection);
			//requestMark(connection);
			//requestDeliberation(connection);
		
		} catch (Exception ex) {
			
			ex.printStackTrace();
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	static void requestContest(java.sql.Connection connection) throws Exception {
		
		ContestDAO ctdao = new ContestDAO(connection);
		ctdao.findAllByFullText(null, "magistrats* license* concours*").forEach(new Consumer<Contest>() {

			@Override
			public void accept(Contest t) {
				
				System.out.println(t);
				
			}
			
		});
		
	}
	
	@SuppressWarnings("unchecked")
	static void requestMatter(java.sql.Connection connection) throws Exception {
		
		MatterDAO mtdao = new MatterDAO(connection);
		mtdao.findAll(new Matter(), null).forEach(new Consumer<Matter>() {

			@Override
			public void accept(Matter t) {
				
				System.out.println(t);
				
			}
			
		});
		
	}
	
	@SuppressWarnings("unchecked")
	static void requestCenter(java.sql.Connection connection) throws Exception{
		
		CenterDAO ctdao = new CenterDAO(connection);
		ctdao.findAll(new Center(), null).forEach(new Consumer<Center>() {

			@Override
			public void accept(Center t) {
				
				System.out.println(t);
				
			}
			
		});
		
	}
	
	@SuppressWarnings("unchecked")
	static void requestCenterDetail(java.sql.Connection connection) throws Exception{
		
		CenterDetailDAO ctdao = new CenterDetailDAO(connection);
		ctdao.findAll(new CenterDetail(), null).forEach(new Consumer<CenterDetail>() {

			@Override
			public void accept(CenterDetail t) {
				
				System.out.println(t);
				
			}
			
		});
		
	}
	
	@SuppressWarnings("unchecked")
	static void requestMark(java.sql.Connection connection) throws Exception{
		
		MarkDAO ctdao = new MarkDAO(connection);
		ctdao.findAll(new Mark(), null).forEach(new Consumer<Mark>() {

			@Override
			public void accept(Mark t) {
				
				System.out.println(t);
				
			}
			
		});
		
	}
	
	@SuppressWarnings("unchecked")
	static void requestDeliberation(java.sql.Connection connection) throws Exception{
		
		DeliberationDAO ctdao = new DeliberationDAO(connection);
		ctdao.findAll(new Deliberation(), null).forEach(new Consumer<Deliberation>() {

			@Override
			public void accept(Deliberation t) {
				
				System.out.println(t);
				
			}
			
		});
		
	}

}
