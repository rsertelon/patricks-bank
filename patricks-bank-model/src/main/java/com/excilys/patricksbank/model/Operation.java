package com.excilys.patricksbank.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.DateTime;

@Entity
@Table(name = "operation")
public class Operation {
	public enum TypeOperation {
		RETRAIT, VIREMENT_EMIS, VIREMENT_RECU, PAIEMENT_CARTE, CHEQUE, REMISE_CHEQUE,
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idOperation", unique = true, nullable = false)
	private String idOperation;

	private String libelle;
	private double montant;

	@Enumerated(EnumType.STRING)
	private TypeOperation typeOperation;

	@Column(name = "dateOperation")
	private DateTime date;

	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Compte.class)
	@JoinColumn(name = "idCompte")
	private Compte compte;

	public Operation() {
	}

	public Operation(Compte compteSource, Compte compteCible, double montant,
			TypeOperation type) {
		this.typeOperation = type;
		this.compte = (type.equals(TypeOperation.VIREMENT_EMIS)) ? compteSource	: compteCible;
		this.date = new DateTime();
		this.libelle = compteSource.getLibelle() + " > " + compteCible.getLibelle();
		this.montant = montant;
	}

	public Operation(String idOperation, String libelle, double montant,
			TypeOperation typeOperation, DateTime date, Compte compte) {
		this.idOperation = idOperation;
		this.libelle = libelle;
		this.montant = montant;
		this.typeOperation = typeOperation;
		this.date = date;
		this.compte = compte;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public TypeOperation getTypeOperation() {
		return typeOperation;
	}

	public void setTypeOperation(TypeOperation typeOperation) {
		this.typeOperation = typeOperation;
	}
	
	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public void setIdOperation(String idOperation) {
		this.idOperation = idOperation;
	}

	public String getIdOperation() {
		return idOperation;
	}

	public String getDateFormatee() {
		return date.toString("dd/MM/yyyy");
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}
}
