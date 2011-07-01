package com.excilys.patricksbank.webservice.soap;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.patricksbank.model.Compte;
import com.excilys.patricksbank.model.Utilisateur;
import com.excilys.patricksbank.model.converter.CompteConverter;
import com.excilys.patricksbank.model.dto.CompteDTO;
import com.excilys.patricksbank.service.api.CompteService;
import com.excilys.patricksbank.service.api.UtilisateurService;
import com.excilys.patricksbank.service.api.VirementService;
import com.excilys.patricksbank.webservice.soap.contract.PatricksBank;

@WebService(endpointInterface = "com.excilys.patricksbank.webservice.soap.contract.PatricksBank")
public class PatricksBankImpl implements PatricksBank {
	@Autowired
	private CompteService compteService;

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private VirementService virementService;

	public List<CompteDTO> getComptes(String idClient) {

		Utilisateur utilisateur = utilisateurService.getUtilisateurParId(idClient);
		List<Compte> listCompte = compteService.getComptesParUtilisateur(utilisateur);
		List<CompteDTO> listCompteDTO = new ArrayList<CompteDTO>();
		CompteConverter compteConverter = new CompteConverter();
		for (Compte c : listCompte) {
			listCompteDTO.add(compteConverter.convert(c));
		}
		return listCompteDTO;
	}

	public boolean passerVirement(String idCompteSource, String idCompteCible, double montant) {

		return virementService.saveVirement(compteService.getCompteParId(idCompteSource), compteService.getCompteParId(idCompteCible), montant);
	}

}
