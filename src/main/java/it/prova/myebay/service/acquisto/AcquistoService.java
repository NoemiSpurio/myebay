package it.prova.myebay.service.acquisto;

import java.util.List;

import it.prova.myebay.model.Acquisto;

public interface AcquistoService {

	public List<Acquisto> listAllAcquisti();

	public Acquisto caricaSingoloAcquisto(Long id);

	public void aggiorna(Acquisto acquistoInstance);

	public void rimuovi(Long id);

	public void inserisciNuovo(Acquisto acquistoInstance);

	public List<Acquisto> findByExample(Acquisto example);
}
