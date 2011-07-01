package com.excilys.patricksbank.test.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.patricksbank.controller.CompteController;
import com.excilys.patricksbank.model.Compte;
import com.excilys.patricksbank.model.Operation;
import com.excilys.patricksbank.service.api.CompteService;
import com.excilys.patricksbank.service.api.OperationService;

@RunWith(MockitoJUnitRunner.class)
public class CompteControllerTest {

	@Mock
	private OperationService operationService;

	@Mock
	private CompteService compteService;

	@InjectMocks
	CompteController compteController = new CompteController();

	@Test
	public void testBuildDetailCompteSiVerificationCompteErronnee() {
		when(compteService.getAndVerifyCompteDansListeCompte(null, "")).thenReturn(null);

		assertEquals("org.springframework.web.servlet.view.RedirectView: unnamed; URL [/user/home.html]",
				compteController.buildDetailCompte(null, null, null, null).getView().toString());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testBuildDetailCompteSiVerificationOketPremierAffichage() {

		Calendar cal = Calendar.getInstance();
		Double total = 150.02;

		Compte compte = new Compte("001", 1, "Compte1");
		compte.setIdCompte("1");

		int[] annees = { cal.get(Calendar.YEAR), cal.get(Calendar.YEAR) - 1, cal.get(Calendar.YEAR) - 2, cal.get(Calendar.YEAR) - 3 };

		Map<Integer, String> mois = new HashMap<Integer, String>();
		String[] tabMois = { "month.jan", "month.feb", "month.mar", "month.apr", "month.may", "month.jun", "month.jul", "month.aug", "month.sep",
				"month.oct", "month.nov", "month.dec" };
		for (int i = 0; i <= cal.get(Calendar.MONTH); i++) {
			mois.put(i, tabMois[i]);
		}

		List<Operation> listOperations = new ArrayList<Operation>();
		Operation op1 = new Operation();
		Operation op2 = new Operation();
		listOperations.add(op1);
		listOperations.add(op2);
		List<Operation> listOperationsCarte = new ArrayList<Operation>();
		Operation op3 = new Operation();
		Operation op4 = new Operation();
		listOperationsCarte.add(op3);
		listOperationsCarte.add(op4);

		when(compteService.getAndVerifyCompteDansListeCompte(any(List.class), eq(compte.getIdCompte()))).thenReturn(compte);
		when(operationService.getTotalPaiementsCarte(any(Compte.class), anyInt(), anyInt())).thenReturn(total);
		when(operationService.getOperationParCompteEtParDate(any(Compte.class), anyInt(), anyInt())).thenReturn(listOperations);
		when(operationService.getOperationCarte(any(Compte.class), anyInt(), anyInt())).thenReturn(listOperationsCarte);

		ModelAndView mav = compteController.buildDetailCompte(compte.getIdCompte(), null, null, null);

		assertEquals("detail", mav.getViewName());
		assertEquals(compte, mav.getModel().get("compte"));
		assertEquals(cal.get(Calendar.YEAR), mav.getModel().get("annee"));
		assertEquals(cal.get(Calendar.MONTH), mav.getModel().get("mois"));
		assertEquals("comptes", mav.getModel().get("here"));
		assertArrayEquals(annees, (int[]) mav.getModel().get("listeAnnees"));
		assertEquals(mois, mav.getModel().get("listeMois"));
		assertEquals(total, mav.getModel().get("totalOperationsCarte"));
		assertEquals(listOperations, mav.getModel().get("listOperations"));
		assertEquals(listOperationsCarte, mav.getModel().get("listOperationsCarte"));

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testBuildDetailCompteSiVerificationOkEtChoixDate() {
		Calendar cal = Calendar.getInstance();
		Double total = 150.02;
		int mois = 2;
		int annee = cal.get(Calendar.YEAR) - 1;

		Compte compte = new Compte("001", 1, "Compte1");
		compte.setIdCompte("1");

		int[] annees = { cal.get(Calendar.YEAR), cal.get(Calendar.YEAR) - 1, cal.get(Calendar.YEAR) - 2, cal.get(Calendar.YEAR) - 3 };

		Map<Integer, String> listMois = new HashMap<Integer, String>();
		String[] tabMois = { "month.jan", "month.feb", "month.mar", "month.apr", "month.may", "month.jun", "month.jul", "month.aug", "month.sep",
				"month.oct", "month.nov", "month.dec" };
		for (int i = 0; i <= 11; i++) {
			listMois.put(i, tabMois[i]);
		}

		List<Operation> listOperations = new ArrayList<Operation>();
		Operation op1 = new Operation();
		Operation op2 = new Operation();
		listOperations.add(op1);
		listOperations.add(op2);
		List<Operation> listOperationsCarte = new ArrayList<Operation>();
		Operation op3 = new Operation();
		Operation op4 = new Operation();
		listOperationsCarte.add(op3);
		listOperationsCarte.add(op4);

		when(compteService.getAndVerifyCompteDansListeCompte(any(List.class), eq(compte.getIdCompte()))).thenReturn(compte);
		when(operationService.getTotalPaiementsCarte(any(Compte.class), eq(annee), eq(mois))).thenReturn(total);
		when(operationService.getOperationParCompteEtParDate(any(Compte.class), eq(annee), eq(mois))).thenReturn(listOperations);
		when(operationService.getOperationCarte(any(Compte.class), eq(annee), eq(mois))).thenReturn(listOperationsCarte);

		ModelAndView mav = compteController.buildDetailCompte(compte.getIdCompte(), null, annee, mois);

		assertEquals("detail", mav.getViewName());
		assertEquals(compte, mav.getModel().get("compte"));
		assertEquals(annee, mav.getModel().get("annee"));
		assertEquals(mois, mav.getModel().get("mois"));
		assertEquals("comptes", mav.getModel().get("here"));
		assertArrayEquals(annees, (int[]) mav.getModel().get("listeAnnees"));
		assertEquals(listMois, mav.getModel().get("listeMois"));
		assertEquals(total, mav.getModel().get("totalOperationsCarte"));
		assertEquals(listOperations, mav.getModel().get("listOperations"));
		assertEquals(listOperationsCarte, mav.getModel().get("listOperationsCarte"));

	}

}
