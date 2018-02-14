package it.corso.mvc.universita.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.corso.mvc.universita.dao.interfaces.IStudentiDao;
import it.corso.mvc.universita.model.AnnoAccademico;
import it.corso.mvc.universita.model.CorsoDiLaurea;
import it.corso.mvc.universita.model.Materia;
import it.corso.mvc.universita.model.MateriaPK;
import it.corso.mvc.universita.model.Studente;

@Repository
public class StudentiDao implements IStudentiDao {
	private Logger logger = Logger.getLogger(StudentiDao.class);

	@PersistenceContext(unitName = "corsoroma") // lo ritroviamo in persistence.xml
	private EntityManager session;

	@Override
	public Studente updateStu(Studente stu) {
		// controllo formale
		if (stu == null) 
			throw new NullPointerException();
		// il metodo merge in session fa tutto il lavoro
		Studente stu1 = session.merge(stu);
		logger.info(String.format(
				"Aggiornato studente con matricola %d",
				stu.getStuMatricola()));
		return stu1;
	}

	@Override
	@Transactional
	public Studente createStu(String stuCognome, String stuNome,
			String stuSesso, AnnoAccademico stuIscrizione,
			CorsoDiLaurea stuCorsoDiLaurea) {
		// bisognerebbe fare le verifiche formali
		// 1) tutti i parametri non null
		if (stuCognome == null || stuNome == null
				|| stuSesso == null || stuIscrizione == null
				|| stuCorsoDiLaurea == null)
			throw new NullPointerException();
		// 2) i parametri stuCognome, stuNome devono avere
		// almeno due caratteri non blank
		if (stuCognome.trim().length() < 2
				|| stuNome.trim().length() < 2)
			throw new IllegalArgumentException();
		// 3) il parametro stuSesso può essere solo M o F
		if (!stuSesso.equals("M") && !stuSesso.equals("F"))
			throw new IllegalArgumentException();
		int maxStuMatricola = session.createQuery(
				"SELECT max(s.stuMatricola) FROM Studente s",
				Integer.class).getSingleResult();
		int stuMatricola = maxStuMatricola + 1;
		Studente stu = new Studente();
		stu.setStuMatricola(stuMatricola);
		stu.setStuCognome(stuCognome);
		stu.setStuNome(stuNome);
		stu.setStuSesso(stuSesso);
		stu.setStuIscrizione(stuIscrizione);
		stu.setStuCorsoDiLaurea(stuCorsoDiLaurea);
		stu.setStuMaterie(new ArrayList<>());
		session.persist(stu);
		logger.info(String.format("Creato stu con matricola %d",
				stuMatricola));
		return stu;
	}

	@Override
	public Studente createIscrizione(int stuMatricola, int matId,
			AnnoAccademico matAnnoAccademico) {
		// VERIFICA FORMALE
		if (matAnnoAccademico == null)
			throw new NullPointerException();
		// ATTENZIONE
		// c'è una verifica che non è formale, vale a dire
		// posso verificarla solamente dopo aver letto i dati
		// sul DB
		// il vincolo è che uno studente non può iscriversi
		// a una materia per un anno di corso successivo a 
		// quello in cui lo studente si trova iscritto
		// vedi dopo
		// ESECUZIONE
		Studente stu = session.find(Studente.class, stuMatricola);
		if (stu != null) {
			MateriaPK id = new MateriaPK();
			id.setMatId(matId);
			id.setMatAnnoAccademico(matAnnoAccademico);
			Materia mat = session
					.createQuery(
							"SELECT m FROM Materia m"
									+ " WHERE m.id = :id",
							Materia.class)
					.setParameter("id", id).getSingleResult();
			// PRIMA di aggiungere la materia
			// verifico il vincolo sui dati (vedi sopra)
			int stuAnnoCorso = matAnnoAccademico.getAnno()
					- stu.getStuIscrizione().getAnno() + 1;
			// IN realtà dovremmo definire una nostra
			// eccezione che estende PersistenceException
			// diciamo che a livello del nostro corso
			// va bene così
			if (stuAnnoCorso < mat.getMatAnnoCorso())
				throw new PersistenceException();
			// ok proseguire
			mat.addMatStudente(stu);
			session.persist(stu);
			logger.info(String.format("creata iscrizione %d %d %s",
					stuMatricola, matId, matAnnoAccademico));
		}
		return stu;
	}

	@Override
	public List<Studente> getStudenti() {
		List<Studente> stus = session
				.createQuery("FROM Studente s", Studente.class)
				.getResultList();
		return stus;
	}
	
	@Override
	public Studente getStudente(int stuMatricola) {
		Studente stu = session
				.createQuery("FROM Studente s"
						+ " WHERE s.stuMatricola = :stuMatricola",
						Studente.class)
				.setParameter("stuMatricola", stuMatricola)
				.getSingleResult();
		return stu;
	}
	
	@Override
	@Transactional
	public boolean deleteStu(int stuMatricola) {
		Studente stu = new Studente();
		try {
			stu = session
					.createQuery(
							"SELECT s FROM Studente s"
							+ " WHERE s.stuMatricola = :stuMatricola",
							Studente.class)
					.setParameter("stuMatricola", stuMatricola)
					.getSingleResult();
			session.remove(stu);
			logger.info(String.format("Cancellato stu %s %s [%d]",
					stu.getStuCognome(), stu.getStuNome(), stuMatricola));
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}
	
	@Override
	@Transactional
	public boolean deleteAllStu(String stuCognome, String stuNome,
			String stuSesso, AnnoAccademico stuIscrizione,
			CorsoDiLaurea stuCorsoDiLaurea) {
		// bisognerebbe fare le verifiche formali
		// 1) tutti i parametri non null
		if (stuCognome == null || stuNome == null
				|| stuSesso == null || stuIscrizione == null
				|| stuCorsoDiLaurea == null)
			throw new NullPointerException();
		// 2) i parametri stuCognome, stuNome devono avere
		// almeno due caratteri non blank
		if (stuCognome.trim().length() < 2
				|| stuNome.trim().length() < 2)
			throw new IllegalArgumentException();
		// 3) il parametro stuSesso pu� essere solo M o F
		if (!stuSesso.equals("M") && !stuSesso.equals("F"))
			throw new IllegalArgumentException();

		List<Studente> stu = new ArrayList<Studente>();
		try {
			stu = session
					.createQuery(
							"SELECT s FROM Studente s"
							+ " WHERE s.stuCognome = :stuCognome"
							+ " AND s.stuNome = :stuNome",
							Studente.class)
					.setParameter("stuCognome", stuCognome)
					.setParameter("stuNome", stuNome)
					.getResultList();
		} catch (NoResultException e) {
			return false;
		}
		for (Studente s : stu)
			session.remove(s);
			logger.info(String.format(
				"Cancellato stu %s %s", stuCognome, stuNome));
		return true;

	}
}
