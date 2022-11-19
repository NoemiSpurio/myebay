package it.prova.myebay.repository.acquisto;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import it.prova.myebay.model.Acquisto;

public interface AcquistoRepository extends CrudRepository<Acquisto, Long>, JpaSpecificationExecutor<Acquisto> {

}
