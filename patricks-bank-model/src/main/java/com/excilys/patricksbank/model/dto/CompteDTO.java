package com.excilys.patricksbank.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.excilys.patricksbank.model.Compte;

public class CompteDTO {
	private String idCompte;
	private double montant;
	private String numero;
	private String libelle;

	public CompteDTO() {

	}

	public CompteDTO(Compte c) {
		this.idCompte = c.getIdCompte();
		this.libelle = c.getLibelle();
		this.montant = c.getMontant();
		this.numero = c.getNumero();
	}

	public static List<CompteDTO> transformListComptes(List<Compte> listComptes) {
		List<CompteDTO> listComptesDTO = new ArrayList<CompteDTO>();
		for (Compte c : listComptes) {
			listComptesDTO.add(new CompteDTO(c));
		}
		return listComptesDTO;
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
