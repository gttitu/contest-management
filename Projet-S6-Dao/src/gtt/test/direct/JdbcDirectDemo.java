package gtt.test.direct;

import java.util.function.Consumer;

import gtt.model.center.Center;
import gtt.model.center.CenterDetail;
import gtt.dao.center.CenterDAO;
import gtt.dao.center.CenterDetailDAO;
import gtt.dao.mark.DeliberationDAO;
import gtt.dao.mark.MarkDAO;
import gtt.dao.setting.ContestDAO;
import gtt.dao.setting.MatterDAO;
import gtt.model.mark.Deliberation;
import gtt.model.mark.Mark;
import gtt.model.setting.Contest;
import gtt.model.setting.Matter;
import gtt.cache.*;
import gtt.dao.*;
import java.time.*;

public class JdbcDirectDemo {
	
	public static void main(String[] args) {
		
		try {
			java.sql.Connection connection = ConnUtils.get();
			requestContest(connection);
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
		
		ContestDAO ctdao = new ContestDAO();
		CacheData cache = new CacheData();
		cache.initCache(Duration.ofSeconds(5), true);
		//ctdao.findAll(new Contest()).forEach(new Consumer<Contest>() {
		String keywords =  "magistr*";
		//String keywords =  "magistrats* license* concours*"
		System.out.println("1er findAllByFullText");
		ctdao.findAllByFullText(null, keywords).forEach(new Consumer<Contest>() {
			@Override
			public void accept(Contest t) {
				System.out.println(t);
			}
		});
		System.out.println("2e findAllByFullText");
		ctdao.findAllByFullText(null, keywords).forEach(new Consumer<Contest>() {
			@Override
			public void accept(Contest t) {
				System.out.println(t);
			}
		});
		Thread.sleep(10000);
		System.out.println("3e findAllByFullText après 10 secs");
		ctdao.findAllByFullText(null, keywords).forEach(new Consumer<Contest>() {
			@Override
			public void accept(Contest t) {
				System.out.println(t);
			}
		});
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	static void requestMatter(java.sql.Connection connection) throws Exception {
		
		MatterDAO mtdao = new MatterDAO();
		mtdao.findAll(new Matter(), null).forEach(new Consumer<Matter>() {

			@Override
			public void accept(Matter t) {
				
				System.out.println(t);
				
			}
			
		});
		
	}
	
	@SuppressWarnings("unchecked")
	static void requestCenter(java.sql.Connection connection) throws Exception{
		
		CenterDAO ctdao = new CenterDAO();
		ctdao.findAll(new Center(), null).forEach(new Consumer<Center>() {

			@Override
			public void accept(Center t) {
				
				System.out.println(t);
				
			}
			
		});
		
	}
	
	@SuppressWarnings("unchecked")
	static void requestCenterDetail(java.sql.Connection connection) throws Exception{
		
		CenterDetailDAO ctdao = new CenterDetailDAO();
		ctdao.findAll(new CenterDetail(), null).forEach(new Consumer<CenterDetail>() {

			@Override
			public void accept(CenterDetail t) {
				
				System.out.println(t);
				
			}
			
		});
		
	}
	
	@SuppressWarnings("unchecked")
	static void requestMark(java.sql.Connection connection) throws Exception{
		
		MarkDAO ctdao = new MarkDAO();
		ctdao.findAll(new Mark(), null).forEach(new Consumer<Mark>() {

			@Override
			public void accept(Mark t) {
				
				System.out.println(t);
				
			}
			
		});
		
	}
	
	@SuppressWarnings("unchecked")
	static void requestDeliberation(java.sql.Connection connection) throws Exception{
		
		DeliberationDAO ctdao = new DeliberationDAO();
		ctdao.findAll(new Deliberation(), null).forEach(new Consumer<Deliberation>() {

			@Override
			public void accept(Deliberation t) {
				
				System.out.println(t);
				
			}
			
		});
		
	}

}
