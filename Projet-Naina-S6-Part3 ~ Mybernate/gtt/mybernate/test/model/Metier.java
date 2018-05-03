package gtt.mybernate.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import gtt.model.BaseModel;
import gtt.model.ModelException;

@Entity
@Table(name = "Metier")
public class Metier extends BaseModel {
	
	// ATTRIBUTES :
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected Integer id;
	
	@Column(name = "nom")
	private String nom;
	
	// CONSTUCTS :
	
	public Metier() {}
	
	public Metier(Integer id, String nom) throws ModelException {
		
		this.setId(id);
		this.setNom(nom);
		
	}
	
	public Metier(String nom) {
		
		this.setNom(nom);
		
	}
	
	// GETTERS AND SETTERS :
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) throws ModelException {
		if(id > 0)
			this.id = id;
		else
			throw new ModelException("Invalid value on ID : " + id + " !");
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	// METHODS :
	
	public boolean equals(Metier cmp) {
		
		return id.equals(cmp.getId());
		
	}
	
	@Override
	public String toString() {
		return "Metier [nom=" + nom + ", id=" + id + "]";
	}
	
	// STATIC METHODS :
	
	

}
