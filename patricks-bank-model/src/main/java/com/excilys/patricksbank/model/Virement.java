package com.excilys.patricksbank.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.DateTime;

@Entity
@Table(name = "virement")
public class Virement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idVirement", unique = true, nullable = false)
	private String idVirement;

	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Compte.class)
	@JoinColumn(name = "idCompteSource")
	private Compte compteSource;

	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Compte.class)
	@JoinColumn(name = "idCompteCible")
	private Compte compteCible;

	// @NotEmpty
	// @Pattern(regexp = "[0-9]+([,.][0-9]{2})?")
	// @Min(0)
	private double montant;

	@Column(name = "dateVirement")
	private DateTime date;
	
	public Virement(Compte compteSource, Compte compteCible, double montant) {
		this.compteSource = compteSource;
		this.compteCible = compteCible;
		this.montant = montant;
		this.date = new DateTime();
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getIdVirement() {
		return idVirement;
	}

	public void setIdVirement(String idVirement) {
		this.idVirement = idVirement;
	}

	public Compte getCompteSource() {
		return compteSource;
	}

	public void setCompteSource(Compte compteSource) {
		this.compteSource = compteSource;
	}

	public Compte getCompteCible() {
		return compteCible;
	}

	public void setCompteCible(Compte compteCible) {
		this.compteCible = compteCible;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}
}
