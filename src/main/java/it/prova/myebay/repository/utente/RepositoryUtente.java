package it.prova.myebay.repository.utente;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.myebay.model.Utente;

public interface RepositoryUtente extends PagingAndSortingRepository<Utente, Long>, JpaSpecificationExecutor<Utente> {

}
