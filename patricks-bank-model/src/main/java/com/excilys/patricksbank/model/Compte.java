package com.excilys.patricksbank.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.excilys.patricksbank.model.dto.CompteDTO;

@Entity
@Table (name="compte")
public class Compte {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idCompte",unique = true, nullable = false)
	private String idCompte;
	private double montant;
	private String numero;
	private String libelle;
	
	@ManyToMany(mappedBy = "comptes", targetEntity = Utilisateur.class)
	private List<Utilisateur> proprietaires;

	@OneToMany(mappedBy = "compte", targetEntity = Operation.class)
	private List<Operation> operations;
	
	public Compte(String numero, double montant, String libelle) {
		this.numero = numero;
		this.montant = montant;
		this.proprietaires = new ArrayList<Utilisateur>();
		this.libelle = libelle;
	}
	
	public Compte(){
	}
	
	public Compte(CompteDTO compteDTO) {
		this(compteDTO.getNumero(), compteDTO.getMontant(), compteDTO.getLibelle());
		this.idCompte = compteDTO.getIdCompte();
	}
	
	public void addProprietaire(Utilisateur u){
		this.proprietaires.add(u);
	}

	public String getIdCompte() {
		return idCompte;
	}

	public void setIdCompte(String idCompte) {
		this.idCompte = idCompte;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public List<Utilisateur> getProprietaires() {
		return proprietaires;
	}

	public void setProprietaires(List<Utilisateur> proprietaires) {
		this.proprietaires = proprietaires;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNumero() {
		return numero;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	
	public List<Operation> getOperations() {
		return operations;
	}
	
	public void addOperation(Operation o) {
		operations.add(o);
	}
}
