package it.prova.myebay.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.myebay.model.Acquisto;
import it.prova.myebay.repository.acquisto.AcquistoRepository;

@Service
public class AcquistoServiceImpl implements AcquistoService {
	
	@Autowired
	AcquistoRepository acquistoRepository;
	
	@Autowired
	UtenteService utenteService;

	@Transactional(readOnly = true)
	public List<Acquisto> listAllElements() {
		return (List<Acquisto>) acquistoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Acquisto caricaSingoloElemento(Long id) {
		return acquistoRepository.findById(id).orElse(null);

	}



	@Override
	@Transactional
	public void aggiorna(Acquisto acquistoInstance) {
		acquistoRepository.save(acquistoInstance);

	}

	@Override
	@Transactional
	public void inserisciNuovo(Acquisto acquistoInstance) {
		acquistoRepository.save(acquistoInstance);

		
	}

	@Override
	@Transactional
	public void rimuovi(Long idToDelete) {
		acquistoRepository.deleteById(idToDelete);

		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Acquisto> findByExample(Acquisto example) {
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Acquisto> cercaPerUtente_Username() {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		
		
		return acquistoRepository.findAllByUtente_Username(username);
	}

	
	

}
