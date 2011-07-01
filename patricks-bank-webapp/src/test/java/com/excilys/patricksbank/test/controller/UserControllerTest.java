package com.excilys.patricksbank.test.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.excilys.patricksbank.controller.UserController;
import com.excilys.patricksbank.model.Compte;
import com.excilys.patricksbank.model.Utilisateur;
import com.excilys.patricksbank.service.api.CompteService;

@RunWith(value = MockitoJUnitRunner.class)
public class UserControllerTest {

	@Mock
	private CompteService compteService;
	
	@Mock
	private HttpServletRequest request;
		
	@InjectMocks
	UserController userController = new UserController();

	@SuppressWarnings("unchecked")
	@Test
	public void testHandleHome(){
		Utilisateur u = new Utilisateur();
		Compte compte1 = new Compte("001", 1, "Compte1");
		Compte compte2 = new Compte("002", 2, "Compte2");
		
		List<Compte> listComptes = new ArrayList<Compte>();
		listComptes.add(compte1);
		listComptes.add(compte2);
		
		when(compteService.getComptesParUtilisateur(any(Utilisateur.class))).thenReturn(listComptes);
		
		assertEquals("accueil", userController.handleHome(u ,request).getViewName());
		assertEquals("comptes", userController.handleHome(u ,request).getModel().get("here"));
		assertEquals(2, ((List<Compte>)userController.handleHome(u ,request).getModel().get("listComptes")).size());
		
		verify(compteService, times(3)).getComptesParUtilisateur(any(Utilisateur.class));
	}
	
	
}
