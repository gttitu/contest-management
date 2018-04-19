package gtt.test;

import gtt.annotation.CondOperator;
import gtt.annotation.DbTable;
import gtt.annotation.TableAttr;
import gtt.model.BaseModel;
import gtt.model.ModelException;

@DbTable(name = "Voiture")
public class Voiture extends BaseModel {
	
	// ATTRIBUTES :
	
	@TableAttr(name="marque")
	private Integer marque;
	
	@TableAttr(name="modele")
	@CondOperator(term = "LIKE")
	private String modele;
	
	// CONSTRUCTS :
	
	public Voiture() {}
	
	public Voiture(Integer id, Integer marque, String modele) throws ModelException {
		
		this.setId(id);
		this.setMarque(marque);
		this.setModele(modele);
		
	}
	
	public Voiture(Integer marque, String modele) throws ModelException {
		
		this.setMarque(marque);
		this.setModele(modele);
		
	}
	
	// GETTERS AND SETTERS :
	
	public Integer getMarque() { return marque; }

	public void setMarque(Integer marque) throws ModelException {
		if(marque > 0)
			this.marque = marque;
		else
			throw new ModelException("Invalid value on Marque : " + marque + " !");
	}

	public String getModele() { return modele; }

	public void setModele(String modele) { this.modele = modele; }
	
	// METHODS :
	
	
	
	// STATIC METHODS :
	
	

}
