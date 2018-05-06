package gtt.test.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import gtt.model.BaseModel;
import gtt.model.candidate.Candidate;
import gtt.model.setting.Contest;
import gtt.model.candidate.CandidateDetail;
import gtt.model.center.Room;
import gtt.model.center.RoomDetail;
import gtt.model.dao.generic.GenericDAO;
import gtt.model.dao.ConnUtils;

@SuppressWarnings("unused")
public class GenericDemo {
	
	public static void main(String[] args) {
		
		System.out.println("*********************Generic*************************");
		
		try {
			GenericDAO dao = new GenericDAO(true);
			//findAll(dao);
			//save(dao)
			//update(dao);
			//delete(dao);
			//findByFullText(dao);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	static void findById(GenericDAO dao) throws Exception {
		
		Room model = new Room(); model.setId(2);
		dao.findById(model);
		
		System.out.println(model);
		
	}
	
	@SuppressWarnings("unchecked")
	static void findAll(GenericDAO dao) throws Exception {
		
		/*List<Room> rooms = dao.findAll(2, 2, new Room(), null);
		rooms.forEach(new Consumer<Room>() {

			@Override
			public void accept(Room t) {
				System.out.println(t);
			}
		});*/
		List<RoomDetail> rooms = dao.findAll(2, 2, new RoomDetail(), null);
		rooms.forEach(new Consumer<RoomDetail>() {

			@Override
			public void accept(RoomDetail t) {
				System.out.println(t);
			}
		});		
		
	}
	
	static void save(GenericDAO dao) throws Exception{
		
		CandidateDetail model = new CandidateDetail(5, "Ryan", "Chan", 20, 1);
		dao.save(model);
		
	}
	
	static void update(GenericDAO dao) throws Exception{
		
		Candidate model = new Candidate(6, 3);
		dao.update(model);
		
	}
	
	static void delete(GenericDAO dao) throws Exception{
		
		Candidate model = new Candidate(6, 3);
		dao.delete(model);
		
	}
	
	@SuppressWarnings("unchecked")
	static void findByFullText(GenericDAO dao) throws Exception{
		
		dao.findAllByFullText(CandidateDetail.class, "Renaud Irving").forEach(new Consumer<CandidateDetail>() {

			@Override
			public void accept(CandidateDetail t) {
				
				System.out.println(t);
				
			}
			
		});
		
	}

}
