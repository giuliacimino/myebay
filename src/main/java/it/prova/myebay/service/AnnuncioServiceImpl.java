package it.prova.myebay.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;
import it.prova.myebay.repository.acquisto.AcquistoRepository;
import it.prova.myebay.repository.annuncio.AnnuncioRepository;

@Service
public class AnnuncioServiceImpl implements AnnuncioService {
	@Autowired
	AnnuncioRepository annuncioRepository;
	
	@Autowired
	UtenteService utenteService;

	@Override
	public List<Annuncio> listAllElements() {
		return (List<Annuncio>) annuncioRepository.findAll();
	}

	@Override
	public Annuncio caricaSingoloElemento(Long id) {
		return annuncioRepository.findById(id).orElse(null);

	}

	@Override
	@Transactional
	public void aggiorna(Annuncio annuncioInstance) {
		Annuncio annuncioFromDb = annuncioRepository.findById(annuncioInstance.getId()).orElseThrow();
		//faccio la copia solo dei campi che mi servono
		annuncioInstance.setUtenteInserimento(annuncioFromDb.getUtenteInserimento());
		annuncioRepository.save(annuncioInstance);
	}

	@Override
	public void inserisciNuovo(Annuncio annuncioInstance) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		Utente utenteFromDb= utenteService.findByUsername(username);
		annuncioInstance.setUtenteInserimento(utenteFromDb);
		annuncioInstance.setAperto(true);
		annuncioInstance.setData(LocalDate.now());
		annuncioRepository.save(annuncioInstance);

		
	}

	@Override
	public void rimuovi(Long idToDelete) {
		annuncioRepository.deleteById(idToDelete);

		
	}

	@Override
	public List<Annuncio> findByExample(Annuncio example) {
		return annuncioRepository.findByExample(example);

	}

	@Override
	@Transactional(readOnly = true)
	public Annuncio caricaAnnuncioConCategorie(Long id) {
		return annuncioRepository.findByIdConCategorie(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Annuncio> cercaPerUtente_Username() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		
		return annuncioRepository.findByUtente_Username(username);
	}

}
