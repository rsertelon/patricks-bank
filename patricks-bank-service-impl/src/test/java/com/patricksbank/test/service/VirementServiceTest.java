package com.patricksbank.test.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.excilys.patricksbank.dao.api.OperationDao;
import com.excilys.patricksbank.dao.api.VirementDao;
import com.excilys.patricksbank.model.Compte;
import com.excilys.patricksbank.model.Operation;
import com.excilys.patricksbank.model.Virement;
import com.excilys.patricksbank.service.api.VirementService;
import com.excilys.patricksbank.service.impl.VirementServiceImpl;


@RunWith(value = MockitoJUnitRunner.class)
public class VirementServiceTest {

	@Mock
	private VirementDao virementDao;
	
	@Mock
	private OperationDao operationDao;

	@InjectMocks
	VirementService virementService = new VirementServiceImpl();

	@Test
	public void testSaveVirement() {
		Compte compteSource = new Compte(null, 50, null);
		Compte compteCible = new Compte(null, 0, null);
		virementService.saveVirement(compteSource, compteCible, 50);
		
		verify(virementDao, times(1)).save(any(Virement.class));
		verify(operationDao, times(2)).save(any(Operation.class));
	}
	
	@Test
	public void testSave() {
		virementService.save(null);
		
		verify(virementDao, times(1)).save(any(Virement.class));
	}
}
