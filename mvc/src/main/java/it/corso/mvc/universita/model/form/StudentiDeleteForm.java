package it.corso.mvc.universita.model.form;

import java.util.List;

import javax.validation.Valid;

import it.corso.mvc.universita.model.Studente;

public class StudentiDeleteForm {
	
	@Valid
	private List<Studente> studenti;
	private String delete;
	
	public List<Studente> getStudenti() {
		return studenti;
	}
	public void setStudenti(List<Studente> studenti) {
		this.studenti = studenti;
	}
	public String getDelete() {
		return delete;
	}
	public void setDelete(String delete) {
		this.delete = delete;
	}
	
}
