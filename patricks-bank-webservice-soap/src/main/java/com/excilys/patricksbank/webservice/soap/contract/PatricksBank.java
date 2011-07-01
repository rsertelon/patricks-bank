package com.excilys.patricksbank.webservice.soap.contract;

import java.util.List;

import javax.jws.WebService;

import com.excilys.patricksbank.model.dto.CompteDTO;

@WebService
public interface PatricksBank {

	List<CompteDTO> getComptes(String idClient);
	boolean passerVirement(String idCompteSource, String idCompteCible, double montant);
}
