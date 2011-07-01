package com.patricksbank.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.excilys.patricksbank.dao.api.UtilisateurDao;
import com.excilys.patricksbank.model.Utilisateur;
import com.excilys.patricksbank.service.api.UtilisateurService;
import com.excilys.patricksbank.service.impl.UtilisateurServiceImpl;

@RunWith(value = MockitoJUnitRunner.class)
public class UtilisateurServiceTest {

	@Mock
	private UtilisateurDao utilisateurDao;

	@InjectMocks
	UtilisateurService utilisateurService = new UtilisateurServiceImpl();

	@Test
	public void testGetUtilisateurParLogin() {
		Utilisateur utilisateur = new Utilisateur("user", "user", "user", "user");
		
		when(utilisateurDao.getUtilisateurParLogin(any(String.class))).thenReturn(utilisateur);
		
		assertEquals(utilisateur, utilisateurService.getUtilisateurParLogin("user"));
	}

	@Test
	public void testUpdate() {
		utilisateurService.update(null);
		
		verify(utilisateurDao, times(1)).update(any(Utilisateur.class));
	}

	@Test
	public void testGetUtilisateurParId() {
		String id = "2";
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(id);
		
		when(utilisateurDao.getUtilisateurParId(id)).thenReturn(utilisateur);
		
		assertEquals(utilisateur, utilisateurService.getUtilisateurParId(id));
	}

}
