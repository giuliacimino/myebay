package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.model.Ruolo;

public interface RuoloService {
	Ruolo cercaPerDescrizioneECodice(String descrizione, String codice);
	void inserisciNuovo (Ruolo ruoloInstance);
	List<Ruolo> listAllElements();


}
