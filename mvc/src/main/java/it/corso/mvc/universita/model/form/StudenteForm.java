package it.corso.mvc.universita.model.form;

import java.util.List;

import javax.validation.Valid;

import it.corso.mvc.universita.model.CorsoDiLaurea;
import it.corso.mvc.universita.model.Studente;

public class StudenteForm {
	
	@Valid
	private Studente studente;
	private List<CorsoDiLaurea> cdlList;
	private String cdlId;
	
	public Studente getStudente() {
		return studente;
	}
	public void setStudente(Studente studente) {
		this.studente = studente;
	}
	public List<CorsoDiLaurea> getCdlList() {
		return cdlList;
	}
	public void setCdlList(List<CorsoDiLaurea> cdlList) {
		this.cdlList = cdlList;
	}
	public String getCdlId() {
		return cdlId;
	}
	public void setCdlId(String cdlId) {
		this.cdlId = cdlId;
	}

}
