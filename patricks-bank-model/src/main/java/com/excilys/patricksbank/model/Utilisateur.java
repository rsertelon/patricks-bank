package com.excilys.patricksbank.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.Table;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idUtilisateur", unique = true, nullable = false)
	private String id;
	private String login;
	private String password;
	private String nom;
	private String prenom;
	private String locale;

	@ManyToMany(targetEntity = Role.class)
	@JoinTable(name = "lien_role_utilisateur", joinColumns = @JoinColumn(name = "idUtilisateur_fk"), inverseJoinColumns = @JoinColumn(name = "idRole_fk"))
	private List<Role> roles;

	@ManyToMany(targetEntity = Compte.class)
	@JoinTable(name = "lien_compte_utilisateur", joinColumns = @JoinColumn(name = "idUtilisateur_fk"), inverseJoinColumns = @JoinColumn(name = "idCompte_fk"))
	private List<Compte> comptes;

	public Utilisateur() {
	}

	public Utilisateur(String login, String password, String nom, String prenom) {
		super();
		this.login = login;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.comptes = new ArrayList<Compte>();
	}

	public void addCompte(Compte c) {
		this.comptes.add(c);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}

	public List<Compte> getComptes() {
		return comptes;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getLocale() {
		return locale;
	}
}
