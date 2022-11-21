package it.prova.myebay.service.acquisto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.myebay.model.Acquisto;
import it.prova.myebay.repository.acquisto.AcquistoRepository;

@Service
public class AcquistoServiceImpl implements AcquistoService {

	@Autowired
	private AcquistoRepository acquistoRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Acquisto> listAllAcquisti() {
		return (List<Acquisto>) acquistoRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Acquisto caricaSingoloAcquisto(Long id) {
		return acquistoRepository.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void aggiorna(Acquisto acquistoInstance) {
		acquistoRepository.save(acquistoInstance);
	}

	@Transactional
	@Override
	public void rimuovi(Long id) {
		acquistoRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void inserisciNuovo(Acquisto acquistoInstance) {
		acquistoRepository.save(acquistoInstance);
	}

	@Override
	public List<Acquisto> findByExample(Acquisto example) {
		return acquistoRepository.findByExample(example);
	}

	@Override
	public List<Acquisto> findAllAcquistiEagerUtente(Long id) {
		return acquistoRepository.findAcquistiUtente(id);
	}

}
