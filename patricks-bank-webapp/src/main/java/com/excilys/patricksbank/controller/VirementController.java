package com.excilys.patricksbank.controller;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.excilys.patricksbank.model.Compte;
import com.excilys.patricksbank.model.Utilisateur;
import com.excilys.patricksbank.model.dto.CompteDTO;
import com.excilys.patricksbank.service.api.CompteService;
import com.excilys.patricksbank.service.api.VirementService;
import com.excilys.utils.web.flash.FlashScope;

@Controller
@RequestMapping(value = "/user/virement/")
@SessionAttributes("utilisateur")
public class VirementController {

	@Resource
	private CompteService compteService;

	@Resource
	private VirementService virementService;

	@RequestMapping(value = "/virement.html", method = RequestMethod.GET)
	public ModelAndView buildVirementPage(@ModelAttribute("utilisateur") Utilisateur user) {
		Map<String, Object> mapModel = new HashMap<String, Object>();
		
		List<Compte> listComptes = compteService.getComptesParUtilisateur(user);
		
		if (listComptes.size() <= 1) {
			mapModel.put("messageErreurNbComptes", "transfer.error.lessthan2accounts");
		}
		
		mapModel.put("listComptes", listComptes);
		mapModel.put("here", "virement");
		
		
		return new ModelAndView("virement", mapModel);
	}

	@RequestMapping(value = "/virement.html", method = RequestMethod.POST)
	public ModelAndView redirigerFormulaire(@RequestParam(value = "compteSource", required = false) String idEmetteur,
			@RequestParam(value = "compteCible", required = false) String idReceveur,
			@RequestParam(value = "montant", required = false) String montant, @ModelAttribute("utilisateur") Utilisateur user) {

		Map<String, Object> mapModel = new HashMap<String, Object>();
		Compte compteSource = compteService.getCompteParId(idEmetteur);
		Compte compteCible = compteService.getCompteParId(idReceveur);

		if (!montant.matches("^([1-9]+[0-9]*([.,][0-9]{1,2})?|0[.,][0-9]{1,2})$")) {
			FlashScope.bind("messageErrorValidationServer").to("transfer.error.validation.server");
			return new ModelAndView(new RedirectView("/user/home.html", true));
		}

		Double montantDouble = Double.parseDouble(montant.replaceAll(",", "."));
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);

		if (compteSource.getMontant() < montantDouble) {

			List<Compte> listComptes = compteService.getComptesParUtilisateur(user);
			mapModel.put("listComptes", listComptes);
			mapModel.put("messageDecouvert", "transfer.error.nomoney");
			mapModel.put("here", "virement");

			return new ModelAndView("virement", mapModel);

		} else {

			FlashScope.bind("compteCible").to(compteCible);
			FlashScope.bind("compteSource").to(compteSource);
			FlashScope.bind("montant").to(montantDouble);
			FlashScope.bind("montantSourceApres").to(df.format(compteSource.getMontant() - montantDouble));
			FlashScope.bind("montantCibleApres").to(df.format(compteCible.getMontant() + montantDouble));
			return new ModelAndView(new RedirectView("./confirmation.html", true));
		}
	}

	@RequestMapping(value = "/confirmation.html")
	public ModelAndView createConfirmationView(HttpServletRequest request) {

		Map<String, Object> mapModel = new HashMap<String, Object>();

		Compte compteCible = (Compte) request.getAttribute("compteCible");
		Compte compteSource = (Compte) request.getAttribute("compteSource");
		Double montant = (Double) request.getAttribute("montant");
		String montantSourceApres = (String) request.getAttribute("montantSourceApres");
		String montantCibleApres = (String) request.getAttribute("montantCibleApres");

		if (compteCible != null && compteSource != null)
			if (compteCible.getIdCompte().equals(compteSource.getIdCompte()) || montant <= 0) {
				FlashScope.bind("messageErrorValidationServer").to("transfer.error.validation.server");
				return new ModelAndView(new RedirectView("/user/home.html", true));
			}

		FlashScope.bind("compteCible").to(compteCible);
		FlashScope.bind("compteSource").to(compteSource);
		FlashScope.bind("montant").to(montant);
		FlashScope.bind("montantSourceApres").to(montantSourceApres);
		FlashScope.bind("montantCibleApres").to(montantCibleApres);

		mapModel.put("compteCible", compteCible);
		mapModel.put("compteSource", compteSource);
		mapModel.put("montant", montant);
		mapModel.put("montantSourceApres", montantSourceApres);
		mapModel.put("montantCibleApres", montantCibleApres);
		mapModel.put("here", "virement");

		if (request.getParameter("locale") != null)
			return new ModelAndView(new RedirectView("./confirmation.html", true));
		else
			return new ModelAndView("confirmation",mapModel);
	}

	@RequestMapping(value = "/confirme.html")
	public ModelAndView confirmVirement(@RequestParam(value = "compteSource", required = false) String idCompteSource,
			@RequestParam(value = "compteCible", required = false) String idCompteCible,
			@RequestParam(value = "montant", required = false) String montant, @ModelAttribute("utilisateur") Utilisateur user) {

		if (idCompteCible == null || idCompteSource == null || montant == null || idCompteCible.equals("") || idCompteSource.equals("")
				|| montant.equals(""))
			return new ModelAndView(new RedirectView("/user/home.html", true));

		Map<String, Object> mapModel = new HashMap<String, Object>();

		virementService.saveVirement(
				compteService.getCompteParId(idCompteSource), 
				compteService.getCompteParId(idCompteCible), 
				Double.parseDouble(montant));
		
		FlashScope.bind("messageConfirmation").to("confirmation.message");

		return new ModelAndView(new RedirectView("/user/home.html", true), mapModel);
	}

	@RequestMapping(value = "/jsoncall.html", method = RequestMethod.GET)
	public @ResponseBody
	List<CompteDTO> sendJSON(@RequestParam String idCompte, @ModelAttribute("utilisateur") Utilisateur user) {

		List<Compte> listComptes = compteService.getComptesParUtilisateur(user);
		Compte c = compteService.getAndVerifyCompteDansListeCompte(listComptes, idCompte);
		if (c != null) {
			listComptes.remove(c);

			return CompteDTO.transformListComptes(listComptes);
		}
		return null;
	}

}
