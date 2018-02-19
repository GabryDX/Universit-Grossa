package it.corso.mvc.universita.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.corso.mvc.universita.dao.interfaces.ICorsiDiLaureaDao;
import it.corso.mvc.universita.dao.interfaces.IMaterieDao;
import it.corso.mvc.universita.model.CorsoDiLaurea;
import it.corso.mvc.universita.model.Materia;
import it.corso.mvc.universita.model.form.CorsoCorsi;
import it.corso.mvc.universita.model.form.CorsoMaterie;

@Controller
@RequestMapping("/universita")
public class UniversitaController {
	
	@Autowired
	private ICorsiDiLaureaDao cdlDao;
	@Autowired
	private IMaterieDao matDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "universita";
	}
	
	@RequestMapping(value="corsi",method = RequestMethod.GET)
	public ModelAndView getCorsiDiLaurea() {
		List<CorsoDiLaurea> cdls = cdlDao.readCdlAll();
		ModelAndView modelAndView = new ModelAndView("corsi", "corsi", cdls);
		return modelAndView;
	}
	
	@RequestMapping(value="corsoform",method = RequestMethod.GET)
	public ModelAndView getCorsoDiLaurea() {
		CorsoCorsi cc = new CorsoCorsi();
		cc.setCorsi(cdlDao.readCdlAll());
//		CorsoDiLaurea cdl = new CorsoDiLaurea();
		cc.setCdl(new CorsoDiLaurea());
//		ModelAndView modelAndView = new ModelAndView("formCorso", "corso", cdl);
		ModelAndView modelAndView = new ModelAndView("formCorso", "cc", cc);
		return modelAndView;
	}
	
	@RequestMapping(value="corsoquery",method = RequestMethod.POST)
	public ModelAndView ricercaCorsoDiLaurea(@Valid @ModelAttribute("cc") 
			CorsoCorsi cc, BindingResult result) {
		
		if (result.hasErrors()) {
			return new ModelAndView("formCorso", "cc", cc);
		}
		
		CorsoDiLaurea corso = cc.getCdl();
		CorsoDiLaurea risultato = cdlDao.search(corso.getCdlId());
		if (risultato == null) {
			risultato = new CorsoDiLaurea();
			risultato.setCdlNome("CORSO NON TROVATO");
			risultato.setCdlId(corso.getCdlId());
		}
		List<Materia> mats = matDao.readMatByCdlId(corso.getCdlId());
		CorsoMaterie cm = new CorsoMaterie();
		cm.setCdl(risultato);
		cm.setMats(mats);
		ModelAndView modelAndView = new ModelAndView("formCorsoResult", "cm", cm);
		return modelAndView;
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public ModelAndView getBookArray(@PathVariable int id) {
		String sid = "10" + id;
		int newId = Integer.valueOf(sid);
		CorsoDiLaurea cdl = cdlDao.search(newId);
		ModelAndView modelAndView = new ModelAndView("universita2", "corsoDiLaurea", cdl);
		return modelAndView;
	}
	
	//Materia
	@RequestMapping(value="materie",method = RequestMethod.GET)
	public ModelAndView getMaterie() {
		List<Materia> mats = matDao.readMatAll();
		ModelAndView modelAndView = new ModelAndView("materie", "materie", mats);
		return modelAndView;
	}
	
}
