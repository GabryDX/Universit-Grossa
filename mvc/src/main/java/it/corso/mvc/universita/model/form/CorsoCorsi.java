package it.corso.mvc.universita.model.form;

import java.util.List;

import it.corso.mvc.universita.model.CorsoDiLaurea;

public class CorsoCorsi {

	private CorsoDiLaurea cdl;
	private List<CorsoDiLaurea> corsi;
	
	public CorsoDiLaurea getCdl() {
		return cdl;
	}
	public void setCdl(CorsoDiLaurea cdl) {
		this.cdl = cdl;
	}
	public List<CorsoDiLaurea> getCorsi() {
		return corsi;
	}
	public void setCorsi(List<CorsoDiLaurea> corsi) {
		this.corsi = corsi;
	}
}
