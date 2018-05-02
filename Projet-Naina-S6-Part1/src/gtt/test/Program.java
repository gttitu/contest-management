package gtt.test;

import gtt.model.dao.Connection;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import gtt.model.dao.candidate.*;
import gtt.model.dao.center.*;
import gtt.model.candidate.*;
import gtt.model.center.*;
import gtt.model.mark.*;
import gtt.model.dao.mark.*;
import gtt.model.*;
import gtt.model.dao.*;

public class Program {
	
	public static void main(String[] args) {
		
		java.sql.Connection connection = initConnection();
		System.out.println("**************************************************");
		
		List<BaseModel> lbm = null;
		CandidateDAO cddao = new CandidateDAO(connection);
		Candidate c = null;
		
		CandidateDetailDAO cdd = new CandidateDetailDAO(connection);
		CandidateDetail cd = null;
		
		Candidate cand = null;
		CandidateDetail canddt = null;
		int savedRow = 0;
		int savedRow1 = 0;
		try {
			//c = new Candidate(1);
			cd = new CandidateDetail(1,"Aro","Toavina",21,1);
			//savedRow = cddao.save(c);
			savedRow1 = cdd.save(cd);
			lbm = cddao.findAll(cand, "");
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(savedRow + "has been saved");
		System.out.println(savedRow1 + "has been saved");
		for(BaseModel base : lbm) {
			Candidate temporaire = (Candidate)base;
			System.out.println(temporaire);
		}
		System.out.println("**************************************************");
		Connection.close(connection);
		
	}
	
	static java.sql.Connection initConnection() {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/contestmanagement";
		return  Connection.get(driver, url, "root", "root");
		
	}

}
