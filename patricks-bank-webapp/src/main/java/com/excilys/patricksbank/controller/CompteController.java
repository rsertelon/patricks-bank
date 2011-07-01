package com.excilys.patricksbank.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.excilys.patricksbank.model.Compte;
import com.excilys.patricksbank.model.Utilisateur;
import com.excilys.patricksbank.service.api.CompteService;
import com.excilys.patricksbank.service.api.OperationService;

@Controller
@RequestMapping(value = "/user/compte")
@SessionAttributes("utilisateur")
public class CompteController {
	
	@Resource
	private OperationService operationService;

	@Resource
	private CompteService compteService;
	
	private String[] tabMois = { "month.jan", "month.feb", "month.mar", "month.apr", "month.may", "month.jun", "month.jul",
			"month.aug", "month.sep", "month.oct", "month.nov", "month.dec" };

	@RequestMapping(value = "/{idCompte}/detail.html")
	public ModelAndView buildDetailCompte(
			@PathVariable String idCompte, 
			@ModelAttribute("utilisateur") Utilisateur user,
			@RequestParam(value="annee", required=false) Integer annee,
			@RequestParam(value="mois", required=false) Integer mois) {

		Map<String,Object> mapModel = new HashMap<String,Object>();
		
		List<Compte> listComptes = compteService.getComptesParUtilisateur(user);
		Compte compte = compteService.getAndVerifyCompteDansListeCompte(
				listComptes, idCompte);
		
		
		if (compte != null) {
			// TODO validation si JS désactivé pour pas afficher les opérations trop vieilles
			
			Calendar cal = Calendar.getInstance();
			Map<Integer, String> listeMois = new HashMap<Integer, String>();
			
			int moisCourant = cal.get(Calendar.MONTH);
			if(mois == null) mois = moisCourant;
			
			int anneeCourante = cal.get(Calendar.YEAR);
			if(annee == null) annee = anneeCourante;
			
			int[] tabAnnee = {anneeCourante, anneeCourante - 1, anneeCourante -2, anneeCourante - 3};
			
			int debut, fin;
			if ((annee == (anneeCourante - 1))
					|| (annee == (anneeCourante - 2))) {// Si on veut afficher l'année A-1 ou A-2
				debut = 0;
				fin = 11;
			} else if (annee == anneeCourante - 3) {// Si on veut afficher l'année A-3
				debut = moisCourant;
				fin = 11;
			} else {// Sinon on affiche l'année A
				debut = 0;
				fin = moisCourant;
			}
			
			for (int i = debut; i <= fin; i++) {
				listeMois.put(i, tabMois[i]);
			}
			
			mapModel.put("compte", compte);
			mapModel.put("listOperations", operationService.getOperationParCompteEtParDate(compte,annee,mois));
			mapModel.put("annee", annee);
			mapModel.put("mois", mois);
			mapModel.put("listeAnnees",tabAnnee);
			mapModel.put("listeMois", listeMois);
			mapModel.put("totalOperationsCarte", operationService.getTotalPaiementsCarte(compte, annee, mois));
			mapModel.put("listOperationsCarte", operationService.getOperationCarte(compte, annee, mois));
			mapModel.put("here", "comptes");
			
			return new ModelAndView("detail", mapModel);
		}

		return new ModelAndView(new RedirectView("/user/home.html", true));
	}
}
