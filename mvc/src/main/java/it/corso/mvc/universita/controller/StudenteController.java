package it.corso.mvc.universita.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.corso.mvc.universita.dao.interfaces.ICorsiDiLaureaDao;
import it.corso.mvc.universita.dao.interfaces.IStudentiDao;
import it.corso.mvc.universita.model.AnnoAccademico;
import it.corso.mvc.universita.model.CorsoDiLaurea;
import it.corso.mvc.universita.model.Studente;
import it.corso.mvc.universita.model.form.StudenteForm;
import it.corso.mvc.universita.model.form.StudentiDeleteForm;

@Controller
@RequestMapping("/universita")
public class StudenteController {

	@Autowired
	private ICorsiDiLaureaDao cdlDao;
	@Autowired
	private IStudentiDao stuDao;


	@RequestMapping(value="studenti",method = RequestMethod.GET)
	public ModelAndView getStudenti() {
		List<Studente> stuLista = stuDao.getStudenti();
		ModelAndView modelAndView = new ModelAndView("studenti", "stuList", stuLista);
		return modelAndView;
	}

	@RequestMapping(value="aggiungiStudente",method = RequestMethod.GET)
	public ModelAndView setStudente() {
		StudenteForm stucdl = new StudenteForm();
		stucdl.setStudente(new Studente());
		stucdl.setCdlList(cdlDao.readCdlAll());

		ModelAndView modelAndView = new ModelAndView("formStudente", "stucdl", stucdl);

		//			List<CorsoDiLaurea> cdlList = cdlDao.readCdlAll();
		//			modelAndView.addObject("cdlList", cdlList);
		return modelAndView;
	}

	@RequestMapping(value="studentequery",method = RequestMethod.POST)
	public ModelAndView aggiuntaStudente(@Valid @ModelAttribute("stucdl") StudenteForm stucdl,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			stucdl.setCdlList(cdlDao.readCdlAll());
			return new ModelAndView("formStudente", "stu", stucdl);
		}

		int cdlId = 0;
		try {
			cdlId = Integer.valueOf(stucdl.getCdlId());
		} catch (Exception e) {

		}
		CorsoDiLaurea cdl = cdlDao.search(cdlId);
		Studente stu = stucdl.getStudente();
		AnnoAccademico aa = new AnnoAccademico(2018);
		Studente risultato = stuDao.createStu(stu.getStuCognome(), stu.getStuNome(), stu.getStuSesso(), aa, cdl);
		ModelAndView modelAndView = new ModelAndView("formStudenteResult", "stu", risultato);
		return modelAndView;
	}
	
	@RequestMapping(value="cercaStudente",method = RequestMethod.GET)
	public ModelAndView scegliStudente() {
		List<Studente> stuList = stuDao.getStudenti();
		StudentiDeleteForm sdf = new StudentiDeleteForm();
		sdf.setStudenti(stuList);
//		Studente stu = new Studente();
		ModelAndView modelAndView = new ModelAndView("deleteStudente", "sdf", sdf);
		return modelAndView;
	}
	
	@RequestMapping(value="mostraStudenteQuery",method = RequestMethod.POST)
	public ModelAndView mostraStudente(@Valid @ModelAttribute StudentiDeleteForm sdf,
			ModelMap model) {
		String matricole = sdf.getDelete();
		String[] splitList = matricole.split(";");
		List<Studente> stuList = new ArrayList<>();
		for (String s : splitList) {
			if (!s.equals("")) {
				int matricola = Integer.valueOf(s);
				Studente stu = stuDao.getStudente(matricola);
				stuList.add(stu);
			}
		}

		StudentiDeleteForm sdf2 = new StudentiDeleteForm();
		sdf2.setStudenti(stuList);
		sdf2.setDelete(matricole.substring(1));
		ModelAndView modelAndView = new ModelAndView("deleteStudenteResult", "sdf2", sdf2);
		return modelAndView;
	}
	
	@RequestMapping(value="deleteStudenteQuery",method = RequestMethod.POST)
	public ModelAndView eliminaStudente(@Valid @ModelAttribute StudentiDeleteForm sdf2,
			ModelMap model) {

		String matricole = sdf2.getDelete();
		String[] splitList = matricole.split(";");
		for (String s : splitList) {
			try {
				int matricola = Integer.valueOf(s);
				stuDao.deleteStu(matricola);
			} catch (Exception e) {
				
			}
		}
		
		List<Studente> stuList = stuDao.getStudenti();
		ModelAndView modelAndView = new ModelAndView("studenti", "stuList", stuList);
		return modelAndView;
	}
	
	@RequestMapping(value="aggiungiStudenteRandom",method = RequestMethod.GET)
	public ModelAndView aggiungiStudenteRandom() {
		Studente stu = stuDao.createRandomStu();
		ModelAndView modelAndView = new ModelAndView("formStudenteResult", "stu", stu);
		return modelAndView;
	}
	
	@RequestMapping(value="popola",method = RequestMethod.GET)
	public ModelAndView popolaStudenti() {
		List<Studente> stuLista = stuDao.popolaStudenti();
		
		ModelAndView modelAndView = new ModelAndView("studenti", "stuList", stuLista);
		return modelAndView;
	}
	
	@RequestMapping(value="popolaPlus",method = RequestMethod.GET)
	public ModelAndView popolaStudentiPlus() {
		List<Studente> stuLista = stuDao.popolaStudentiPlus();
		
		ModelAndView modelAndView = new ModelAndView("studenti", "stuList", stuLista);
		return modelAndView;
	}
	
}
