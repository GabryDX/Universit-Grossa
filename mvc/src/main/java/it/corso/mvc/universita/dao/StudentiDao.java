package it.corso.mvc.universita.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.corso.mvc.universita.dao.interfaces.ICorsiDiLaureaDao;
import it.corso.mvc.universita.dao.interfaces.IMaterieDao;
import it.corso.mvc.universita.dao.interfaces.IStudentiDao;
import it.corso.mvc.universita.model.AnnoAccademico;
import it.corso.mvc.universita.model.CorsoDiLaurea;
import it.corso.mvc.universita.model.Materia;
import it.corso.mvc.universita.model.MateriaPK;
import it.corso.mvc.universita.model.Studente;

@Repository
public class StudentiDao implements IStudentiDao {

	private final String[] nomeM = {"Achille","Alessandro","Antonio","Dimitri","Fabio",
			"Franco","Gabriele","Giacomo","Giovanni","Jacopo","Lorenzo","Luca",
			"Marco","Mario","Mattia","Mirko","Peter","Tommaso","Tony"};
	private final String[] nomeF = {"Clara","Elisa","Lucia","Giulia","Maria",
			"Marta","Roberta","Silvia","Valdete","Valentina"};
	private final String[] cognome = {"Arceri","Astolfi","Berti","Bianchi","Bruni",
			"Bruno","Carlino","de Meo","Di Tommasi","Favino","Ferrari","Gamella","Giorgi",
			"Gjoni","Grasso","Lucarelli","Margiotta","Menestò","Messina","Neri",
			"Nicosanti","Parker","Petrucci","Rossi","Russo","Savino","Stark",
			"Valentini","Verdi"};
	private final String[] province = {"Agrigento","Alessandria","Ancona","Aosta",
			"Aquila","Arezzo","Ascoli Piceno","Asti","Avellino","Bari","Belluno",
			"Benevento","Bergamo","Biella","Bologna","Bolzano","Brescia","Brindisi",
			"Cagliari","Caltanissetta","Campobasso","Caserta","Catania","Catanzaro",
			"Chieti","Como","Cosenza","Cremona","Crotone","Cuneo","Enna","Ferrara",
			"Firenze","Foggia","Forlì e Cesena","Frosinone","Genova","Gorizia","Grosseto",
			"Imperia","Isernia","La Spezia","Latina","Lecce","Lecco","Livorno","Lodi",
			"Lucca","Macerata","Mantova","Massa-Carrara","Matera","Messina","Milano",
			"Modena","Napoli","Novara","Nuoro","Oristano","Padova","Palermo","Parma",
			"Pavia","Perugia","Pesaro e Urbino","Pescara","Piacenza","Pisa","Pistoia",
			"Pordenone","Potenza","Prato","Ragusa","Ravenna","Reggio Calabria",
			"Reggio Emilia","Rieti","Rimini","Roma","Rovigo","Salerno","Sassari",
			"Savona","Siena","Siracusa","Sondrio","Taranto","Teramo","Terni","Torino",
			"Trapani","Trento","Treviso","Trieste","Udine","Varese","Venezia",
			"Verbano-Cusio-Ossola","Vercelli","Verona","Vibo Valentia","Vicenza",
	"Viterbo"};

	private Logger logger = Logger.getLogger(StudentiDao.class); 

	@PersistenceContext(unitName = "corsoroma") // lo ritroviamo in persistence.xml
	private EntityManager session;
	@Autowired
	ICorsiDiLaureaDao cdlDao;
	@Autowired
	IMaterieDao matDao;

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
		// 3) il parametro stuSesso puÃ² essere solo M o F
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
	@Transactional
	public Studente createRandomStu() {
		int maxStuMatricola = session.createQuery(
				"SELECT max(s.stuMatricola) FROM Studente s",
				Integer.class).getSingleResult();

		int stuMatricola = maxStuMatricola + 1;
		Studente s = new Studente();
		s.setStuMatricola(stuMatricola);

		int r1;
		int r2;
		s.setStuMatricola(stuMatricola);
		int nomeMl = nomeM.length;
		int nomeFl = nomeF.length;
		int nomil = nomeMl + nomeFl;
		r1 = ThreadLocalRandom.current().nextInt(0, nomil);
		if (r1<nomeMl) {
			r2 = ThreadLocalRandom.current().nextInt(0, nomeMl);
			s.setStuNome(nomeM[r2]);
			s.setStuSesso("M");
		} else {
			r2 = ThreadLocalRandom.current().nextInt(0, nomeFl);
			s.setStuNome(nomeF[r2]);
			s.setStuSesso("F");
		}
		r2 = ThreadLocalRandom.current().nextInt(0, cognome.length);
		s.setStuCognome(cognome[r2]);
		r2 = ThreadLocalRandom.current().nextInt(0, province.length);
		s.setStuLuogoNascita(province[r2]);

		int anno = ThreadLocalRandom.current().nextInt(1950, 2000);
		int mese = ThreadLocalRandom.current().nextInt(1, 13);
		int giorno = ThreadLocalRandom.current().nextInt(1, 30);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ITALY);
		Date dataNascita;
		try {
			dataNascita = format.parse(anno+"-"+mese+"-"+giorno);
			s.setStuDataNascita(dataNascita);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int rAA = ThreadLocalRandom.current().nextInt(2015, 2019);
		AnnoAccademico aa = new AnnoAccademico(rAA);
		s.setStuIscrizione(aa);

		List<CorsoDiLaurea> corsi = cdlDao.readCdlAll();
		int cdlMin = corsi.get(0).getCdlId();
		int cdlMax = cdlMin + corsi.size();
		int rCdl = ThreadLocalRandom.current().nextInt(cdlMin, cdlMax);
		
		s.setStuCorsoDiLaurea(cdlDao.search(rCdl));

		List<Materia> mats = matDao.readMatByCdlIdAndAa(rCdl, aa);
		List<Materia> materie = new ArrayList<>();
		int rMat;
		for (Materia m : mats) {
			logger.debug(m);
			rMat = ThreadLocalRandom.current().nextInt(0, 3); //33%
			if (rMat == 0)
				materie.add(m);
		}
		s.setStuMaterie(materie);
		
		session.persist(s);
		logger.info(String.format("Creato stu con matricola %d",
				stuMatricola));
		return s;
	}

