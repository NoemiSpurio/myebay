package it.prova.myebay.service.annuncio;

import java.util.List;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;

public interface AnnuncioService {

	public List<Annuncio> listAllAnnunci();

	public Annuncio caricaSingoloAnnuncio(Long id);

	public void aggiorna(Annuncio annuncioInstance);

	public void rimuovi(Long id);

	public void inserisciNuovo(Annuncio annuncioInstance);
	
	public List<Annuncio> findByExample(Annuncio example);
	
	public Annuncio caricaSingoloElementoEager(Long id);
	
	public Annuncio caricaSingoloElementoConCategorie(Long id);
	
	public void compra(Long id, Utente utenteInstance);
}
