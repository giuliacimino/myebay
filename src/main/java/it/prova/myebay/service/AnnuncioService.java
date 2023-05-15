package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Annuncio;

public interface AnnuncioService {
	public List<Annuncio> listAllElements();

	public Annuncio caricaSingoloElemento(Long id);


	public void aggiorna(Annuncio annuncioInstance);

	public void inserisciNuovo(Annuncio annuncioInstance);

	public void rimuovi(Long idToDelete);

	public List<Annuncio> findByExample(Annuncio example);
	
	public Annuncio caricaAnnuncioConCategorie (Long id);
	
	public List<Annuncio> cercaPerUtente_Username();

}
