package com.patricksbank.test.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import com.excilys.patricksbank.dao.api.OperationDao;
import com.excilys.patricksbank.model.Compte;
import com.excilys.patricksbank.model.Operation;
import com.excilys.patricksbank.service.api.OperationService;
import com.excilys.patricksbank.service.impl.OperationServiceImpl;

@RunWith(value = MockitoJUnitRunner.class)
public class OperationServiceTest {

	@Mock
	private OperationDao operationDao;

	@InjectMocks
	OperationService operationService = new OperationServiceImpl();

	@Test
	public void testGetOperationParCompteEtParDate() {
		List<Operation> listOperations = new ArrayList<Operation>();
		Operation op1 = new Operation();
		Operation op2 = new Operation();
		listOperations.add(op1);
		listOperations.add(op2);
		
		when(operationDao.getOperationParCompteEtParDate(any(Compte.class), anyInt(), anyInt())).thenReturn(listOperations);
		
		assertEquals(listOperations, operationService.getOperationParCompteEtParDate(null, 0, 0));
	}

	@Test
	public void testGetTotalPaiementsCarte() {
		List<Operation> listOperations = new ArrayList<Operation>();
		Operation op1 = new Operation("1", null, 152.25, null, null, null);
		Operation op2 = new Operation("2", null, 152.25, null, null, null);
		listOperations.add(op1);
		listOperations.add(op2);
		
		when(operationDao.getPaiementsCarte(any(Compte.class), anyInt(), anyInt())).thenReturn(listOperations);
		
		assertEquals(304.50, operationService.getTotalPaiementsCarte(null, 0, 0), 0);
	}

	@Test
	public void testGetOperationsCarte() {
		List<Operation> listOperations = new ArrayList<Operation>();
		
		when(operationDao.getPaiementsCarte(any(Compte.class), anyInt(), anyInt())).thenReturn(listOperations);
		
		assertEquals(listOperations, operationService.getOperationCarte(null, 0, 0));
	}

}
