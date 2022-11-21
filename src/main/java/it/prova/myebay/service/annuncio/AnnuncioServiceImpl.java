package it.prova.myebay.service.annuncio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.myebay.exceptions.CreditoInsufficienteException;
import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;
import it.prova.myebay.repository.acquisto.AcquistoRepository;
import it.prova.myebay.repository.annuncio.AnnuncioRepository;
import it.prova.myebay.repository.utente.UtenteRepository;

@Service
public class AnnuncioServiceImpl implements AnnuncioService {

	@Autowired
	private AnnuncioRepository annuncioRepository;
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private AcquistoRepository acquistoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Annuncio> listAllAnnunci() {
		return (List<Annuncio>) annuncioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Annuncio caricaSingoloAnnuncio(Long id) {
		return annuncioRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Annuncio annuncioInstance) {
		annuncioRepository.save(annuncioInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Long id) {
		annuncioRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Annuncio annuncioInstance) {
		annuncioRepository.save(annuncioInstance);
	}

	@Override
	public List<Annuncio> findByExample(Annuncio example) {
		return annuncioRepository.findByExample(example);
	}

	@Override
	@Transactional(readOnly = true)
	public Annuncio caricaSingoloElementoEager(Long id) {
		return annuncioRepository.findSingleAnnuncioEager(id);
	}

	@Override
	public Annuncio caricaSingoloElementoConCategorie(Long id) {
		return annuncioRepository.findByIdConCategorie(id).orElse(null);
	}

	@Override
	public void compra(Long id, Utente utenteInstance) {
		Annuncio annuncioDaAcquistare = annuncioRepository.findById(id).orElse(null);
		
		if(annuncioDaAcquistare == null)
			throw new RuntimeException("L'annuncio che stai provando ad acquistare non esiste, mi dispiace!.");
		
		if(utenteInstance == null || utenteRepository.findById(utenteInstance.getId()).orElse(null) == null)
			throw new RuntimeException("Ops! Non ti troviamo nell'elenco!");
		
		if(utenteInstance.getCreditoResiduo() < annuncioDaAcquistare.getPrezzo())
			throw new CreditoInsufficienteException("Credito residuo insufficiente per effettuare l'acquisto.");
		
		int creditoAggiornato = utenteInstance.getCreditoResiduo() - annuncioDaAcquistare.getPrezzo();
		
		utenteInstance.setCreditoResiduo(creditoAggiornato);
		utenteRepository.save(utenteInstance);
		
		annuncioDaAcquistare.setAperto(false);
		
		Acquisto nuovoAcquisto = new Acquisto(annuncioDaAcquistare.getTestoAnnuncio(), new Date(), annuncioDaAcquistare.getPrezzo(), utenteInstance);
		
		acquistoRepository.save(nuovoAcquisto);
	}

}
