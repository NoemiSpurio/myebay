package it.prova.myebay.repository.utente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.myebay.model.Utente;

public class CustomUtenteRepositoryImpl implements CustomUtenteRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Utente> findByExample(Utente example) {
		Map<String, Object> parameterMap = new HashMap<>();
		List<String> whereClause = new ArrayList<>();

		StringBuilder queryBuilder = new StringBuilder("select u from Utente u where u.id = u.id");

		if (!example.getNome().isEmpty()) {
			whereClause.add(" u.nome like :nome ");
			parameterMap.put("nome", "%" + example.getNome() + "%");
		}
		if (!example.getCognome().isEmpty()) {
			whereClause.add(" u.cognome like :cognome ");
			parameterMap.put("cognome", "%" + example.getCognome() + "%");
		}
		if (!example.getUsername().isEmpty()) {
			whereClause.add(" u.username like :username ");
			parameterMap.put("username", "%" + example.getUsername() + "%");
		}
		if (example.getDateCreated() != null) {
			whereClause.add(" u.dataCreated >= :dataCreated");
			parameterMap.put("dataCreated", example.getDateCreated());
		}
		if (example.getStato() != null) {
			whereClause.add(" u.stato like :stato");
			parameterMap.put("stato", example.getStato());
		}

		queryBuilder.append(!whereClause.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClause, "and"));
		TypedQuery<Utente> query = entityManager.createQuery(queryBuilder.toString(), Utente.class);

		for (String key : parameterMap.keySet()) {
			query.setParameter(key, parameterMap.get(key));
		}

		return query.getResultList();
	}

}
