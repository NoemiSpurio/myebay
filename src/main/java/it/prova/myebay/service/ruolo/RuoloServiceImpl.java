package it.prova.myebay.service.ruolo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.myebay.model.Ruolo;
import it.prova.myebay.repository.ruolo.RuoloRepository;

@Service
public class RuoloServiceImpl implements RuoloService {

	@Autowired
	private RuoloRepository ruoloRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Ruolo> listAll() {
		return (List<Ruolo>)ruoloRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Ruolo caricaSingoloElemento(Long id) {
		return ruoloRepository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Ruolo ruoloInstance) {
		// TODO Auto-generated method stub

	}

	@Transactional
	@Override
	public void inserisciNuovo(Ruolo ruoloInstance) {
		ruoloRepository.save(ruoloInstance);
	}

	@Override
	public void rimuovi(Long idToDelete) {
		// TODO Auto-generated method stub

	}

}
