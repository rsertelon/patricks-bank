package com.excilys.patricksbank.model.converter;

import org.springframework.core.convert.converter.Converter;

import com.excilys.patricksbank.model.Compte;
import com.excilys.patricksbank.model.dto.CompteDTO;

public class CompteConverter implements Converter<Compte, CompteDTO> {

	@Override
	public CompteDTO convert(Compte compte) {

		return new CompteDTO(compte);
	}

	public Compte convert(CompteDTO compteDTO) {

		return new Compte(compteDTO);

	}

}
