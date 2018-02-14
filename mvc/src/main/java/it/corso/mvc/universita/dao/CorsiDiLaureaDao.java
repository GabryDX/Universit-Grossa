package it.corso.mvc.universita.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import it.corso.mvc.universita.dao.interfaces.ICorsiDiLaureaDao;
import it.corso.mvc.universita.model.CorsoDiLaurea;

@Repository
public class CorsiDiLaureaDao implements ICorsiDiLaureaDao {
	private Logger logger = Logger.getLogger(CorsiDiLaureaDao.class);

	@PersistenceContext(unitName="corsoroma") // lo ritroviamo in persistence.xml
	private EntityManager session;

	@Override
	public List<CorsoDiLaurea> readCdlAll() {
		List<CorsoDiLaurea> cors = session
			.createQuery("FROM CorsoDiLaurea c", CorsoDiLaurea.class)
			.getResultList();
		logger.info(String.format("readCdlAll: letti %d elementi cdl", cors.size()));
		return cors;
	}

	@Override
	public CorsoDiLaurea search(int cdlId) {
		logger.debug("Ricerca ID Corso " + cdlId);
		CorsoDiLaurea cdl;
		try {
		cdl = session
				.createQuery("FROM CorsoDiLaurea c"
						+ " WHERE c.cdlId = :cdlId", 
						CorsoDiLaurea.class)
				.setParameter("cdlId", cdlId)
				.getSingleResult();
		} catch (Exception e) {
			logger.error("Errore conversione ID Corso " + cdlId, e);
			cdl = null;
		}
		return cdl;
	}
}
