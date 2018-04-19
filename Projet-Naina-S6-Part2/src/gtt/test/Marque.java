package gtt.test;

import gtt.annotation.DbTable;
import gtt.annotation.NotTableAttr;
import gtt.annotation.TableAttr;
import gtt.model.BaseModel;
import gtt.model.ModelException;

@DbTable(name = "Marque")
public class Marque extends BaseModel {
	
	// ATTRIBUTES :
	
	@TableAttr(name="nom")
	private String nom;
	
	@NotTableAttr
	private String proprietaire;
	
	// CONSTRUCTS :
	
	public Marque() {}

	public Marque(String nom) {
		
		this.setNom(nom);
		
	}
	
	public Marque(Integer id, String nom) throws ModelException {
		
		this.setId(id);
		this.setNom(nom);
		
	}
	
	// GETTERS AND SETTERS :
	
	public String getNom() { return nom; }

	public void setNom(String nom) { this.nom = nom; }
	
	public String getProprietaire() { return proprietaire; }

	public void setProprietaire(String proprietaire) { this.proprietaire = proprietaire; }
	
	// METHODS :
	
	
	
	// STATIC METHODS :
	
	

}
