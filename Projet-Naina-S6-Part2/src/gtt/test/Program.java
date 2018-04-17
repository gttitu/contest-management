package gtt.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import gtt.model.BaseModel;
import gtt.model.GenericCondition;
import gtt.model.dao.Connection;
import gtt.model.dao.generic.GenericDAO;
import gtt.model.dao.generic.UtilsDAO;

public class Program {
	
	public static void main(String[] args) {
		
		java.sql.Connection connection = initConnection();
		System.out.println("**********************************************");
		
		GenericDAO dao = new GenericDAO(connection, true);
		String query = null;
		try {
			
			//List<BaseModel> models = dao.findAll(new GenericCondition(Marque.class, query));
			//List<Marque> marques = convertToMarque(models);
			//listMarques(marques);
			
			//Marque marque = (Marque)dao.findById(new GenericCondition(Marque.class, 2));
			//System.out.println(marque.getId() + " : " + marque.getNom());
			//dao.delete(new GenericCondition(Voiture.class, 3));
			
			//List<BaseModel> models = dao.findAll(new GenericCondition(Voiture.class, query));
			//List<Voiture> voitures = convertToVoiture(models);
			//listVoitures(voitures);
			
			//Marque m = new Marque("Mitsubishi");
			//dao.save(m);
			
			Voiture v = new Voiture(1, 1, "Terios");
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