	@Override
	public Studente createIscrizione(int stuMatricola, int matId,
			AnnoAccademico matAnnoAccademico) {
		// VERIFICA FORMALE
		if (matAnnoAccademico == null)
			throw new NullPointerException();
		// ATTENZIONE
		// c'Ã¨ una verifica che non Ã¨ formale, vale a dire
		// posso verificarla solamente dopo aver letto i dati
		// sul DB
		// il vincolo Ã¨ che uno studente non puÃ² iscriversi
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
			// IN realtÃ  dovremmo definire una nostra
			// eccezione che estende PersistenceException
			// diciamo che a livello del nostro corso
			// va bene cosÃ¬
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
		// 3) il parametro stuSesso può essere solo M o F
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

	@Override
	@Transactional
	public List<Studente> popolaStudenti() {
		List<Studente> stus = new ArrayList<Studente>();
		try {
			stus = session.createQuery(
					"SELECT s FROM Studente s",
					Studente.class)
					.getResultList();

			int r1;
			int r2;
			for (Studente s : stus) {
				int nomeMl = nomeM.length;
				int nomeFl = nomeF.length;
				int nomil = nomeMl + nomeFl;
				r1 = ThreadLocalRandom.current().nextInt(0, nomil);
				if (r1<nomeMl) {
					r2 = ThreadLocalRandom.current().nextInt(0, nomeMl);
					s.setStuNome(nomeM[r2]);
					s.setStuSesso("M");
				} else {
					r2 = ThreadLocalRandom.current().nextInt(0, nomeFl);
					s.setStuNome(nomeF[r2]);
					s.setStuSesso("F");
				}
				r2 = ThreadLocalRandom.current().nextInt(0, cognome.length);
				s.setStuCognome(cognome[r2]);
				r2 = ThreadLocalRandom.current().nextInt(0, province.length);
				s.setStuLuogoNascita(province[r2]);

				int anno = ThreadLocalRandom.current().nextInt(1950, 2000);
				int mese = ThreadLocalRandom.current().nextInt(1, 13);
				int giorno = ThreadLocalRandom.current().nextInt(1, 30);
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ITALY);
				Date dataNascita;
				try {
					dataNascita = format.parse(anno+"-"+mese+"-"+giorno);
					s.setStuDataNascita(dataNascita);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				int rAA = ThreadLocalRandom.current().nextInt(2015, 2019);
				AnnoAccademico aa = new AnnoAccademico(rAA);
				s.setStuIscrizione(aa);
				
				List<CorsoDiLaurea> corsi = cdlDao.readCdlAll();
				int cdlMin = corsi.get(0).getCdlId();
				int cdlMax = cdlMin + corsi.size();
				int rCdl = ThreadLocalRandom.current().nextInt(cdlMin, cdlMax);
				
				s.setStuCorsoDiLaurea(cdlDao.search(rCdl));
				
				List<Materia> mats = matDao.readMatByCdlIdAndAa(rCdl, aa);
				List<Materia> materie = new ArrayList<>();
				int rMat;
				for (Materia m : mats) {
					logger.debug(m);
					rMat = ThreadLocalRandom.current().nextInt(0, 3); //33%
					if (rMat == 0)
						materie.add(m);
				}
				s.setStuMaterie(materie);

				session.merge(s);
			}

		} catch (NoResultException e) {

		}

		return stus;
	}

	@Override
	@Transactional
	public List<Studente> popolaStudentiPlus() {
		List<Studente> stus = new ArrayList<Studente>();

		for (int i=0; i<100; i++) {
			stus.add(createRandomStu());
		}
		return stus;
	}

}
