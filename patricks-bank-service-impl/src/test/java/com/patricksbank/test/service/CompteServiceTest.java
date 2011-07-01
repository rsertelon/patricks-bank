package com.patricksbank.test.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import com.excilys.patricksbank.dao.api.CompteDao;
import com.excilys.patricksbank.model.Compte;
import com.excilys.patricksbank.model.Utilisateur;
import com.excilys.patricksbank.service.api.CompteService;
import com.excilys.patricksbank.service.impl.CompteServiceImpl;

@RunWith(value = MockitoJUnitRunner.class)
public class CompteServiceTest {

	@Mock
	private CompteDao compteDao;

	@InjectMocks
	CompteService compteService = new CompteServiceImpl();

	@Test
	public void testGetComptesParUtilisateur() {
		List<Compte> listComptes = new ArrayList<Compte>();
		
		when(compteDao.getComptesParUtilisateur(any(Utilisateur.class))).thenReturn(listComptes);
		
		assertEquals(listComptes, compteService.getComptesParUtilisateur(null));
	}

	@Test
	public void testGetAndVerifyCompteDansListeCompte() {
		List<Compte> listComptes = new ArrayList<Compte>();
		Compte compte1 = new Compte();
		compte1.setIdCompte("1");
		Compte compte2 = new Compte();
		compte2.setIdCompte("2");
		listComptes.add(compte1);
		listComptes.add(compte2);

		assertEquals(compte1, compteService.getAndVerifyCompteDansListeCompte(listComptes, "1"));
		assertNull(compteService.getAndVerifyCompteDansListeCompte(listComptes, "7"));
	}

	@Test
	public void testGetCompteParId() {
		Compte compte = new Compte();
		compte.setIdCompte("1");
		
		when(compteDao.getCompteParIdAvecOperations("1")).thenReturn(compte);
		
		assertEquals(compte, compteService.getCompteParId("1"));
	}
}
