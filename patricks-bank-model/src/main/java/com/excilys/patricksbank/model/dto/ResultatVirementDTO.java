package com.excilys.patricksbank.model.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="virement")
public class ResultatVirementDTO {
	@SuppressWarnings("unused")
	@XmlElement(name="estEffectue")
	private boolean estEffectue;

	public ResultatVirementDTO(){}
	
	public ResultatVirementDTO(boolean estEffectue) {
		this.estEffectue = estEffectue;
	}
}
