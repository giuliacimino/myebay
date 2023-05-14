package it.prova.myebay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.myebay.model.Ruolo;
import it.prova.myebay.repository.ruolo.RuoloRepository;

@Service
public class RuoloServiceImpl implements RuoloService{
	@Autowired
	RuoloRepository ruoloRepository;

	@Override
	public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice) {
		return ruoloRepository.findByDescrizioneAndCodice(descrizione, codice);
	}

	@Override
	public void inserisciNuovo(Ruolo ruoloInstance) {
		ruoloRepository.save(ruoloInstance);
		
	}

	@Override
	public List<Ruolo> listAllElements() {
		return (List<Ruolo>) ruoloRepository.findAll();
	}

	
	

}
