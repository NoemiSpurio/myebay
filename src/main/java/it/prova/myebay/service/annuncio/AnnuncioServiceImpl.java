package it.prova.myebay.service.annuncio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.repository.annuncio.AnnuncioRepository;

@Service
public class AnnuncioServiceImpl implements AnnuncioService {

	@Autowired
	private AnnuncioRepository annuncioRepository;

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

}
