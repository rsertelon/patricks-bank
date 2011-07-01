package com.excilys.patricksbank.webservice.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.patricksbank.model.Utilisateur;
import com.excilys.patricksbank.model.dto.ListeCompteDTO;
import com.excilys.patricksbank.model.dto.ResultatVirementDTO;
import com.excilys.patricksbank.service.api.CompteService;
import com.excilys.patricksbank.service.api.UtilisateurService;
import com.excilys.patricksbank.service.api.VirementService;
import com.excilys.patricksbank.webservice.rest.contract.PatricksBank;


@Path("/patricksbankservice/")
@Produces("application/xml")
public class PatricksBankImpl implements PatricksBank {

	@Autowired
    private CompteService compteService;
	
	@Autowired
	private UtilisateurService utilisateurService;
	
	@Autowired
	private VirementService virementService;
	
	@GET
	@Path("/comptesparutilisateur/{id}")
    public ListeCompteDTO getComptes(@PathParam("id") String idClient) {
    	Utilisateur utilisateur = utilisateurService.getUtilisateurParId(idClient);
    	return new ListeCompteDTO(compteService.getComptesParUtilisateur(utilisateur));	
    }
    
	@GET
	@Path("/virement/of/{montant}/from/{idCompte1}/to/{idCompte2}")
    public ResultatVirementDTO passerVirement(
    		@PathParam("idCompte1") String idCompteSource, 
    		@PathParam("idCompte2") String idCompteCible, 
    		@PathParam("montant") double montant){
		return new ResultatVirementDTO(virementService.saveVirement(
				compteService.getCompteParId(idCompteSource), 
				compteService.getCompteParId(idCompteCible), 
				montant));
    	
    }
}