package it.prova.myebay.repository.ruolo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import it.prova.myebay.model.Ruolo;

public interface RuoloRepository extends CrudRepository<Ruolo, Long>, JpaSpecificationExecutor<Ruolo> {

}
