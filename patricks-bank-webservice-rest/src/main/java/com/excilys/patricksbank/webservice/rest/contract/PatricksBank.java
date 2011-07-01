package com.excilys.patricksbank.webservice.rest.contract;

import com.excilys.patricksbank.model.dto.ListeCompteDTO;
import com.excilys.patricksbank.model.dto.ResultatVirementDTO;

public interface PatricksBank {

	ListeCompteDTO getComptes(String id);

	ResultatVirementDTO passerVirement(String idCompteSource, String idCompteCible, double montant);
}
