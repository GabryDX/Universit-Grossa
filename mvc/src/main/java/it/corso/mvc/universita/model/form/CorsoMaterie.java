package it.corso.mvc.universita.model.form;

import java.util.List;

import it.corso.mvc.universita.model.CorsoDiLaurea;
import it.corso.mvc.universita.model.Materia;

public class CorsoMaterie {

	private CorsoDiLaurea cdl;
	private List<Materia> mats;
	
	public CorsoDiLaurea getCdl() {
		return cdl;
	}
	public void setCdl(CorsoDiLaurea cdl) {
		this.cdl = cdl;
	}
	public List<Materia> getMats() {
		return mats;
	}
	public void setMats(List<Materia> mats) {
		this.mats = mats;
	}

}
