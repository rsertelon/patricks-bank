package com.excilys.patricksbank.model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.excilys.patricksbank.model.Compte;

@XmlRootElement(name = "comptes")
public class ListeCompteDTO {

	@XmlElement(name = "compte")
	private List<CompteDTO> compte;

	public ListeCompteDTO(List<Compte> liste) {
		if (compte == null)
			compte = new ArrayList<CompteDTO>();
		for (Compte c : liste) {
			this.compte.add(new CompteDTO(c));
		}
	}

	public ListeCompteDTO() {}
}
