package gtt.test;

import java.util.ArrayList;
import java.util.List;

import gtt.model.BaseModel;
import gtt.model.dao.Connection;
import gtt.model.dao.generic.GenericDAO;

public class Program {
	
	public static void main(String[] args) {
		
		java.sql.Connection connection = initConnection();
		System.out.println("**********************************************");
		
		GenericDAO dao = new GenericDAO(connection, true);
		try {
			
			/*Voiture v = new Voiture();
			v.setModele("Ter%");
			List<Voiture> voitures = convertToVoiture(dao.findAll(v, null));
			listVoitures(voitures);*/
			
			//Marque m = new Marque("Peugeot");
			//dao.save(m);
			
			Voiture v = new Voiture(3, 2, "18");
			dao.update(v);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
		
			System.out.println("**********************************************");
			Connection.close(connection);
		
		}
		
	}
	
	static java.sql.Connection initConnection() {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/TestGeneric";
		return  Connection.get(driver, url, "root", "root");
		
	}
	
	static List<Marque> convertToMarque(List<BaseModel> models){
		
		List<Marque> result = new ArrayList<>();
		
		for(BaseModel m : models) {
			
			result.add((Marque)m);
			
		} return result;
		
	}
	
	static List<Voiture> convertToVoiture(List<BaseModel> models){
		
		List<Voiture> result = new ArrayList<>();
		
		for(BaseModel m : models) {
			
			result.add((Voiture)m);
			
		} return result;
		
	}
	
	static void listVoitures(List<Voiture> voitures) {
		
		for(Voiture v : voitures) {
			
			System.out.println(v.getId() + " : " + v.getMarque() + " : " + v.getModele());
			
		}
		
	}
	
	static void listMarques(List<Marque> marques) {
		
		for(Marque m : marques) {
			
			System.out.println(m.getId() + " : " + m.getNom());
			
		}
		
	}

}
